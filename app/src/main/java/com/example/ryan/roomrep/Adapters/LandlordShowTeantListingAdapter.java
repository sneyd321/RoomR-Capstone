package com.example.ryan.roomrep.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ryan.roomrep.Classes.Tenant.Tenant;
import com.example.ryan.roomrep.R;


import java.util.List;

public class LandlordShowTeantListingAdapter extends RecyclerView.Adapter<LandlordShowTeantListingAdapter.ViewHolder> {

    private Context context;
    private List<Tenant> data;
    private LayoutInflater inflater;
    private ItemClickListener itemClickListener;
    public String passData;
    public String passData2;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;
    //public int postionNumber;


    public LandlordShowTeantListingAdapter(Context context, List<Tenant> data) {
        this.inflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }


    @NonNull
    @Override
    public LandlordShowTeantListingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.addtenant_row, parent, false);
        return new LandlordShowTeantListingAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final LandlordShowTeantListingAdapter.ViewHolder holder, final int position) {
        final Tenant teant = data.get(position);

        holder.txtName.setText(teant.getFirstName() + " " + teant.getLastName());
        holder.txtRatingInf.setText("4.3");

        if(mOnItemClickListener != null){

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition(); // 1
                    mOnItemClickListener.onItemClick(holder.itemView,position); // 2
                }
            });
        }
        if(mOnItemLongClickListener != null){
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = holder.getLayoutPosition();
                    mOnItemLongClickListener.onItemLongClick(holder.itemView,position);

                    return true;
                }
            });
        }


//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                holder.txtName.setText("GG"+ holder.getLayoutPosition());
//                passData = teant.getFirstName();
//                passData2 = teant.getLastName();
//
//
//
//
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    public interface OnItemLongClickListener{
        void onItemLongClick(View view,int position);
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }
    public void setOnClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
            //implements View.OnClickListener
    {

        TextView txtName;
        TextView txtRatingInf;


        public ViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.tenantName);
            txtRatingInf = itemView.findViewById(R.id.tenantRatingInf);

            //itemView.setOnClickListener(this);
        }




//        @Override
//        public void onClick(View v) {
//            if (itemClickListener != null) {
//                itemClickListener.onItemClick(v, getAdapterPosition());
//            }
//
//        }
//    }
//
//
//
//    public interface ItemClickListener {
//
//        void onItemClick(View view, int position);
//
//    }
        // convenience method for getting data at click position
        //String getItem(int id) {
        //   return mData.get(id);
        //}

        // allows clicks events to be caught
        //void setClickListener(ItemClickListener itemClickListener) {
        //   this.mClickListener = itemClickListener;
        //}

        // parent activity will implement this method to respond to click events
        //public interface ItemClickListener {
        //   void onItemClick(View view, int position);
        //}


    }
}