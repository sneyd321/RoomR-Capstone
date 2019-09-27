package com.example.ryan.roomrep.TenantFragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.ryan.roomrep.Classes.LanguageTranslation;
import com.example.ryan.roomrep.Classes.Network.FragmentEventListener;
import com.example.ryan.roomrep.Classes.Network.Network;
import com.example.ryan.roomrep.Classes.PhotoManager;

import com.example.ryan.roomrep.Classes.Router.TenantRouterAction;

import com.example.ryan.roomrep.R;
import com.google.gson.Gson;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


import static android.app.Activity.RESULT_OK;
import static com.android.volley.VolleyLog.TAG;


public class RepairPictureFragment extends Fragment implements FragmentEventListener {
    Button btnTakePhoto;
    Button btnSendPhoto;
    Button btnPickPhoto;
    Spinner languages;
    TextView txtError;
    ImageView imgView;
    String language;
    File file;
    ProgressDialog progressDialog;
    //LanguageTranslation languageTranslation;

    PhotoManager photoManager;

    private static ProgressDialog mProgressDialog;
    private TenantRouterAction actionListener;

    public static final int PICTURE_TAKER = 1;
    public static final int PICK_PICTURE = 2;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_repair_picture, container, false);
        btnTakePhoto = view.findViewById(R.id.btn_takePicture);
        btnSendPhoto = view.findViewById(R.id.btn_sendPhoto);
        btnPickPhoto = view.findViewById(R.id.btn_pickImage);
        languages = view.findViewById(R.id.spn_languages);
        txtError = view.findViewById(R.id.txt_error);
        imgView = view.findViewById(R.id.imgView);
        photoManager = new PhotoManager();

        btnSendPhoto.setVisibility(View.INVISIBLE);
        txtError.setVisibility(View.INVISIBLE);

        //initializing onclick listeners for the buttons
        btnSendPhoto.setOnClickListener(onSendPhoto);
        btnTakePhoto.setOnClickListener(onTakePhoto);
        btnPickPhoto.setOnClickListener(onPickPhoto);
        language = "none";

        languages.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                String shortFormLanguages[] = getResources().getStringArray(R.array.languagesShortForm);
                language = shortFormLanguages[pos];

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
            if (actionListener != null) {
                uploadPicture();
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICTURE_TAKER){
            Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
            imgView.setImageBitmap(imageBitmap);
            btnTakePhoto.setText("Retake Photo");
            file = saveImageToInternalStorage(imageBitmap, "Photo", "TestNetworkRepair.png");
            btnSendPhoto.setVisibility(View.VISIBLE);
        }
        if (requestCode == PICK_PICTURE){
            if (resultCode == RESULT_OK){
                Bitmap imageBitmap;
                //get image from intent
                Uri image = data.getData();
                //convert uri into bitmap
                try (InputStream is = getContext().getContentResolver().openInputStream(image)) {
                    imageBitmap = BitmapFactory.decodeStream(is);
                    imageBitmap = Bitmap.createScaledBitmap(imageBitmap, 200, 200, true);
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
                    imgView.setImageBitmap(imageBitmap);
                    file = saveImageToInternalStorage(imageBitmap, "Photo", "TestNetworkRepair.png");
                    btnSendPhoto.setVisibility(View.VISIBLE);
                }
                //if no image is found return an error
                else{
                    Toast.makeText(getContext(), "Error: Error loading image", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public File saveImageToInternalStorage(Bitmap bitmap, String directoryName, String filename) {
        ContextWrapper contextWrapper = new ContextWrapper(getActivity());

        File directory = contextWrapper.getDir(directoryName, Context.MODE_PRIVATE);

        File path = new File(directory, filename);

        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(path);

            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            try {
                fos.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }

        return path;

    }

    public void uploadPicture(){
        Network network = Network.getInstance();
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Uploading Picture...");
        progressDialog.show();
        network.registerObserver(this);
        network.uploadRepairImage(file, language);
        Toast.makeText(getContext(), "photo sent", Toast.LENGTH_SHORT).show();
    }

    public void setActionListener(TenantRouterAction actionListener) {
        this.actionListener = actionListener;
    }

    @Override
    public void update(String response) {
        Gson gson = new Gson();
        try {
            JSONArray jsonArray = new JSONArray(response);
            JSONObject jsonWordsInOtherLanguage = jsonArray.getJSONObject(0);
            JSONObject jsonImgUrl = jsonArray.getJSONObject(1);
            JSONObject jsonWordsInEnglish = jsonArray.getJSONObject(2);
            JSONObject jsonCategory = jsonArray.getJSONObject(3);

            String category = (jsonCategory.getString("category"));
            String imgUrl = (jsonImgUrl.getString("imgUrl"));
            List<String> wordsInEnglish = new ArrayList<>();
            for(int i = 0 ; i < jsonWordsInEnglish.getJSONArray("wordsInEnglish").length(); i++){
                wordsInEnglish.add(jsonWordsInEnglish.getJSONArray("wordsInEnglish").getString(i));
            }
            List<String> wordsInOtherLanguage = new ArrayList<>();
            if(!jsonWordsInOtherLanguage.getString("wordsInOtherLanguage").equals("none")){
                for(int i = 0 ; i < jsonWordsInOtherLanguage.getJSONArray("wordsInOtherLanguage").length(); i++){
                    wordsInOtherLanguage.add(jsonWordsInOtherLanguage.getJSONArray("wordsInOtherLanguage").getString(i));
                }
            }
            else{
                wordsInOtherLanguage.add("none");
            }
            LanguageTranslation languageTranslation = new LanguageTranslation(wordsInOtherLanguage, wordsInEnglish, imgUrl, category);
            Log.d(TAG, "update: " + languageTranslation);
            languageTranslation.setLanguage(language);
            progressDialog.dismiss();
            actionListener.onNavigateToSendRepair(languageTranslation);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}