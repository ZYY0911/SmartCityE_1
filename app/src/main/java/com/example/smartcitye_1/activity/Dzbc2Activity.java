package com.example.smartcitye_1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcitye_1.AppClient;
import com.example.smartcitye_1.R;
import com.example.smartcitye_1.adapter.RlAdapter;
import com.example.smartcitye_1.bean.BusTitle;
import com.example.smartcitye_1.bean.RlBeasn;
import com.example.smartcitye_1.util.OnClickItem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/15 at 19:04
 */
public class Dzbc2Activity extends AppCompatActivity {
    private BusTitle busTitle;
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private GridView girdRl;
    private TextView tvMsg;
    private Button btNext;
    private List<RlBeasn> rlBeasns, chooseBean;
    private List<Integer> integers;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dzbc2_layout);
        AppClient.add(this);
        busTitle = (BusTitle) getIntent().getSerializableExtra("info");
        initView();
        chooseBean = new ArrayList<>();
        rlBeasns = new ArrayList<>();
        integers = new ArrayList<>();
        title.setText("定制班车");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setDate();
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dzbc2Activity.this, Dzbc3Activity.class);
                i.putExtra("info", busTitle);
                i.putExtra("date", choose);
                startActivity(i);
            }
        });


    }

    private void setDate() {
        final Calendar calendar = Calendar.getInstance();
        for (int i = 1; i < getWeekDay(calendar); i++) {
            rlBeasns.add(new RlBeasn(i, 1, 1, 3));
            integers.add(3);
        }
        for (int i = 0; i < 42; i++) {
            rlBeasns.add(new RlBeasn(i, calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH) + 1, getBg(calendar)));
            integers.add(getBg(calendar));
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        if (rlBeasns.size()!=42){
            int count = 49 - rlBeasns.size();
            for (int i = 0; i <count; i++) {
                rlBeasns.add(new RlBeasn(i, 1, 1, 3));
                integers.add(3);
            }
        }
        final RlAdapter adapter = new RlAdapter(Dzbc2Activity.this, rlBeasns);
        girdRl.setAdapter(adapter);
        adapter.setOnClickItem(new OnClickItem() {
            @Override
            public void onClick(int position, String name) {
                RlBeasn rlBeasn = rlBeasns.get(position);
                if (name.equals("0") || name.equals("1")) {
                    rlBeasn.setBg(2);
                    chooseBean.add(rlBeasn);
                } else {
                    chooseBean.remove(rlBeasn);
                    rlBeasn.setBg(integers.get(position));
                }
                rlBeasns.set(position, rlBeasn);
                adapter.notifyDataSetChanged();
                setMsgText();
            }
        });
    }

    String choose;

    private void setMsgText() {
        Collections.sort(chooseBean, new Comparator<RlBeasn>() {
            @Override
            public int compare(RlBeasn o1, RlBeasn o2) {
                return o1.getIndex() - o2.getIndex();
            }
        });
        String str = "";
        choose = "";
        for (int i = 0; i < chooseBean.size(); i++) {
            RlBeasn rlBeasn = chooseBean.get(i);
            if (i == 0) {
                str = "已选择日期：\n2020-" + rlBeasn.getMonth() + "-" + rlBeasn.getDay();
                choose = "2020-" + rlBeasn.getMonth() + "-" + rlBeasn.getDay();
            } else {
                str += ",2020-" + rlBeasn.getMonth() + "-" + rlBeasn.getDay();
                choose += ",2020-" + rlBeasn.getMonth() + "-" + rlBeasn.getDay();
            }
        }
        tvMsg.setText(str);
    }

    private int getBg(Calendar calendar) {
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        if (week == 1 || week == 7) {
            return 1;
        } else {
            return 0;
        }
    }


    private int getWeekDay(Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_WEEK);
    }


    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        girdRl = findViewById(R.id.gird_rl);
        tvMsg = findViewById(R.id.tv_msg);
        btNext = findViewById(R.id.bt_next);
    }
}
