package com.example.ryan.roomrep.TenantFragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.ryan.roomrep.MainActivityTenant;
import com.example.ryan.roomrep.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;


public class RepairPictureFragment extends Fragment {
    Button btnTakePhoto;
    Button btnSendPhoto;
    Button btnPredictPhoto;
    Button btnPickPhoto;
    Spinner predictionResults;
    TextView txtError;
    ProgressBar progressBar;
    ImageView imgView;
    String imageString;

    public static final int PICTURE_TAKER = 1;
    public static final int PICK_PICTURE = 2;

    //192.168.2.28
    String urlString = "http://34.73.26.89:8000/photo";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_repair_picture, container, false);
        btnTakePhoto = view.findViewById(R.id.btn_takePicture);
        btnSendPhoto = view.findViewById(R.id.btn_sendProblem);
        btnPickPhoto = view.findViewById(R.id.btn_pickImage);
        btnPredictPhoto = view.findViewById(R.id.btn_predict);
        predictionResults = view.findViewById(R.id.spn_predictions);
        txtError = view.findViewById(R.id.txt_error);
        progressBar = view.findViewById(R.id.progressBar);
        imgView = view.findViewById(R.id.imgView);

        //Setting visibility to invisible until prediction is done.
        btnPredictPhoto.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        predictionResults.setVisibility(View.INVISIBLE);
        txtError.setVisibility(View.INVISIBLE);

        //initializing onclick listeners for the buttons
        btnSendPhoto.setOnClickListener(onSendPhoto);
        btnTakePhoto.setOnClickListener(onTakePhoto);
        btnPredictPhoto.setOnClickListener(onPredictPhoto);
        btnPickPhoto.setOnClickListener(onPickPhoto);
        return view;

    }

    View.OnClickListener onPickPhoto = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, PICK_PICTURE);
        }
    };

    View.OnClickListener onSendPhoto = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((MainActivityTenant)getActivity()).setViewPager(6);

        }
    };

    View.OnClickListener onTakePhoto = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            startActivityForResult(takePictureIntent, PICTURE_TAKER);
        }
    };

    View.OnClickListener onPredictPhoto = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestPrediction();
        }
    };


    //Converts Bitmap into a string.
    private String convertBitmapToString(Bitmap bitmap){
        ByteArrayOutputStream btmp = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, btmp);
        byte [] bytes = btmp.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    //Function that rotates image
    public Bitmap rotateImage(Bitmap bMap){
        Matrix matrix;
        //Create object of new Matrix.
        matrix = new Matrix();
        //set image rotation value to 90 degrees in matrix.
        matrix.postRotate(90);
        //Create bitmap with new values.
        Bitmap bMapRotate = Bitmap.createBitmap(bMap, 0, 0, bMap.getWidth(), bMap.getHeight(), matrix, true);
        return bMapRotate;
    }

    //creates the JSON request
    private JSONObject createJsonObject(){
        //initalize json object
        JSONObject photo = new JSONObject();
        try {
            //put photo string in json object
            photo.put("photo", imageString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //return json object
        return photo;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICTURE_TAKER){
            imageString = "";
            btnPredictPhoto.setVisibility(View.VISIBLE);
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageString = convertBitmapToString(imageBitmap);
            imgView.setImageBitmap(imageBitmap);
            btnTakePhoto.setText("Retake Photo");
        }
        if (requestCode == PICK_PICTURE){
            if (resultCode == RESULT_OK){
                Bitmap imageBitmap;
                //reset image string
                imageString = "";
                //get image from intent
                Uri image = data.getData();
                //convert uri into bitmap
                try (InputStream is = getContext().getContentResolver().openInputStream(image)) {
                    imageBitmap = BitmapFactory.decodeStream(is);
                    imageBitmap = Bitmap.createScaledBitmap(imageBitmap, 200, 200, true);
                    is.close();
                } catch (FileNotFoundException e) {
                    imageBitmap = null;
                    e.printStackTrace();
                } catch (IOException e) {
                    imageBitmap = null;
                    e.printStackTrace();
                }
                //if an image is found
                if (imageBitmap != null){
                    //convert bitmap to string
                    imageBitmap = rotateImage(imageBitmap);
                    imageString = convertBitmapToString(imageBitmap);

                    imgView.setImageBitmap(imageBitmap);
                }
                //if no image is found return an error
                else{
                    Toast.makeText(getContext(), "Error: Error loading image", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    public void requestPrediction(){
        RequestQueue MyRequestQueue = Volley.newRequestQueue(getContext());
        final String json = createJsonObject().toString();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, urlString, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Toast.makeText(getContext(), response.toString(), Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public byte[] getBody() {
                //specifies the body of the request in JSON format
                return json.getBytes();
            }

            @Override
            public String getBodyContentType() {
                //specifies the type of the request
                return "application/json";
            }
        };

        MyRequestQueue.add(jsonArrayRequest);
    }
}
