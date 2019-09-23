package com.example.ryan.roomrep.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ryan.roomrep.Classes.Repair;
import com.example.ryan.roomrep.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class RepairRecyclerViewAdapter extends RecyclerView.Adapter<RepairRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RepairRecyclerView";
    private List<Repair> mData;
    private Context context;
    private LayoutInflater mInflater;
    final long ONE_MEGABYTE = 1024 * 1024;
    FirebaseStorage storage = FirebaseStorage.getInstance();

    public RepairRecyclerViewAdapter(Context context, List<Repair> data){
        this.mData = data;
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.repair_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        //This allows the method to bind the values to the views.
        Repair repair = mData.get(position);
        holder.txt_descriptionRow.setText(repair.getDescription());
        holder.txt_dateRow.setText(repair.getDateReported());
        holder.txt_problemIdRow.setText(repair.getName());
        StorageReference storageRef = storage.getReference();
        StorageReference pathReference = storageRef.child(repair.getPhotoRef());
        try{
            pathReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    holder.imgViewRow.setImageBitmap(bitmap);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });
        }catch (NullPointerException ex){

        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgViewRow;
        TextView txt_descriptionRow;
        TextView txt_problemIdRow;
        TextView txt_dateRow;

        public ViewHolder(View itemView) {
            super(itemView);
            imgViewRow = itemView.findViewById(R.id.imgRepairRow);
            txt_dateRow = itemView.findViewById(R.id.txt_dateRow);
            txt_descriptionRow = itemView.findViewById(R.id.txt_descriptionRow);
            txt_problemIdRow = itemView.findViewById(R.id.txt_problemRow);
        }
    }
}
