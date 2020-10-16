package com.example.smartcitye_1.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcitye_1.R;
import com.example.smartcitye_1.util.Util;

import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/16 at 8:53
 */
public class KjflImageActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private ImageView itemImage;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            itemImage.setImageBitmap(Util.getBitMat(getIntent().getStringExtra("info") + "&" + random.nextInt(10)));

            return false;
        }
    });
    Random random = new Random();
    private boolean isLoop = true;
    private TextView tvMag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kjgl_image_layout);

        initView();
        title.setText("快件管理");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        itemImage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                tvMag.setText(getIntent().getStringExtra("info"));
                return true;
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isLoop) {
                    handler.sendEmptyMessage(0);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isLoop = false;
    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        itemImage = findViewById(R.id.item_image);
        tvMag = findViewById(R.id.tv_mag);
    }
}
