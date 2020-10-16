package com.example.smartcitye_1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcitye_1.R;
import com.example.smartcitye_1.bean.CarManager;
import com.example.smartcitye_1.util.Util;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/16 at 10:01
 */
public class AddCarMagaerActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private EditText etCp;
    private EditText etCwh;
    private EditText etTckh;
    private EditText etName;
    private EditText etTel;
    private EditText etHome;
    private EditText etAddress;
    private Button btSave;
    int lx;
    private CarManager carManager;
    private int position;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_car_manage);
        initView();
        lx
                = getIntent().getIntExtra("lx", 1);

        title.setText(lx == 1 ? "添加车辆" : "修改车辆");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (lx == 1) {
            btSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CarManager carManager = new CarManager(etCp.getText().toString()
                            , etCwh.getText().toString()
                            , etTckh.getText().toString()
                            , etName.getText().toString()
                            , etTel.getText().toString()
                            , etHome.getText().toString()
                            , etAddress.getText().toString());
                    Intent intent = new Intent();
                    intent.putExtra("info", carManager);
                    setResult(RESULT_OK, intent);
                    Util.showToast("添加成功", AddCarMagaerActivity.this);
                    finish();
                }
            });
        } else {
            carManager = (CarManager) getIntent().getSerializableExtra("info");
            etCp.setText(carManager.getCp());
            etCwh.setText(carManager.getCwh());
            etTckh.setText(carManager.getTch());
            etName.setText(carManager.getCz());
            etTel.setText(carManager.getTel());
            etHome.setText(carManager.getHome());
            etAddress.setText(carManager.getAddress());
            position = getIntent().getIntExtra("position", 1);
            btSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CarManager carManager = new CarManager(etCp.getText().toString()
                            , etCwh.getText().toString()
                            , etTckh.getText().toString()
                            , etName.getText().toString()
                            , etTel.getText().toString()
                            , etHome.getText().toString()
                            , etAddress.getText().toString());
                    Intent intent = new Intent();
                    intent.putExtra("info", carManager);
                    intent.putExtra("position", position);
                    setResult(RESULT_OK, intent);
                    Util.showToast("修改成功", AddCarMagaerActivity.this);
                    finish();
                }
            });
        }
    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        etCp = findViewById(R.id.et_cp);
        etCwh = findViewById(R.id.et_cwh);
        etTckh = findViewById(R.id.et_tckh);
        etName = findViewById(R.id.et_name);
        etTel = findViewById(R.id.et_tel);
        etHome = findViewById(R.id.et_home);
        etAddress = findViewById(R.id.et_address);
        btSave = findViewById(R.id.bt_save);
    }
}

