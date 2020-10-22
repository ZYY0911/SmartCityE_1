package com.example.smartcitye_1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smartcitye_1.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/16 at 8:25
 */
public class WyfwActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private LinearLayout layoutWyfu;
    private LinearLayout layoutTcwfw;
    private LinearLayout layoutZbrx;
    private LinearLayout layoutBxdh;
    private LinearLayout layoutBmfu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wyfw_layout);
        initView();
        title.setText("物业服务");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        layoutWyfu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WyfwActivity.this, FwxqActivity.class);
                intent.putExtra("info", "物业服务中心：\n电话：400-4124125");
                intent.putExtra("tel", "400-4124125");
                startActivity(intent);
            }
        });
        layoutBmfu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WyfwActivity.this, FwxqActivity.class);
                intent.putExtra("info", "便民服务：\n电话：400-423141225");
                intent.putExtra("tel", "400-423141225");
                startActivity(intent);
            }
        });
        layoutTcwfw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WyfwActivity.this, FwxqActivity.class);
                intent.putExtra("info", "停车位服务中心：\n电话：400-222222");
                intent.putExtra("tel", "400-222222");
                startActivity(intent);
            }
        });
        layoutZbrx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WyfwActivity.this, FwxqActivity.class);
                intent.putExtra("info", "24小时值班热线：\n电话：400-1111111");
                intent.putExtra("tel", "400-1111111");
                startActivity(intent);
            }
        });
        layoutBxdh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WyfwActivity.this, FwxqActivity.class);
                intent.putExtra("info", "报修电话：\n电话：400-555555");
                intent.putExtra("tel", "400-555555");
                startActivity(intent);
            }
        });
    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        layoutWyfu = findViewById(R.id.layout_wyfu);
        layoutTcwfw = findViewById(R.id.layout_tcwfw);
        layoutZbrx = findViewById(R.id.layout_zbrx);
        layoutBxdh = findViewById(R.id.layout_bxdh);
        layoutBmfu = findViewById(R.id.layout_bmfu);
    }
}

