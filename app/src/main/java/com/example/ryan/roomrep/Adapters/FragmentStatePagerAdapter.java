package com.example.ryan.roomrep.Adapters;


import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ryan.roomrep.LandlordFragments.ViewLeaseFragment;
import com.example.ryan.roomrep.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.adapter.StatefulAdapter;

public class FragmentStatePagerAdapter extends FragmentStateAdapter implements StatefulAdapter {

    private List<Fragment> fragments;

    public FragmentStatePagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
        fragments = new ArrayList<>();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        ViewLeaseFragment viewLeaseFragment = new ViewLeaseFragment();
        return fragments.set(position, viewLeaseFragment);
    }


    @Override
    public int getItemCount() {
        return this.fragments.size();
    }




}
