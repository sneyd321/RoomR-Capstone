package com.example.ryan.roomrep;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ryan.roomrep.Classes.House;

import java.io.ByteArrayOutputStream;


public class AddHouseFragment extends Fragment {

    ImageView houseImage;
    EditText address;
    Button next;
    Button takePhoto;
    House house;
    SeekBar seekBar;
    TextView rent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_house, container, false);

        houseImage = view.findViewById(R.id.imgHouse);
        address = view.findViewById(R.id.edtAddress);
        next = view.findViewById(R.id.btnNextLAH);
        next.setOnClickListener(onNext);
        house = new House();
        house.setLandlord(((MainActivityLandlord)getActivity()).getLandlord());
        takePhoto = view.findViewById(R.id.btnTakeHousePicture);
        takePhoto.setOnClickListener(onTakePicture);
        seekBar = view.findViewById(R.id.skbRent);
        rent = view.findViewById(R.id.txtRentDisplay);
        seekBar.setMax(1000);
        seekBar.setOnSeekBarChangeListener(onChangeRent);





        return view;
    }


    SeekBar.OnSeekBarChangeListener onChangeRent = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            rent.setText(Integer.toString(progress));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };


    View.OnClickListener onTakePicture = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(takePictureIntent, 1);

        }
    };


    View.OnClickListener onNext = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            house.setAddress(address.getText().toString());
            house.setRent(Integer.parseInt(rent.getText().toString()));
            if (houseImage.getDrawable() != null){
                Bitmap bmp = ((BitmapDrawable)houseImage.getDrawable()).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                house.setImage(byteArray);
                ((MainActivityLandlord)getActivity()).getHouse().add(house);
                ((MainActivityLandlord)getActivity()).setViewPager(2);
            }
            else {
                Toast.makeText(getActivity(), "Please take a photo", Toast.LENGTH_SHORT).show();
            }


        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        Bundle extras = data.getExtras();
        Bitmap imageBitmap = (Bitmap) extras.get("data");
        houseImage.setImageBitmap(imageBitmap);
    }
}
