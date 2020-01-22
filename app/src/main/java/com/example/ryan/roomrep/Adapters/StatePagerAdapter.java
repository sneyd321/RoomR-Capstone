package com.example.ryan.roomrep.Adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class StatePagerAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> fragments = new ArrayList<>();
    private final List<String> fragmentNames = new ArrayList<>();


    public StatePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment, String name){
        fragments.add(fragment);
        fragmentNames.add(name);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
