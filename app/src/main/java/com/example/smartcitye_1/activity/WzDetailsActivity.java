package com.example.smartcitye_1.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcitye_1.R;
import com.example.smartcitye_1.bean.CarWzInfos;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/15 at 18:07
 */
public class WzDetailsActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private TextView tvTime;
    private TextView tvLocation;
    private TextView tvAction;
    private TextView tvTzsh;
    private TextView tvKf;
    private TextView tvMoney;
    private CarWzInfos carWzInfos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wz_details_layout);
        initView();
        carWzInfos = (CarWzInfos) getIntent().getSerializableExtra("info");
        title.setText("违章详情");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTime.setText(carWzInfos.getTime());
        tvLocation.setText(carWzInfos.getPlace());
        tvAction.setText(carWzInfos.getDescription());
        tvTzsh.setText(carWzInfos.getNotification());
        tvKf.setText(carWzInfos.getDeductPoints() + "");
        tvMoney.setText(carWzInfos.getCost() + "");
    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        tvTime = findViewById(R.id.tv_time);
        tvLocation = findViewById(R.id.tv_location);
        tvAction = findViewById(R.id.tv_action);
        tvTzsh = findViewById(R.id.tv_tzsh);
        tvKf = findViewById(R.id.tv_kf);
        tvMoney = findViewById(R.id.tv_money);
    }
}
