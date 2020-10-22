package com.example.smartcitye_1.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcitye_1.AppClient;
import com.example.smartcitye_1.R;
import com.example.smartcitye_1.adapter.MyOrderAdapter;
import com.example.smartcitye_1.bean.BusOrder;
import com.example.smartcitye_1.net.VolleyLo;
import com.example.smartcitye_1.net.VolleyTo;
import com.example.smartcitye_1.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/15 at 19:49
 */
public class MyOderActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private TextView tvYzf;
    private TextView tvWzf;
    private ExpandableListView expandList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_order_layout);

        initView();
        title.setText("我的订单");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvWzf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvYzf.setTextColor(Color.BLACK);
                tvWzf.setTextColor(getResources().getColor(R.color.colorPrimary));
                tvWzf.setBackgroundResource(R.drawable.text_line);
                tvYzf.setBackgroundResource(R.drawable.text_no_line);
                setListAdapter("N");

            }
        });
        tvYzf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvYzf.setTextColor(getResources().getColor(R.color.colorPrimary));
                tvWzf.setTextColor(Color.BLACK);
                tvWzf.setBackgroundResource(R.drawable.text_no_line);
                tvYzf.setBackgroundResource(R.drawable.text_line);
                setListAdapter("Y");
            }
        });
        setVolley();
    }


    List<BusOrder> busOrders;

    private void setVolley() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getOrderBus")
                .setJsonObject("name", AppClient.username)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        busOrders = new Gson().fromJson(jsonObject.optJSONArray(Util.Rows).toString()
                                , new TypeToken<List<BusOrder>>() {
                                }.getType());
                        Collections.sort(busOrders, new Comparator<BusOrder>() {
                            @Override
                            public int compare(BusOrder o1, BusOrder o2) {
                                return o2.getId() - o1.getId();
                            }
                        });
                        setListAdapter("N");
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setListAdapter(String n) {
        List<BusOrder> busOrders1 = new ArrayList<>();
        for (int i = 0; i < busOrders.size(); i++) {
            BusOrder busOrder = busOrders.get(i);
            if (busOrder.getIsPay().equals(n)) {
                busOrders1.add(busOrder);
            }
        }
        Log.i("aaa", "setListAdapter: "+busOrders1.size());
        expandList.setAdapter(new MyOrderAdapter(busOrders1));
    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        tvYzf = findViewById(R.id.tv_yzf);
        tvWzf = findViewById(R.id.tv_wzf);
        expandList = findViewById(R.id.expand_list);
        expandList.setGroupIndicator(null);
    }
}
