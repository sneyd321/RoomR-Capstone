package com.example.ryan.roomrep.LandlordFragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ryan.roomrep.Adapters.StatePagerAdapter;
import com.example.ryan.roomrep.Classes.House.RentBuilder;
import com.example.ryan.roomrep.R;

import java.util.ArrayList;

public class AddHouseStatePagerFragment extends Fragment {


    RentBuilder rentBuilder = new RentBuilder();

    StatePagerAdapter adapter;

    ArrayList<Fragment> addHouseFragmentList = new ArrayList<>();

    int pagerIndex = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_house_state_pager, container, false);




        return view;
    }



    private StatePagerAdapter setupViewPager(){
        StatePagerAdapter adapter = new StatePagerAdapter(getChildFragmentManager());
        for (Fragment fragment : addHouseFragmentList){

            adapter.addFragment(fragment, null);
        }
        return adapter;
    }


    public void setPosition(int position) {
        this.pagerIndex = position;
    }

    public void setFragment(Fragment fragment){
        this.addHouseFragmentList.add(fragment);
    }






}
