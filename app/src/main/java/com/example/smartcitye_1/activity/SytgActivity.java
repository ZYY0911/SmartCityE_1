package com.example.smartcitye_1.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.smartcitye_1.R;
import com.example.smartcitye_1.bean.Sytg;
import com.example.smartcitye_1.fragment.SytgFargemnt;
import com.example.smartcitye_1.util.MyViewPagerTranslate;

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
 * @Create by 张瀛煜 on 2020/10/16 at 9:30
 */
public class SytgActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    List<Sytg> sytgList;
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sytg_layout);

        initView();
        title.setText("商业推广");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        sytgList = new ArrayList<>();
        sytgList.add(new Sytg("物业服务：首页设置物业服务功能入口，点击跳转到物业服务页面，显示物业支持响应的所有部门和联系方式，如物业服务中心、停车位服务中心、24小时值班热线、报修电话、便民服务等，提供快速拨号和评价反馈的通道。", "市场营销", R.mipmap.a));
        sytgList.add(new Sytg("物业服务：首页设置物业服务功能入口，点击跳转到物业服务页面，显示物业支持响应的所有部门和联系方式，如物业服务中心、停车位服务中心、24小时值班热线、报修电话、便民服务等，提供快速拨号和评价反馈的通道。", "市场营销", R.mipmap.b));
        sytgList.add(new Sytg("物业服务：首页设置物业服务功能入口，点击跳转到物业服务页面，显示物业支持响应的所有部门和联系方式，如物业服务中心、停车位服务中心、24小时值班热线、报修电话、便民服务等，提供快速拨号和评价反馈的通道。", "市场营销", R.mipmap.c));
        sytgList.add(new Sytg("物业服务：首页设置物业服务功能入口，点击跳转到物业服务页面，显示物业支持响应的所有部门和联系方式，如物业服务中心、停车位服务中心、24小时值班热线、报修电话、便民服务等，提供快速拨号和评价反馈的通道。", "市场营销", R.mipmap.d));
        fragments = new ArrayList<>();
        for (int i = 0; i < sytgList.size(); i++) {
            fragments.add(new SytgFargemnt(sytgList.get(i)));
        }
        viewPager.setPageTransformer(false, new MyViewPagerTranslate(this));

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

    List<Fragment> fragments;

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        viewPager = findViewById(R.id.view_pager);
    }
}
