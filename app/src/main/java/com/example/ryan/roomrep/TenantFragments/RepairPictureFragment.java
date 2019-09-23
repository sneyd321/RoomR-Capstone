package com.example.ryan.roomrep.TenantFragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

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

import com.example.ryan.roomrep.Classes.PhotoManager;

import com.example.ryan.roomrep.Classes.Repair;
import com.example.ryan.roomrep.Classes.Router.TenantRouterAction;

import com.example.ryan.roomrep.R;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


import static android.app.Activity.RESULT_OK;


public class RepairPictureFragment extends Fragment {
    Button btnTakePhoto;
    Button btnSendPhoto;
    Button btnPickPhoto;
    Spinner languages;
    TextView txtError;
    ImageView imgView;
    String language;
    String languageSelected;

    String imageString;
    ArrayAdapter<String> spinnerArrayAdapter;
    Repair repair;
    PhotoManager photoManager;

    private static ProgressDialog mProgressDialog;
    private TenantRouterAction actionListener;

    public static final int PICTURE_TAKER = 1;
    public static final int PICK_PICTURE = 2;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_repair_picture, container, false);
        btnTakePhoto = view.findViewById(R.id.btn_takePicture);
        btnSendPhoto = view.findViewById(R.id.btn_sendProblem);
        btnPickPhoto = view.findViewById(R.id.btn_pickImage);
        languages = view.findViewById(R.id.spn_languages);
        txtError = view.findViewById(R.id.txt_error);
        imgView = view.findViewById(R.id.imgView);
        repair = new Repair();
        photoManager = new PhotoManager();

        btnSendPhoto.setEnabled(false);
        txtError.setVisibility(View.INVISIBLE);

        //initializing onclick listeners for the buttons
        btnSendPhoto.setOnClickListener(onSendPhoto);
        btnTakePhoto.setOnClickListener(onTakePhoto);
        btnPickPhoto.setOnClickListener(onPickPhoto);

        languages.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                language = adapterView.getItemAtPosition(pos).toString();
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
            //Send Repair Object as a parameter
            //Bitmap bmp = ((BitmapDrawable)imgView.getDrawable()).getBitmap();
            //                if (actionListener != null) {
            //                    actionListener.onNavigateToSendRepair();
            //                }
        }
    };

    View.OnClickListener onTakePhoto = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            startActivityForResult(takePictureIntent, PICTURE_TAKER);
        }
    };

    public void setView(){
    }
    View.OnClickListener onPredictPhoto = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            btnSendPhoto.setEnabled(true);
            if ((spinnerArrayAdapter == null) || spinnerArrayAdapter.isEmpty()) {
                //requestPrediction();
            }else{
                //requestPrediction();
            }
            //requestPrediction();
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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

    //showSimpleProgressDialog(getContext(), "Loading...","Predicting Image",false);
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

    public void setActionListener(TenantRouterAction actionListener) {
        this.actionListener = actionListener;
    }
}