package com.example.ryan.roomrep.Classes;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.Base64;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;

public class PhotoManager {
    public PhotoManager(){}

    //Converts Bitmap into a string.
    public String convertBitmapToString(Bitmap bitmap){
        ByteArrayOutputStream btmp = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, btmp);
        byte [] bytes = btmp.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    //Function that rotates image
    public Bitmap rotateImage(Bitmap bitmap){
        Matrix matrix;
        //Create object of new Matrix.
        matrix = new Matrix();
        //set image rotation value to 90 degrees in matrix.
        matrix.postRotate(90);
        //Create bitmap with new values.
        Bitmap bMapRotate = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return bMapRotate;
    }

    //creates the JSON request This is for the post request.
    public JSONObject createJsonObject(String imgString){
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
    }

}
