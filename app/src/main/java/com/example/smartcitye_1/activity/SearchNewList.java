package com.example.smartcitye_1.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcitye_1.R;
import com.example.smartcitye_1.adapter.NewListAdapter;
import com.example.smartcitye_1.bean.NewList;
import com.example.smartcitye_1.net.VolleyLo;
import com.example.smartcitye_1.net.VolleyTo;
import com.example.smartcitye_1.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/16 at 7:45
 */
public class SearchNewList extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private String msg;
    private ListView listItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_new_list);
        initView();
        msg = getIntent().getStringExtra("info");
        setVolley();
        title.setText("搜索结果");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    List<NewList> newLists;

    private void setVolley() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getNewsByKeys")
                .setJsonObject("keys", msg)
                .setDialog(this)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        newLists = new Gson().fromJson(jsonObject.optJSONArray(Util.Rows).toString()
                                , new TypeToken<List<NewList>>() {
                                }.getType());
                        Log.i("aaa", "onResponse: " + newLists.size());
                        listItem.setAdapter(new NewListAdapter(SearchNewList.this, newLists));


                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        setVolley();

                    }
                }).start();
    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        listItem = findViewById(R.id.list_item);
    }
}
