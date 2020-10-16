package com.example.smartcitye_1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.smartcitye_1.R;
import com.example.smartcitye_1.adapter.CarManagerAdpter;
import com.example.smartcitye_1.bean.CarManager;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/16 at 9:50
 */
public class CarManagerActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private ListView listView;
    private Button btAdd;

    List<CarManager> carManagers;

    private CarManagerAdpter adpter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_manager_layout);
        initView();
        title.setText("车辆管理");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        carManagers = new ArrayList<>();
        carManagers.add(new CarManager("鲁A10001", "1", "1", "张三", "1241251234", "长河街道", "大学路"));
        carManagers.add(new CarManager("鲁A10002", "2", "3", "张三", "1241251234", "长河街道", "大学路"));
        carManagers.add(new CarManager("鲁A10003", "3", "4", "李四", "1241251234", "长河街道", "大学路"));
        adpter = new CarManagerAdpter(this, carManagers);

        listView.setAdapter(adpter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CarManagerActivity.this, AddCarMagaerActivity.class);
                intent.putExtra("lx", 2);
                intent.putExtra("position", position);
                intent.putExtra("info", carManagers.get(position));
                startActivityForResult(intent, 2);
            }
        });

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CarManagerActivity.this, AddCarMagaerActivity.class);
                intent.putExtra("lx", 1);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    carManagers.add((CarManager) data.getSerializableExtra("info"));
                    adpter.notifyDataSetChanged();
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    carManagers.set(data.getIntExtra("position", 1), (CarManager) data.getSerializableExtra("info"));
                    adpter.notifyDataSetChanged();
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        listView = findViewById(R.id.list_view);
        btAdd = findViewById(R.id.bt_add);
    }
}
