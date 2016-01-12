package com.example.floatingbutton;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by ABHISHEK on 12/25/2015.
 */
public class HomeTabAdapter extends FragmentPagerAdapter {


    private static final int TAB_COUNT = 3;

    public HomeTabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        TestFragment frag = new TestFragment();
        frag.position = position;
        return frag;
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }
}
