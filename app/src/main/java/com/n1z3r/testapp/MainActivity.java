package com.n1z3r.testapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toolbar;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.nio.file.WatchEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
private TabLayout tabLayout;
private ViewPager viewPager;
private TabItem jokes_item,web_item;
private ListView listView;
private Button btnReload;
PagerAdapter pagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayout);
        jokes_item = findViewById(R.id.jokes_item);
        web_item = findViewById(R.id.web_item);
        btnReload = findViewById(R.id.btnReload);
        listView = findViewById(R.id.list_view);

        final ViewPager viewPager = findViewById(R.id.viewPager);
        final PagerAdapter pagerAdapter = new PageAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setTabTextColors(Color.parseColor("#727272"), Color.parseColor("#00BCD4"));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
            viewPager.setCurrentItem(tab.getPosition());
                 if(tab.getPosition()==0){
                     tab.setIcon(R.drawable.jokes_clicked);
                     pagerAdapter.notifyDataSetChanged();
                 }  else  if(tab.getPosition()==1){
                              pagerAdapter.notifyDataSetChanged();

                     tab.setIcon(R.drawable.web_clicked);
                             }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if(tab.getPosition()==0) {
                    tab.setIcon(R.drawable.jokes_unclicked);
                }
              else if(tab.getPosition()==1){
                    tab.setIcon(R.drawable.web_unclick);
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }
}