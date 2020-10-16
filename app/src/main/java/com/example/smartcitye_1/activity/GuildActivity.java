package com.example.smartcitye_1.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.smartcitye_1.AppClient;
import com.example.smartcitye_1.R;
import com.example.smartcitye_1.fragment_gu.GuileFragment;
import com.example.smartcitye_1.fragment_gu.GuileFragment2;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/14 at 17:19
 */
public class GuildActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private int images[] = {R.mipmap.a, R.mipmap.b, R.mipmap.c, R.mipmap.d, R.mipmap.e};
    private List<Fragment> fragments;
    private LinearLayout layout;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gulid_layout);
        preferences = AppClient.sharedPreferences;
        if (preferences.getBoolean(AppClient.IsFirst, true)) {
            preferences.edit().putBoolean(AppClient.IsFirst, false).apply();
        } else {
            startActivity(new Intent(GuildActivity.this, AppHomeActivity.class));
            finish();
            return;
        }
        initView();
        fragments = new ArrayList<>();
        for (int i = 0; i < images.length; i++) {
            if (i != images.length - 1) {
                fragments.add(new GuileFragment(images[i]));
            } else {
                fragments.add(new GuileFragment2(images[i]));
            }
            ImageView imageView = new ImageView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(20, 20);
            layoutParams.setMargins(30, 0, 30, 0);
            imageView.setLayoutParams(layoutParams);
            if (i == 0) {
                imageView.setImageResource(R.drawable.select_yuan);
            } else {
                imageView.setImageResource(R.drawable.noselect_yuan);
            }
            layout.addView(imageView);
        }
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < layout.getChildCount(); i++) {
                    ImageView imageView
                            = (ImageView) layout.getChildAt(i);
                    if (i == position) {
                        imageView.setImageResource(R.drawable.select_yuan);
                    } else {
                        imageView.setImageResource(R.drawable.noselect_yuan);
                    }
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });

    }

    private void initView() {
        viewPager = findViewById(R.id.view_pager);
        layout = findViewById(R.id.layout);
    }
}
