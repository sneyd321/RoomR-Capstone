package com.example.ryan.roomrep.TenantFragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.ryan.roomrep.Classes.PhotoManager;
import com.example.ryan.roomrep.Classes.Prediction;
import com.example.ryan.roomrep.Classes.Repair;
import com.example.ryan.roomrep.MainActivityTenant;
import com.example.ryan.roomrep.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import static android.R.layout.simple_spinner_item;

import static android.app.Activity.RESULT_OK;


public class RepairPictureFragment extends Fragment {
    Button btnTakePhoto;
    Button btnSendPhoto;
    Button btnPredictPhoto;
    Button btnPickPhoto;
    Spinner predictionResults;
    TextView txtError;
    ImageView imgView;

    String imageString;
    ArrayList<Prediction> predictionArray;
    String predictionPicked;
    ArrayAdapter<String> spinnerArrayAdapter;
    Repair repair;
    PhotoManager photoManager;

    private static ProgressDialog mProgressDialog;

    private ArrayList<String> predictionLabels = new ArrayList<>();


    public static final int PICTURE_TAKER = 1;
    public static final int PICK_PICTURE = 2;

    //192.168.2.28
    String urlString = "http://35.203.55.41:8000/photo";


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
        imgView = view.findViewById(R.id.imgView);
        repair = new Repair();
        photoManager = new PhotoManager();

        ArrayAdapter<String> spinnerArrayAdapter;

        //Setting visibility to invisible until prediction is done.
        btnPredictPhoto.setVisibility(View.INVISIBLE);
        predictionResults.setVisibility(View.INVISIBLE);
        btnSendPhoto.setVisibility(View.INVISIBLE);
        txtError.setVisibility(View.INVISIBLE);

        //initializing onclick listeners for the buttons
        btnSendPhoto.setOnClickListener(onSendPhoto);
        btnTakePhoto.setOnClickListener(onTakePhoto);
        btnPredictPhoto.setOnClickListener(onPredictPhoto);
        btnPickPhoto.setOnClickListener(onPickPhoto);

        predictionResults.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                predictionPicked = adapterView.getItemAtPosition(pos).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
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
            repair.setProblemIdentification(predictionPicked);
            if(imgView.getDrawable() != null){
                Bitmap bmp = ((BitmapDrawable)imgView.getDrawable()).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                repair.setImage(byteArray);
                ((MainActivityTenant)getActivity()).getRepair().add(repair);
                ((MainActivityTenant)getActivity()).setViewPager(9);
            }else{
                Toast.makeText(getActivity(), "Please take a photo", Toast.LENGTH_SHORT).show();
            }
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
            predictionArray = new ArrayList<>();
            btnSendPhoto.setVisibility(View.VISIBLE);
            if ((spinnerArrayAdapter == null) || spinnerArrayAdapter.isEmpty()) {
                requestPrediction();
            }else{
                clearSpinner();
                requestPrediction();
            }
            //requestPrediction();
        }
    };

    public void clearSpinner(){
        spinnerArrayAdapter.clear();
        predictionPicked = "";
        spinnerArrayAdapter.notifyDataSetChanged();
    }

    //creates the JSON request This is for the post request.
    /*private JSONObject createJsonObject(String imgString){
        //initalize json object
        JSONObject photo = new JSONObject();
        String imageString = imgString;
        try {
            //put photo string in json object
            photo.put("photo", imageString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //return json object
        return photo;
    }*/

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        btnPredictPhoto.setVisibility(View.VISIBLE);
        if (requestCode == PICTURE_TAKER){
            imageString = "";
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageString = photoManager.convertBitmapToString(imageBitmap);
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
                    photoManager = new PhotoManager();
                    imageBitmap = photoManager.rotateImage(imageBitmap);
                    imageString = photoManager.convertBitmapToString(imageBitmap);

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
        showSimpleProgressDialog(getContext(), "Loading...","Predicting Image",false);
        RequestQueue MyRequestQueue = Volley.newRequestQueue(getContext());
        final String json = photoManager.createJsonObject(imageString).toString();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, urlString, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //Toast.makeText(getContext(), response.toString(), Toast.LENGTH_LONG).show();
                predictionArray = new ArrayList<>();
                try{
                    for(int i = 0; i < response.length(); i++){
                        JSONObject predictionRow = response.getJSONObject(i);
                        Prediction prediction = new Prediction();
                        prediction.setLabel(predictionRow.getString("label"));
                        prediction.setScore(predictionRow.getString("scored"));
                        predictionArray.add(prediction);
                    }
                    for(int i = 0; i < predictionArray.size(); i++){
                        predictionLabels.add(predictionArray.get(i).getLabel());
                    }
                    spinnerArrayAdapter = new ArrayAdapter<>(getContext(), simple_spinner_item , predictionLabels);
                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    predictionResults.setAdapter(spinnerArrayAdapter);
                    removeSimpleProgressDialog();
                    predictionResults.setVisibility(View.VISIBLE);
                }catch (JSONException e){
                    e.printStackTrace();
                    removeSimpleProgressDialog();
                }


            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Error Connecting to Predictions, Please try again later", Toast.LENGTH_LONG).show();
                btnSendPhoto.setVisibility(View.INVISIBLE);
                removeSimpleProgressDialog();
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

    public static void removeSimpleProgressDialog() {
        try {
            if (mProgressDialog != null) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                    mProgressDialog = null;
                }
            }
        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();

        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void showSimpleProgressDialog(Context context, String title,
                                                String msg, boolean isCancelable) {
        try {
            if (mProgressDialog == null) {
                mProgressDialog = ProgressDialog.show(context, title, msg);
                mProgressDialog.setCancelable(isCancelable);
            }

            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }

        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();
        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}