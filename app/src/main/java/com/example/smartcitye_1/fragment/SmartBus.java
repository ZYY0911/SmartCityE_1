package com.example.smartcitye_1.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcitye_1.R;
import com.example.smartcitye_1.activity.AppHomeActivity;
import com.example.smartcitye_1.activity.DzbcActivity;
import com.example.smartcitye_1.activity.MyOderActivity;
import com.example.smartcitye_1.adapter.BusExpandAdapter;
import com.example.smartcitye_1.bean.BusDetails;
import com.example.smartcitye_1.bean.BusTitle;
import com.example.smartcitye_1.net.VolleyLo;
import com.example.smartcitye_1.net.VolleyTo;
import com.example.smartcitye_1.util.OnClickItem;
import com.example.smartcitye_1.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/15 at 17:05
 */
public class SmartBus extends Fragment {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private ExpandableListView expandList;

    public SmartBus() {
    }

    private AppHomeActivity appHomeActivity;

    public SmartBus(AppHomeActivity appHomeActivity) {
        this.appHomeActivity = appHomeActivity;
    }

    public static SmartBus newInstance(AppHomeActivity appHomeActivity) {
        return new SmartBus(appHomeActivity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.smart_bus_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        title.setText("智慧巴士");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appHomeActivity.backOnClick();
            }
        });
        title1.setText("我的订单");
        title1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MyOderActivity.class));
            }
        });
        setShow();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            setShow();
        }
        super.onHiddenChanged(hidden);
    }

    List<BusTitle> busTitles;
    Map<BusTitle, List<BusDetails>> map;

    private void setShow() {
        map = new HashMap<>();
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("busList")
                .setDialog(getActivity())
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        busTitles = new Gson().fromJson(jsonObject.optJSONArray(Util.Rows).toString()
                                , new TypeToken<List<BusTitle>>() {
                                }.getType());
                        setVolley_Details();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setVolley_Details() {
        for (int i = 0; i < busTitles.size(); i++) {
            final BusTitle busTitle = busTitles.get(i);
            VolleyTo volleyTo = new VolleyTo();
            if (i == busTitles.size() - 1) {
                volleyTo.setDialog(getActivity());
            }
            volleyTo.setUrl("busStationById")
                    .setJsonObject("busid", busTitle.getBusid())
                    .setVolleyLo(new VolleyLo() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
                            List<BusDetails> busDetails = new Gson().fromJson(jsonObject.optJSONArray(Util.Rows).toString()
                                    , new TypeToken<List<BusDetails>>() {
                                    }.getType());
                            map.put(busTitle, busDetails);
                            if (map.size() == busDetails.size()) {
                                setViewExpand();
                            }
                        }

                        @Override
                        public void onErrorResponse(VolleyError volleyError) {

                        }
                    }).start();
        }
    }

    private void setViewExpand() {
        BusExpandAdapter expandAdapter = new BusExpandAdapter(map, busTitles);
        expandList.setAdapter(expandAdapter);
        expandAdapter.setOnClickItem(new OnClickItem() {
            @Override
            public void onClick(int position, String name) {
                Intent intent = new Intent(getActivity(), DzbcActivity.class);
                intent.putExtra("info", busTitles.get(position));
                startActivity(intent);
            }
        });
    }

    private void initView() {
        itemChange = getView().findViewById(R.id.item_change);
        title = getView().findViewById(R.id.title);
        title1 = getView().findViewById(R.id.title1);
        expandList = getView().findViewById(R.id.expand_list);
        expandList.setGroupIndicator(null);
    }
}
