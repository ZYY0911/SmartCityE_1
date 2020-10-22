package com.example.smartcitye_1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcitye_1.R;
import com.example.smartcitye_1.adapter.CarInfoAdapter;
import com.example.smartcitye_1.bean.CarWzInfos;
import com.example.smartcitye_1.net.VolleyLo;
import com.example.smartcitye_1.net.VolleyTo;
import com.example.smartcitye_1.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;
import java.util.Random;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/15 at 17:37
 */
public class CarInfoAcvitiy extends AppCompatActivity {
    private String cp;
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private ListView listView;
    private Button btQuery;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_info_layout);
        cp = getIntent().getStringExtra("info");
        Log.i("aa", "onCreate: " + cp);
        initView();
        title.setText("查询结果");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setVolley();
        btQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.setCount(carPeals.size());
                adapter.notifyDataSetChanged();
            }
        });
    }

    List<CarWzInfos> carPeals;

    private void setVolley() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getViolationsByCarId")
                .setJsonObject("carid", cp)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        carPeals = new Gson().fromJson(jsonObject.optJSONArray(Util.Rows).toString()
                                , new TypeToken<List<CarWzInfos>>() {
                                }.getType());
                        setListView();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private CarInfoAdapter adapter;

    private void setListView() {
        Random random = new Random();
        int count = ((random.nextInt(2) + 1) % 2 == 0 ? 5 : 6);
        if (carPeals.size() < 5) {
            count = carPeals.size();
        }
        Log.i("aaa", "setListView: " + carPeals.size());
        Log.i("aaa", "setListView: " + count);
        adapter = new CarInfoAdapter(this, carPeals, count);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CarInfoAcvitiy.this, WzDetailsActivity.class);
                intent.putExtra("info", carPeals.get(position));
                startActivity(intent);
            }
        });
    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        listView = findViewById(R.id.list_view);
        btQuery = findViewById(R.id.bt_query);
    }
}
