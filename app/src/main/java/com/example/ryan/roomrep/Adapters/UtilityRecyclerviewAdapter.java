package com.example.ryan.roomrep.Adapters;

import android.animation.Animator;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.ryan.roomrep.Classes.House.Utility.Utility;
import com.example.ryan.roomrep.R;

import java.util.List;

public class UtilityRecyclerviewAdapter extends RecyclerView.Adapter<UtilityRecyclerviewAdapter.ViewHolder> {

    private List<Utility> data;
    private LayoutInflater inflater;
    private ItemClickListener clickListener;
    private View view;

    public UtilityRecyclerviewAdapter(Context context, List<Utility> data){
        this.data = data;
        this.inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public UtilityRecyclerviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = this.inflater.inflate(R.layout.utility_selection_row, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull UtilityRecyclerviewAdapter.ViewHolder holder, int position) {
        Utility utility = data.get(position);
        holder.txtName.setText(utility.getName());


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
        TextView txtName;
        TextView txtTotalAmount;
        Switch swtAddUtility;
        RadioButton rbtnTenant, rbtnLandlord, rbtnWeek, rbtnMonth, rbtnEveryOtherMonth;
        EditText edtAmount;



        public ViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtAddUtilityUtilityName);
            txtTotalAmount = itemView.findViewById(R.id.txtAddUtilityTotalAmount);
            swtAddUtility = itemView.findViewById(R.id.swtAddUtilitySelectUtility);
            rbtnTenant = itemView.findViewById(R.id.rbtnAddUtilityTenantResponsibility);
            rbtnLandlord = itemView.findViewById(R.id.rbtnAddUtilityLandlordResponsibility);
            rbtnWeek = itemView.findViewById(R.id.rbtnAddUtilityAmountWeek);
            rbtnMonth = itemView.findViewById(R.id.rbtnAddUtilityAmountMonth);
            rbtnEveryOtherMonth = itemView.findViewById(R.id.rbtnAddUtilityAmountEveryOtherMonth);
            edtAmount = itemView.findViewById(R.id.edtAddUtilityAmount);
            swtAddUtility.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (compoundButton.isChecked()) {
                        rbtnTenant.setEnabled(true);
                        rbtnLandlord.setEnabled(true);
                        rbtnWeek.setEnabled(true);
                        rbtnMonth.setEnabled(true);
                        rbtnEveryOtherMonth.setEnabled(true);
                        edtAmount.setEnabled(true);
                        return;
                    }
                    rbtnTenant.setEnabled(false);
                    rbtnLandlord.setEnabled(false);
                    rbtnWeek.setEnabled(false);
                    rbtnMonth.setEnabled(false);
                    rbtnEveryOtherMonth.setEnabled(false);
                    edtAmount.setEnabled(false);
                }
            });



        }

        @Override
        public void onClick(View v) {
            if (clickListener != null){
                clickListener.onItemClick(v, getAdapterPosition());
            }
        }

    }

    public void addItem(Utility utility) {
        this.data.add(utility);

        notifyItemInserted(getItemCount());
    }


    public void setClickListener(ItemClickListener itemClickListener){
        this.clickListener = itemClickListener;
    }




}
