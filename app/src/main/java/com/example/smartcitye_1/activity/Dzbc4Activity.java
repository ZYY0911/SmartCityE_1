package com.example.smartcitye_1.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcitye_1.AppClient;
import com.example.smartcitye_1.R;
import com.example.smartcitye_1.bean.BusTitle;
import com.example.smartcitye_1.net.VolleyLo;
import com.example.smartcitye_1.net.VolleyTo;
import com.example.smartcitye_1.util.Util;

import org.json.JSONObject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/15 at 19:29
 */
public class Dzbc4Activity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private TextView tvLine;
    private TextView etName;
    private TextView etTel;
    private TextView etUp;
    private TextView etDown;
    private TextView etDate;
    private Button btNext;
    private BusTitle busTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dzbc4_layout);
        AppClient.add(this);
        initView();
        title.setText("定制班车");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // Intent i = new Intent(Dzbc3Activity.this, Dzbc4Activity.class);
        //                i.putExtra("info", busTitle);
        //                i.putExtra("date", chooseTime);
        //                i.putExtra("name", etName.getText().toString());
        //                i.putExtra("tel", etTel.getText().toString());
        //                i.putExtra("up", etUp.getSelectedItem().toString());
        //                i.putExtra("down", etDown.getSelectedItem().toString());
        busTitle = (BusTitle) getIntent().getSerializableExtra("info");
        etName.setText(getIntent().getStringExtra("name"));
        etDate.setText(getIntent().getStringExtra("date"));
        etDown.setText(getIntent().getStringExtra("down"));
        etUp.setText(getIntent().getStringExtra("up"));
        etTel.setText(getIntent().getStringExtra("tel"));
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VolleyTo volleyTo = new VolleyTo();
                volleyTo.setUrl("setOrderBus")
                        //{busid:1,"name":"abc","phone":"1234","upsite":"火车站",
                        // "downsite":"阳光新天地","traveltime":"2020-10-5,2020-10-6","isPay":"N"}
                        .setJsonObject("busid", busTitle.getBusid())
                        .setJsonObject("name", etName.getText().toString())
                        .setJsonObject("phone", etTel.getText().toString())
                        .setJsonObject("upsite", etUp.getText().toString())
                        .setJsonObject("downsite", etDown.getText().toString())
                        .setJsonObject("traveltime", etDate.getText().toString())
                        .setJsonObject("isPay", "N")
                        .setVolleyLo(new VolleyLo() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                if (jsonObject.optString("RESULT").equals("S")) {
                                    Util.showToast("提交成功", Dzbc4Activity.this);
                                    AppClient.finAll();
                                } else {
                                    Util.showToast("提交失败", Dzbc4Activity.this);
                                }
                            }

                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                Util.showToast("提交失败", Dzbc4Activity.this);

                            }
                        }).start();
            }
        });
    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        tvLine = findViewById(R.id.tv_line);
        etName = findViewById(R.id.et_name);
        etTel = findViewById(R.id.et_tel);
        etUp = findViewById(R.id.et_up);
        etDown = findViewById(R.id.et_down);
        etDate = findViewById(R.id.et_date);
        btNext = findViewById(R.id.bt_next);
    }
}
