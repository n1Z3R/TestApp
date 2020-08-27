package com.n1z3r.testapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class PageAdapter extends FragmentPagerAdapter {

    private int num;


    public PageAdapter(@NonNull FragmentManager fm,int num) {
        super(fm);
        this.num = num;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return new JokesTab();
            case 1:
                return new WebTab();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return num;
    }
}
