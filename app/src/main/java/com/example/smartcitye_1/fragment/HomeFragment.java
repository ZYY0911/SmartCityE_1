package com.example.smartcitye_1.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.android.volley.VolleyError;
import com.example.smartcitye_1.R;
import com.example.smartcitye_1.activity.AppHomeActivity;
import com.example.smartcitye_1.activity.EmptyActivity;
import com.example.smartcitye_1.adapter.NewListAdapter;
import com.example.smartcitye_1.adapter.ServiceAdapter;
import com.example.smartcitye_1.adapter.SubjectAdapter;
import com.example.smartcitye_1.bean.HdInfo;
import com.example.smartcitye_1.bean.HomeImage;
import com.example.smartcitye_1.bean.NewList;
import com.example.smartcitye_1.bean.ServiceOrder;
import com.example.smartcitye_1.net.VolleyLo;
import com.example.smartcitye_1.net.VolleyTo;
import com.example.smartcitye_1.util.MyGirdView;
import com.example.smartcitye_1.util.MyImageView;
import com.example.smartcitye_1.util.MyListView;
import com.example.smartcitye_1.util.OnClickItem;
import com.example.smartcitye_1.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/15 at 7:42
 */
public class HomeFragment extends Fragment {
    private ViewFlipper viewFlipper;
    private MyGirdView serviceGird;
    private MyGirdView subjectGird;
    private LinearLayout newTypeLayout;
    private MyListView newList;

    public HomeFragment() {

    }

    private AppHomeActivity appHomeActivity;

    public HomeFragment(AppHomeActivity appHomeActivity) {
        this.appHomeActivity = appHomeActivity;
    }

    public static HomeFragment newInstance(AppHomeActivity appHomeActivity) {
        return new HomeFragment(appHomeActivity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    List<HomeImage> homeImages;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        setVolley_Image();
        setVolley_Service();
        setVolley_Subject();
        setVolley_NewList();
    }

    private void setVolley_NewList() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getNEWsList")

                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        newLists = new Gson().fromJson(jsonObject.optJSONArray(Util.Rows).toString()
                                , new TypeToken<List<NewList>>() {
                                }.getType());
                        setVolley_News();

                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    List<String> netType;

    private void setVolley_News() {
        netType = new ArrayList<>();
        newTypeLayout.removeAllViews();
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getAllNewsType")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray(Util.Rows);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            netType.add(jsonArray.optJSONObject(i).optString("newstype"));
                        }
                        for (int i = 0; i < netType.size(); i++) {
                            final TextView textView = new TextView(getActivity());
                            textView.setText(netType.get(i));
                            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                            layoutParams.setMargins(20, 0, 20, 0);
                            textView.setTextSize(20);
                            textView.setLayoutParams(layoutParams);
                            textView.setTextColor(Color.BLACK);
                            textView.setBackgroundResource(R.drawable.text_no_line);
                            if (i == 0) {
                                textView.setBackgroundResource(R.drawable.text_line);
                                textView.setTextColor(getResources().getColor(R.color.colorPrimary));
                            }
                            final int finalI = i;
                            textView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    for (int j = 0; j < newTypeLayout.getChildCount(); j++) {
                                        TextView textView1 = (TextView) newTypeLayout.getChildAt(j);
                                        if (j == finalI) {
                                            textView1.setBackgroundResource(R.drawable.text_line);
                                            textView1.setTextColor(getResources().getColor(R.color.colorPrimary));
                                        } else {
                                            textView1.setBackgroundResource(R.drawable.text_no_line);
                                            textView1.setTextColor(Color.BLACK);
                                        }
                                    }
                                    setNewType(textView.getText().toString());
                                }
                            });
                            newTypeLayout.addView(textView);
                        }
                        setNewType(netType.get(0));
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();

    }

    List<NewList> newLists;

    private void setNewType(String toString) {
        List<NewList> newListListM = new ArrayList<>();
        for (int i = 0; i < newLists.size(); i++) {
            NewList newList = newLists.get(i);
            if (newList.getNewsType().equals(toString)) {
                newListListM.add(newList);
            }
        }
        newList.setAdapter(new NewListAdapter(getActivity(), newListListM));

    }


    private void setVolley_Subject() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getAllSubject")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        String arr = jsonObject.optString(Util.Rows).replace("[", "").replace("]", "");
                        String[] arrs = arr.split(",");
                        final List<String> list = new ArrayList<>();
                        list.addAll(Arrays.asList(arrs));
                        subjectGird.setAdapter(new SubjectAdapter(getActivity(), list));
                        subjectGird.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(getActivity(), EmptyActivity.class);
                                intent.putExtra("info", list.get(position));
                                startActivity(intent);
                            }
                        });
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    List<ServiceOrder> serviceOrders;

    private void setVolley_Service() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getServiceInOrder")
                .setJsonObject("order", "2")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        serviceOrders = new Gson().fromJson(jsonObject.optJSONArray(Util.Rows).toString()
                                , new TypeToken<List<ServiceOrder>>() {
                                }.getType());
                        ServiceAdapter adapter = new ServiceAdapter(getActivity(), serviceOrders);
                        serviceGird.setAdapter(adapter);
                        adapter.setOnClickItem(new OnClickItem() {
                            @Override
                            public void onClick(int position, String name) {
                                if (name.equals("活动")) {
                                    appHomeActivity.switchFragment(appHomeActivity.map.get("活动"));
                                }else if (name.equals("智慧巴士")){
                                    appHomeActivity.switchFragment(appHomeActivity.map.get("智慧巴士"));
                                }else if (name.equals("违章查询")){
                                    appHomeActivity.switchFragment(appHomeActivity.map.get("违章查询"));
                                }

                            }
                        });
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setVolley_Image() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getImages")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        homeImages = new Gson().fromJson(jsonObject.optJSONArray(Util.Rows).toString()
                                , new TypeToken<List<HomeImage>>() {
                                }.getType());
                        for (int i = 0; i < homeImages.size(); i++) {
                            HomeImage homeImage = homeImages.get(i);
                            MyImageView imageView = new MyImageView(getActivity());
                            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                            imageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                            imageView.setMyUrl(homeImage.getPath());
                            imageView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(getActivity(), EmptyActivity.class);
                                    intent.putExtra("info", "新闻");
                                    startActivity(intent);
                                }
                            });
                            viewFlipper.addView(imageView);
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void initView() {
        viewFlipper = getView().findViewById(R.id.view_flipper);
        serviceGird = getView().findViewById(R.id.service_gird);
        subjectGird = getView().findViewById(R.id.subject_gird);
        newTypeLayout = getView().findViewById(R.id.new_type_layout);
        newList = getView().findViewById(R.id.new_list);
    }
}
