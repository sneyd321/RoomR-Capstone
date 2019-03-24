package com.example.ryan.roomrep.Classes;

public class House {


    private String address;
    private String ImageBitmap;

    public House(String address, String ImageBitmap){
        this.setAddress(address);
        this.setImageBitmap(ImageBitmap);

    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImageBitmap() {
        return ImageBitmap;
    }

    public void setImageBitmap(String imageBitmap) {
        ImageBitmap = imageBitmap;
    }
}
