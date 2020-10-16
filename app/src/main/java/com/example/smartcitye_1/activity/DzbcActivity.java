package com.example.smartcitye_1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcitye_1.AppClient;
import com.example.smartcitye_1.R;
import com.example.smartcitye_1.bean.BusTitle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/15 at 18:58
 */
public class DzbcActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private ImageView ivPhoto;
    private TextView tvMsg;
    private Button btNext;

    private BusTitle busTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dzbc_layout);
        AppClient.add(this);
        initView();
        busTitle = (BusTitle) getIntent().getSerializableExtra("info");
        title.setText("定制班车");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvMsg.setText("起点：" + busTitle.getStartSite() + "\n" +
                "终点：" + busTitle.getEndSite() + "\n" +
                "票价：" + busTitle.getPrice() + ".0元\n" +
                "里程：" + busTitle.getMileage() + ".0km");
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DzbcActivity.this, Dzbc2Activity.class);
                i.putExtra("info", busTitle);
                startActivity(i);
            }
        });
        ivPhoto.setImageResource(busTitle.getBusid() % 2 == 0 ? R.mipmap.ditu : R.mipmap.ditu2);

    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        ivPhoto = findViewById(R.id.iv_photo);
        tvMsg = findViewById(R.id.tv_msg);
        btNext = findViewById(R.id.bt_next);
    }
}
