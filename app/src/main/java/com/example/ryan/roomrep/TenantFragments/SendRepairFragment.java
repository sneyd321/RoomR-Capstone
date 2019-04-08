package com.example.ryan.roomrep.TenantFragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ryan.roomrep.Classes.Repair;
import com.example.ryan.roomrep.MainActivityTenant;
import com.example.ryan.roomrep.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SendRepairFragment extends Fragment {
    private FirebaseStorage storage = FirebaseStorage.getInstance();

    Button btn_send;
    TextView txt_problemIdentified;
    EditText txt_description;
    ImageView img_problem;
    Repair repair;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_send_repair, container, false);

        btn_send = view.findViewById(R.id.btn_sendProblem);
        txt_problemIdentified = view.findViewById(R.id.txt_problemId);
        txt_description = view.findViewById(R.id.edt_description);
        img_problem = view.findViewById(R.id.img_repair);

        btn_send.setOnClickListener(onSendProblemToDB);


        setView();



        return view;
    }

    private void setView(){
        //Sets the view with the image and problem identification picked by the user.
        int lastPosition = ((MainActivityTenant)getActivity()).getRepair().size() - 1;
        if(lastPosition >= 0){
            repair = ((MainActivityTenant)getActivity()).getRepair().get(lastPosition);
            Bitmap bmp = BitmapFactory.decodeByteArray(repair.getImage(), 0, repair.getImage().length);
            img_problem.setImageBitmap(bmp);
            txt_problemIdentified.setText(repair.getProblemIdentification());
        }else{
            Toast.makeText(getActivity(),"Please Go back to Repairs and find a repair",Toast.LENGTH_SHORT).show();
        }

    }
    View.OnClickListener onSendProblemToDB = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            //Here the method sends the problem detected to the next page. and launches sendProblem.
            if(!txt_description.getText().toString().equals("")){
                repair.setDescription(txt_description.getText().toString());
                String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                DateFormat time = new SimpleDateFormat("hh:mm:ss a");
                String formatTime = time.format(new Date());
                repair.setDateReported(date + " " + formatTime);

                insertPhotoIntoStorage();
                insertValueIntoRepair();
            }else{
                Toast.makeText(getActivity(), "Please write a description", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private void insertPhotoIntoStorage(){
        //Inserts photo into the firestorage.
        String path = "Repairs/" + repair.getDateReported() + ".png";
        repair.setStorageReference(path);
        StorageReference repairRef = storage.getReference(path);

        StorageMetadata metadata = new StorageMetadata.Builder()
                .setCustomMetadata("text", "test")
                .build();

        UploadTask uploadTask = repairRef.putBytes(repair.getImage(), metadata);
        uploadTask.addOnSuccessListener(getActivity(), new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

            }
        });
    }

    private void insertValueIntoRepair(){
        //Adds values to the firestorage.
        Task<Void> result = repair.addValues();
        result.addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(repair.isSuccessful()){
                    Toast.makeText(getActivity(),"Repair Problem has been uploaded", Toast.LENGTH_SHORT).show();
                    ((MainActivityTenant)getActivity()).setViewPager(0);
                }
            }
        });
    }

}
