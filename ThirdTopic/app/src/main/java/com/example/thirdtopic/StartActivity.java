package com.example.thirdtopic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.thirdtopic.Start.OneStartFragment;
import com.example.thirdtopic.Start.ThreeStartFragment;
import com.example.thirdtopic.Start.TwoStartFragment;

import java.util.ArrayList;
import java.util.List;

public class StartActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private Button button;
    private RadioGroup radioGroup;
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<View> raList;
    public MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        initView();
        initAddList();
        myAdapter = new MyAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myAdapter);
        raList = radioGroup.getTouchables();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                RadioButton radioButton = (RadioButton) raList.get(position);
                radioButton.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.start_rb1:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.start_rb2:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.start_rb3:
                        viewPager.setCurrentItem(2);
                        break;
                }
            }
        });
//        View view = LayoutInflater.from(fragmentList.get(2).getContext()).inflate(R.layout.fragment_three_start, null);
//        button=view.findViewById(R.id.start_btn);


    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        public MyAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }

    private void initAddList() {
        OneStartFragment oneStartFragment = new OneStartFragment();
        TwoStartFragment twoStartFragment = new TwoStartFragment();
        ThreeStartFragment threeStartFragment = new ThreeStartFragment();
        fragmentList.add(oneStartFragment);
        fragmentList.add(twoStartFragment);
        fragmentList.add(threeStartFragment);


    }

    private void initView() {
        viewPager = findViewById(R.id.start_layout_vp);
        radioGroup = findViewById(R.id.start_rg);
    }

}