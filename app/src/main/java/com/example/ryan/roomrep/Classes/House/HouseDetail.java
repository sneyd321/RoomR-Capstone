package com.example.ryan.roomrep.Classes.House;

import android.graphics.drawable.Drawable;

public class HouseDetail {


    private Drawable imgDrawable;
    private String title;

    public HouseDetail(Drawable imgDrawable, String title) {
        this.imgDrawable = imgDrawable;
        this.title = title;
    }

    public Drawable getImgDrawable() {
        return imgDrawable;
    }

    public String getTitle() {
        return title;
    }

}
