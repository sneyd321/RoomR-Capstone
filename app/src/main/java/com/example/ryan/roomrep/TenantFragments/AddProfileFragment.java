package com.example.ryan.roomrep.TenantFragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ryan.roomrep.Classes.Network.FragmentEventListener;
import com.example.ryan.roomrep.Classes.Network.Network;
import com.example.ryan.roomrep.Classes.Profile.Profile;
import com.example.ryan.roomrep.Classes.Router.ProfileRouterAction;
import com.example.ryan.roomrep.LoginActivities.LoginActivity;
import com.example.ryan.roomrep.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class AddProfileFragment extends Fragment implements FragmentEventListener {

    EditText edtFirstName;
    EditText edtLastName;
    EditText edtEmail;
    EditText edtBio;
    Button btnAddProfile;
    TextView txtErrorMessage;
    TextView txtGoBack;

    ProfileRouterAction routerAction;
    Profile profile;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_profile, container, false);
        edtFirstName = view.findViewById(R.id.edtProfileFirstName);
        edtLastName = view.findViewById(R.id.edtProfileLastName);
        edtEmail = view.findViewById(R.id.edtProfileEmail);
        edtBio = view.findViewById(R.id.edtProfileBio);
        btnAddProfile = view.findViewById(R.id.btnCreateProfile);
        btnAddProfile.setOnClickListener(onCreateProfile);
        txtErrorMessage = view.findViewById(R.id.txtAddProfileErrorMessage);
        txtGoBack = view.findViewById(R.id.txtCreateProfileGoBack);
        txtGoBack.setOnTouchListener(onGoBack);
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        edtFirstName.setText(sharedPref.getString("ProfileFirstName", ""));
        edtLastName.setText(sharedPref.getString("ProfileLastName", ""));
        edtEmail.setText(sharedPref.getString("ProfileEmail", ""));
        edtBio.setText(sharedPref.getString("ProfileBio", ""));



        return view;
    }


    View.OnTouchListener onGoBack = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
            return false;
        }
    };

    View.OnClickListener onCreateProfile = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String firstName = edtFirstName.getText().toString();
            String lastName = edtLastName.getText().toString();
            String email = edtEmail.getText().toString();
            String bio = edtBio.getText().toString();
            profile = new Profile(firstName, lastName, email, bio);
            boolean isValid = true;

            Map<Integer, String> map = profile.getValidator();
            for (Map.Entry<Integer, String> entry : map.entrySet()) {
                if (!entry.getValue().isEmpty()){
                    switch (entry.getKey()){
                        case 0:
                            edtFirstName.setError(entry.getValue());
                            isValid = false;
                            break;
                        case 1:
                            edtLastName.setError(entry.getValue());
                            isValid = false;
                            break;
                        case 2:
                            edtEmail.setError(entry.getValue());
                            isValid = false;
                            break;
                        case 3:
                            edtBio.setError(entry.getValue());
                            isValid = false;
                            break;
                        case 4:
                            edtBio.setError(entry.getValue());
                            isValid = false;
                            break;
                    }
                }
            }

            if (isValid){
                addToSharedPreferences(profile);

                Network network = Network.getInstance();
                network.registerObserver(AddProfileFragment.this);
                network.addProfile(profile);

            }

        }
    };

    public void setRouterAction(ProfileRouterAction profileRouterAction) {
        this.routerAction = profileRouterAction;
    }


    public void addToSharedPreferences(Profile profile) {
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("ProfileFirstName", profile.getFirstName());
        editor.putString("ProfileLastName", profile.getLastName());
        editor.putString("ProfileEmail", profile.getEmail());
        editor.putString("ProfileBio", profile.getBio());
        editor.apply();
    }

    @Override
    public void update(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            final String result = jsonObject.get("Result").toString();
            if (result.equals("Error: Profile already exists")){
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                        alertDialog.setTitle("Profile Already Exists");
                        alertDialog.setMessage("Would you like to update your profile?");
                        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Update", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Network network = Network.getInstance();
                                network.updateProfile(profile);
                                alertDialog.dismiss();
                                if (routerAction != null) {
                                    routerAction.onNavigateToSearchListings();
                                }
                            }
                        });
                        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Don't Update", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                alertDialog.dismiss();
                                if (routerAction != null) {
                                    routerAction.onNavigateToSearchListings();
                                }
                            }
                        });
                        alertDialog.show();
                    }
                });
            }
            else {
                if (routerAction != null) {
                    routerAction.onNavigateToSearchListings();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }



    }
}
