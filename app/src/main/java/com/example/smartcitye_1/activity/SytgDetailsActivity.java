package com.example.smartcitye_1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcitye_1.R;
import com.example.smartcitye_1.bean.Sytg;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/16 at 9:41
 */
public class SytgDetailsActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private ImageView itemImage;
    private TextView tvTitle;
    private TextView itemName;
    private Button btNext;
    private Sytg sytg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sytgdetalis_layout);

        sytg = (Sytg) getIntent().getSerializableExtra("info");
        initView();
        title.setText("商业推广");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        itemImage.setImageResource(sytg.getImage());
        tvTitle.setText(sytg.getTitle());
        itemName.setText(sytg.getMsg());
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SytgDetailsActivity.this,YjfkActivity3.class));
            }
        });
    }

    private void initView() {

        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        itemImage = findViewById(R.id.item_image);
        tvTitle = findViewById(R.id.tv_title);
        itemName = findViewById(R.id.item_name);
        btNext = findViewById(R.id.bt_next);
    }
}
