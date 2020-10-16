package com.example.smartcitye_1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcitye_1.AppClient;
import com.example.smartcitye_1.R;
import com.example.smartcitye_1.bean.BusDetails;
import com.example.smartcitye_1.bean.BusTitle;
import com.example.smartcitye_1.net.VolleyLo;
import com.example.smartcitye_1.net.VolleyTo;
import com.example.smartcitye_1.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/15 at 19:24
 */
public class Dzbc3Activity extends AppCompatActivity {
    private BusTitle busTitle;
    private String chooseTime;
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private TextView tvLine;
    private EditText etName;
    private EditText etTel;
    private Spinner etUp;
    private Spinner etDown;
    private Button btNext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dzbc3_layout);
        initView();
        AppClient.add(this);
        busTitle = (BusTitle) getIntent().getSerializableExtra("info");
        chooseTime = getIntent().getStringExtra("date");
        title.setText("定制班车");
        setVollet();
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvLine.setText("起点：" + busTitle.getStartSite() + "  终点:" + busTitle.getEndSite());
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etTel.getText()) || TextUtils.isEmpty(etName.getText())) {
                    Util.showDialog("信息不能为空", Dzbc3Activity.this);
                    return;
                }
                if (etTel.getText().toString().length()!=11){
                    Util.showToast("手机号不正确",Dzbc3Activity.this);
                    return;
                }

                Intent i = new Intent(Dzbc3Activity.this, Dzbc4Activity.class);
                i.putExtra("info", busTitle);
                i.putExtra("date", chooseTime);
                i.putExtra("name", etName.getText().toString());
                i.putExtra("tel", etTel.getText().toString());
                i.putExtra("up", etUp.getSelectedItem().toString());
                i.putExtra("down", etDown.getSelectedItem().toString());
                startActivity(i);
            }
        });

    }

    private void setVollet() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("busStationById")
                .setJsonObject("busid", busTitle.getBusid())
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        busStations = new Gson().fromJson(jsonObject.optJSONArray(Util.Rows).toString()
                                , new TypeToken<List<BusDetails>>() {
                                }.getType());
                        up = new ArrayList<>();
                        dowm = new ArrayList<>();
                        for (int i = 0; i < busStations.size(); i++) {
                            up.add(busStations.get(i).getSiteName());
                            dowm.add(busStations.get(i).getSiteName());
                        }
                        etUp.setAdapter(new ArrayAdapter<String>(Dzbc3Activity.this
                                , android.R.layout.simple_list_item_1, up));
                        etDown.setAdapter(new ArrayAdapter<String>(Dzbc3Activity.this
                                , android.R.layout.simple_list_item_1, dowm));
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();

    }

    List<BusDetails> busStations;
    private List<String> up, dowm;

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        tvLine = findViewById(R.id.tv_line);
        etName = findViewById(R.id.et_name);
        etTel = findViewById(R.id.et_tel);
        etUp = findViewById(R.id.et_up);
        etDown = findViewById(R.id.et_down);
        btNext = findViewById(R.id.bt_next);
    }
}
