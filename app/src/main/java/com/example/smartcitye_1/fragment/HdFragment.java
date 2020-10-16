package com.example.smartcitye_1.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.android.volley.VolleyError;
import com.example.smartcitye_1.R;
import com.example.smartcitye_1.activity.AppHomeActivity;
import com.example.smartcitye_1.activity.HdDetailsActivity;
import com.example.smartcitye_1.adapter.HdADapter;
import com.example.smartcitye_1.bean.HdIamge;
import com.example.smartcitye_1.bean.HdInfo;
import com.example.smartcitye_1.bean.HdList;
import com.example.smartcitye_1.net.VolleyLo;
import com.example.smartcitye_1.net.VolleyTo;
import com.example.smartcitye_1.util.MyImageView;
import com.example.smartcitye_1.util.MyListView;
import com.example.smartcitye_1.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/15 at 15:54
 */
public class HdFragment extends Fragment {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private ViewFlipper viewFlipper;
    private LinearLayout newTypeLayout;
    private ListView hdList;

    public HdFragment() {

    }

    private AppHomeActivity appHomeActivity;

    public HdFragment(AppHomeActivity appHomeActivity) {
        this.appHomeActivity = appHomeActivity;
    }

    public static HdFragment newInstance(AppHomeActivity appHomeActivity) {
        return new HdFragment(appHomeActivity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.hd_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        title.setText("活动");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appHomeActivity.backOnClick();
            }
        });


    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            setView();
            Log.i("aaa", "onHiddenChanged: ");
        }
        super.onHiddenChanged(hidden);

    }


    @Override
    public void onResume() {
        super.onResume();
        Log.i("aaa", "onResume: ");
        setView();

    }

    List<HdList> hdLists;

    private void setView() {
        setVolley_Image();
        newTypeLayout.removeAllViews();
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getAllActionType")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        hdLists = new Gson().fromJson(jsonObject.optJSONArray(Util.Rows).toString()
                                , new TypeToken<List<HdList>>() {
                                }.getType());
                        for (int i = 0; i < hdLists.size(); i++) {
                            final TextView textView = new TextView(getActivity());
                            textView.setText(hdLists.get(i).getTypename());
                            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                            layoutParams.setMargins(20, 0, 20, 0);
                            textView.setTextSize(20);
                            textView.setLayoutParams(layoutParams);
                            textView.setTextColor(Color.BLACK);
                            textView.setTag(hdLists.get(i).getId());
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
                                    setNewType(textView.getTag().toString());
                                }
                            });
                            newTypeLayout.addView(textView);
                        }
                        setNewType(hdLists.get(0).getId() + "");
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();

    }

    List<HdIamge> hdIamges;

    private void setVolley_Image() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getActionImages")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        hdIamges = new Gson().fromJson(jsonObject.optJSONArray(Util.Rows).toString()
                                , new TypeToken<List<HdIamge>>() {
                                }.getType());
                        for (int i = 0; i < hdIamges.size(); i++) {
                            MyImageView imageView = new MyImageView(getActivity());
                            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                            imageView.setMyUrl(hdIamges.get(i).getImage());
                            final int finalI = i;
                            imageView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    VolleyTo volleyTo1 = new VolleyTo();
                                    volleyTo1.setUrl("getActionById")
                                            .setJsonObject("id", hdIamges.get(finalI).getId())
                                            .setVolleyLo(new VolleyLo() {
                                                @Override
                                                public void onResponse(JSONObject jsonObject) {
                                                    HdInfo hdInfo = new Gson().fromJson(jsonObject.optJSONArray(Util.Rows).optJSONObject(0).toString()
                                                            , HdInfo.class);
                                                    Intent intent = new Intent(getActivity(), HdDetailsActivity.class);
                                                    intent.putExtra("info", hdInfo);
                                                    startActivity(intent);
                                                }

                                                @Override
                                                public void onErrorResponse(VolleyError volleyError) {

                                                }
                                            }).start();
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

    List<HdInfo> hdInfos;

    private void setNewType(String toString) {
        VolleyTo volleyTo
                = new VolleyTo();
        volleyTo.setUrl("getActionsByType")
                .setDialog(getActivity())
                .setJsonObject("typeid", toString)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        hdInfos = new Gson().fromJson(jsonObject.optJSONArray(Util.Rows).toString()
                                , new TypeToken<List<HdInfo>>() {
                                }.getType());
                        Collections.sort(hdInfos, new Comparator<HdInfo>() {
                            @Override
                            public int compare(HdInfo o1, HdInfo o2) {
                                Date date = null, date1 = null;
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                try {
                                    date = format.parse(o1.getTime());
                                    date1 = format.parse(o2.getTime());
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                return date1.compareTo(date);
                            }
                        });
                        hdList.setAdapter(new HdADapter(getActivity(), hdInfos));
                        hdList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(getActivity(), HdDetailsActivity.class);
                                intent.putExtra("info", hdInfos.get(position));
                                startActivity(intent);
                            }
                        });

                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();

    }

    private void initView() {
        itemChange = getView().findViewById(R.id.item_change);
        title = getView().findViewById(R.id.title);
        title1 = getView().findViewById(R.id.title1);
        viewFlipper = getView().findViewById(R.id.view_flipper);
        newTypeLayout = getView().findViewById(R.id.new_type_layout);
        hdList = getView().findViewById(R.id.hd_list);
    }
}
