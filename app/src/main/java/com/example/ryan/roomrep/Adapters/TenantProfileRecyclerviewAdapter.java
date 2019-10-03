package com.example.ryan.roomrep.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ryan.roomrep.Classes.Profile.Profile;
import com.example.ryan.roomrep.R;

import java.util.List;

public class TenantProfileRecyclerviewAdapter extends RecyclerView.Adapter<TenantProfileRecyclerviewAdapter.ViewHolder> {


    private List<Profile> data;
    private LayoutInflater inflater;
    private ItemClickListener itemClickListener;

    public TenantProfileRecyclerviewAdapter(Context context, List<Profile> data){
        this.data = data;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.tenant_profile_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Profile profile = data.get(position);
        holder.txtFirstName.setText(profile.getFirstName() + " " + profile.getLastName());
        holder.txtBio.setText(profile.getBio());
        holder.txtRating.setText("4.5");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView txtFirstName;
        TextView txtBio;
        TextView txtRating;

        public ViewHolder(View itemView) {
            super(itemView);
            txtFirstName = itemView.findViewById(R.id.txtTenantProfileFirstName);
            txtBio = itemView.findViewById(R.id.txtTenantProfileBio);
            txtRating = itemView.findViewById(R.id.txtTenantProfileRating);
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(v, getAdapterPosition());
            }
        }
    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }


}
