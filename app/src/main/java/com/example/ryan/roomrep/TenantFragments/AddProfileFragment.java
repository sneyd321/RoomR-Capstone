package com.example.ryan.roomrep.TenantFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.ryan.roomrep.Classes.Network.AddProfileListener;
import com.example.ryan.roomrep.Classes.Network.Network;
import com.example.ryan.roomrep.Classes.Profile.Profile;
import com.example.ryan.roomrep.Classes.Router.ProfileRouterAction;
import com.example.ryan.roomrep.LoginActivities.ProfileActivity;
import com.example.ryan.roomrep.R;

import java.util.Map;

public class AddProfileFragment extends Fragment implements AddProfileListener {

    EditText edtFirstName;
    EditText edtLastName;
    EditText edtEmail;
    EditText edtBio;
    Button btnAddProfile;

    ProfileRouterAction routerAction;





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
        return view;
    }

    View.OnClickListener onCreateProfile = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String firstName = edtFirstName.getText().toString();
            String lastName = edtLastName.getText().toString();
            String email = edtEmail.getText().toString();
            String bio = edtBio.getText().toString();
            Profile profile = new Profile(firstName, lastName, email, bio);
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
                ((ProfileActivity)getActivity()).addToSharedPreferences(profile);
                if (routerAction != null) {
                    routerAction.onNavigateToSearchListings();
                    Network network = new Network();
                    network.registerAddProfileListener(AddProfileFragment.this);
                    network.addProfile(profile);

                }
            }

        }
    };

    public void setRouterAction(ProfileRouterAction profileRouterAction) {
        this.routerAction = profileRouterAction;
    }


    @Override
    public void onAddProfile(Profile profile) {

    }
}
