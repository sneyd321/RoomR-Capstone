package com.example.ryan.roomrep.LandlordFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ryan.roomrep.Adapters.StatePagerAdapter;
import com.example.ryan.roomrep.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class HouseDetailStatePagerFragment extends Fragment {


    TabLayout tabLayout;
    ViewPager viewPager;

    StatePagerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_house_detail_state_pager, container, false);
        tabLayout = view.findViewById(R.id.houseDetailTabLayout);
        viewPager = view.findViewById(R.id.houseDetailViewPager);

        adapter = setupViewPager();
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager, true);
        tabLayout.getTabAt(0).setText("House");
        tabLayout.getTabAt(0).setIcon(getResources().getDrawable(R.drawable.ic_home_black_24dp));
        tabLayout.getTabAt(1).setText("Rent");
        tabLayout.getTabAt(1).setIcon(getResources().getDrawable(R.drawable.ic_monetization_on_black_24dp));
        tabLayout.getTabAt(2).setText("Agreement");
        tabLayout.getTabAt(2).setIcon(getResources().getDrawable(R.drawable.ic_assignment_black_24dp));





        return view;
    }



    private StatePagerAdapter setupViewPager(){
        StatePagerAdapter adapter = new StatePagerAdapter(getChildFragmentManager());
        adapter.addFragment(new RentDetailFragment(), "");
        return adapter;
    }
}
