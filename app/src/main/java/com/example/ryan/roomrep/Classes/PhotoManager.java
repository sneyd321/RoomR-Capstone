package com.example.ryan.roomrep.Classes;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.Base64;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;

public class PhotoManager {
    Bitmap bitmap;
    public PhotoManager(Bitmap bitmap){
        this.bitmap = bitmap;
    }

    //Converts Bitmap into a string.
    public String convertBitmapToString(){
        ByteArrayOutputStream btmp = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, btmp);
        byte [] bytes = btmp.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    //Function that rotates image
    public Bitmap rotateImage(){
        Matrix matrix;
        //Create object of new Matrix.
        matrix = new Matrix();
        //set image rotation value to 90 degrees in matrix.
        matrix.postRotate(90);
        //Create bitmap with new values.
        Bitmap bMapRotate = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return bMapRotate;
    }

}
