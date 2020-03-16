package com.example.ryan.roomrep.LandlordFragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ryan.roomrep.Adapters.ItemClickListener;
import com.example.ryan.roomrep.Adapters.LongClickItemListener;
import com.example.ryan.roomrep.R;

public class LandlordListingsFragment extends Fragment implements ItemClickListener, LongClickItemListener {


    RecyclerView rcyLandlordListings;
    Button btnPostListing;
    TextView txtNoListings;





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_landlord_listings, container, false);
        rcyLandlordListings = view.findViewById(R.id.rcyLandlordListings);
        txtNoListings = view.findViewById(R.id.txtLandlordListingsNoListings);
        rcyLandlordListings.setLayoutManager(new LinearLayoutManager(getActivity()));
        btnPostListing = view.findViewById(R.id.btnLandlordListingsPostListing);
        btnPostListing.setOnClickListener(onAddListing);


        txtNoListings.setVisibility(View.VISIBLE);
        return view;
    }







    @Override
    public void onItemClick(View view, int position) {

    }

    View.OnClickListener onAddListing = new View.OnClickListener() {
        @Override
        public void onClick(View v) {



        }
    };







    @Override
    public boolean onLongClick(View view, final int position) {
        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();

        alertDialog.setTitle("Remove Listing");
        alertDialog.setMessage("Are you sure you want to remove this property?");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Remove", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                alertDialog.dismiss();
            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Don't Remove", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
        return true;
    }
}
