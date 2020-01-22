package com.example.ryan.roomrep.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ryan.roomrep.R;
import com.google.android.libraries.places.api.model.AutocompletePrediction;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PlacesAddressSuggestionAdapter extends ArrayAdapter<AutocompletePrediction> {

    private List<AutocompletePrediction> predictionListFull;

    public PlacesAddressSuggestionAdapter(@NonNull Context context, List<AutocompletePrediction> results) {
        super(context, 0, results);
        predictionListFull = new ArrayList<>(results);
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return countryFilter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.places_address_row, parent, false);
        }

        TextView txtPrimary = convertView.findViewById(R.id.txtPlaceSuggestionPrimary);
        TextView txtSecondary = convertView.findViewById(R.id.txtPlaceSuggestionSecondary);

        AutocompletePrediction prediction = getItem(position);

        if (prediction != null) {
            txtPrimary.setText(prediction.getPrimaryText(null).toString());
            txtSecondary.setText(prediction.getSecondaryText(null).toString());
        }

        return convertView;
    }

    private Filter countryFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<AutocompletePrediction> suggestions = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                suggestions.addAll(predictionListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (AutocompletePrediction prediction : predictionListFull) {
                    if (prediction.getPrimaryText(null).toString().toLowerCase().contains(filterPattern)) {
                        suggestions.add(prediction);
                    }
                }
            }

            results.values = suggestions;
            results.count = suggestions.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            clear();
            addAll((List) results.values);
            notifyDataSetChanged();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((AutocompletePrediction) resultValue).getPrimaryText(null).toString();
        }
    };
}
