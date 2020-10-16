package com.example.smartcitye_1;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.smartcitye_1.bean.Sqtb;
import com.example.smartcitye_1.bean.UserNum;
import com.example.smartcitye_1.net.VolleyLo;
import com.example.smartcitye_1.net.VolleyTo;
import com.example.smartcitye_1.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/11 at 19:58
 */
public class AppClient extends Application {
    public static SharedPreferences sharedPreferences;
    public static final String IP = "IP";
    public static final String PORT = "port";
    private static RequestQueue requestQueue;
    public static final String username = "abc";
    public static final String IsFirst = "isFirst";
    private static List<AppCompatActivity> appCompatActivities = new ArrayList<>();
    private List<Sqtb> sqtbs = new ArrayList<>();

    public List<Sqtb> getSqtbs() {
        return sqtbs;
    }

    public static void add(AppCompatActivity appCompatActivity) {
        appCompatActivities.add(appCompatActivity);
    }

    public static void finAll() {
        for (int i = 0; i < appCompatActivities.size(); i++) {
            AppCompatActivity appCompatActivity = appCompatActivities.get(i);
            if (!appCompatActivity.isFinishing()) {
                appCompatActivity.finish();
            }
        }
        appCompatActivities.clear();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sharedPreferences = getSharedPreferences("aaa", Context.MODE_PRIVATE);
        requestQueue = Volley.newRequestQueue(this);
        sqtbs.add(new Sqtb("台媒：10月12日起搭两岸班机，需3天内核酸报告"
                , "蒙古国为支持中国抗击疫情捐赠的3万只羊又有新消息了。据介绍，目前，蒙古国政府从牧民手中收购的活羊已有2万余只，运到了东戈壁省扎门乌德县的活畜隔离免疫区，剩余的活羊收购工作还在加紧进行，3万只活羊有望在10月上旬全部进入免疫区。"
                , "2020-10-2", "张三"));
        sqtbs.add(new Sqtb("台媒：10月12日起搭两岸班机，需3天内核酸报告"
                , "蒙古国为支持中国抗击疫情捐赠的3万只羊又有新消息了。据介绍，目前，蒙古国政府从牧民手中收购的活羊已有2万余只，运到了东戈壁省扎门乌德县的活畜隔离免疫区，剩余的活羊收购工作还在加紧进行，3万只活羊有望在10月上旬全部进入免疫区。"
                , "2020-10-4", "张三"));
        sqtbs.add(new Sqtb("台媒：10月12日起搭两岸班机，需3天内核酸报告"
                , "蒙古国为支持中国抗击疫情捐赠的3万只羊又有新消息了。据介绍，目前，蒙古国政府从牧民手中收购的活羊已有2万余只，运到了东戈壁省扎门乌德县的活畜隔离免疫区，剩余的活羊收购工作还在加紧进行，3万只活羊有望在10月上旬全部进入免疫区。"
                , "2020-10-3", "张三"));
        setVolley();
    }

    private static List<UserNum> userNums;

    private void setVolley() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getUsers")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        userNums = new Gson().fromJson(jsonObject.optJSONArray(Util.Rows).toString()
                                , new TypeToken<List<UserNum>>() {
                                }.getType());

                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    public static String getUserId(String username) {
        for (int i = 0; i < userNums.size(); i++) {
            UserNum userNum = userNums.get(i);
            if (userNum.getUsername().equals(username)) {
                return userNum.getUserid();
            }
        }
        return "1";
    }


    public static void add(JsonObjectRequest jsonObjectRequest) {
        requestQueue.add(jsonObjectRequest);
    }

    public static void add(ImageRequest imageRequest) {
        requestQueue.add(imageRequest);
    }

}
