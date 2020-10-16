package com.example.smartcitye_1.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcitye_1.R;
import com.example.smartcitye_1.activity.AppHomeActivity;
import com.example.smartcitye_1.activity.OrderDetailsActivity;
import com.example.smartcitye_1.adapter.OrderAdapter;
import com.example.smartcitye_1.bean.OrderTitle;
import com.example.smartcitye_1.net.VolleyLo;
import com.example.smartcitye_1.net.VolleyTo;
import com.example.smartcitye_1.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/15 at 9:15
 */
public class MyOderFragment extends Fragment {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private ListView listOrder;

    public MyOderFragment() {
    }

    private AppHomeActivity appHomeActivity;

    public MyOderFragment(AppHomeActivity appHomeActivity) {
        this.appHomeActivity = appHomeActivity;
    }

    public static MyOderFragment newInstance(AppHomeActivity appHomeActivity) {
        return new MyOderFragment(appHomeActivity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.order_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        title.setText("订单列表");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appHomeActivity.switchFragment(appHomeActivity.map.get("个人中心"));
            }
        });
        setVolley();

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            setVolley();
        }
        super.onHiddenChanged(hidden);

    }

    List<String> strings;
    private void setVolley() { strings = new ArrayList<>();
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getAllOrderType")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray(Util.Rows);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            strings.add(jsonArray.optString(i));
                        }
                        setVolley_OrderList();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    List<OrderTitle> orderTitles;

    private void setVolley_OrderList() {
        orderTitles = new ArrayList<>();
        for (int i = 0; i < strings.size(); i++) {
            VolleyTo volleyTo = new VolleyTo();
            final int finalI = i;
            volleyTo.setUrl("getOrderByType")
                    .setJsonObject("type", strings.get(i))
                    .setDialog(getActivity())
                    .setVolleyLo(new VolleyLo() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
                            List<OrderTitle> orderTitles1 = new Gson().fromJson(jsonObject.optJSONArray(Util.Rows).toString()
                                    , new TypeToken<List<OrderTitle>>() {
                                    }.getType());
                            orderTitles.addAll(orderTitles1);
                            if (finalI == strings.size() - 1) {
                                listOrder.setAdapter(new OrderAdapter(getActivity(), orderTitles));
                                listOrder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Intent intent = new Intent(getActivity(), OrderDetailsActivity.class);
                                        intent.putExtra("info", orderTitles.get(position));
                                        startActivity(intent);
                                    }
                                });
                            }

                        }

                        @Override
                        public void onErrorResponse(VolleyError volleyError) {

                        }
                    }).start();
        }
    }
    private void initView() {
        itemChange = getView().findViewById(R.id.item_change);
        title = getView().findViewById(R.id.title);
        title1 = getView().findViewById(R.id.title1);
        listOrder = getView().findViewById(R.id.list_order);
    }
}
