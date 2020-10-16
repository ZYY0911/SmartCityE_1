package com.example.smartcitye_1.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcitye_1.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/16 at 8:20
 */
public class SqdtdetailsActivity extends AppCompatActivity {
    private ImageView itemImage;
    private TextView itemName;
    private TextView tvDate;
    private ImageView itemChange;
    private TextView title;
    private TextView title1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqdtdetails_layout);
        initView();
        title.setText("社区动态");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        itemImage.setImageResource(getIntent().getIntExtra("index", R.mipmap.a));
        tvDate.setText("发布日期："+getIntent().getStringExtra("date"));
        itemName.setText("智慧社区是社区管理的一个新的管理形态，它利用物联网、云计算、移动互联网等新一代信息技术的集成应用,为社区居民提供一个安全舒适的智慧化生活环境,从而形成基于信息化、智能化社区管理与服务。\n" +
                "“以智慧小区提升社区品质”是社区管理的目标，社区引入智慧平台的能够有效推动经济流动，促进现代服务业发展。通过智慧社区系统的建设，解决了社区物业管理机制的即时响应，周边商业服务的推广，社区物业通知，友邻社交的平台等居民生活需求，系统主要包括以下功能模块。");


    }

    private void initView() {
        itemImage = findViewById(R.id.item_image);
        itemName = findViewById(R.id.item_name);
        tvDate = findViewById(R.id.tv_date);
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
    }
}
