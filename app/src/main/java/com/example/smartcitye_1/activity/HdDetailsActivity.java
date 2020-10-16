package com.example.smartcitye_1.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcitye_1.AppClient;
import com.example.smartcitye_1.R;
import com.example.smartcitye_1.adapter.HdTjAdapter;
import com.example.smartcitye_1.bean.HdInfo;
import com.example.smartcitye_1.dialog.PLDialog;
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
import java.util.Random;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/15 at 16:14
 */
public class HdDetailsActivity extends AppCompatActivity {
    private HdInfo hdInfo;
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private MyImageView ivImage;
    private TextView tVMsg;
    private MyListView tjHdView;
    private TextView tvPl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hd_details_layout);
        hdInfo = (HdInfo) getIntent().getSerializableExtra("info");
        initView();
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title.setText(hdInfo.getName());
        tVMsg.setText(hdInfo.getDescription());
        title1.setText("报名活动");
        ivImage.setMyUrl(hdInfo.getImage());
        setVolley();
        title1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HdDetailsActivity.this);
                builder.setTitle("提示");
                builder.setMessage("您确定要报名此活动吗？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        VolleyTo volleyTo = new VolleyTo();
                        volleyTo.setUrl("setActionSignUpCount")
                                .setJsonObject("id", hdInfo.getId())
                                .setJsonObject("userid", AppClient.getUserId(AppClient.username))
                                .setVolleyLo(new VolleyLo() {
                                    @Override
                                    public void onResponse(JSONObject jsonObject) {
                                        if (jsonObject.optString("RESULT").equals("S")) {
                                            Util.showToast("报名成功", HdDetailsActivity.this);
                                        } else {
                                            Util.showToast("报名失败", HdDetailsActivity.this);
                                        }

                                    }

                                    @Override
                                    public void onErrorResponse(VolleyError volleyError) {
                                        Util.showToast("报名失败", HdDetailsActivity.this);
                                    }
                                }).start();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();

            }
        });

        tvPl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PLDialog plDialog = new PLDialog(hdInfo.getId(), getWindowManager().getDefaultDisplay().getWidth());
                plDialog.show(getSupportFragmentManager(), "");
            }
        });
    }

    List<HdInfo> hdInfos;

    private void setVolley() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getRecommandAction")
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
                        for (int i = 0; i < hdInfos.size(); i++) {
                            HdInfo hdInfoss = hdInfos.get(i);
                            if (hdInfoss.getId() == hdInfo.getId()) {
                                hdInfos.remove(i);
                            }
                        }
                        Random random = new Random();
                        tjHdView.setAdapter(new HdTjAdapter(HdDetailsActivity.this, hdInfos, random.nextInt(3) + 1));
                        tjHdView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(HdDetailsActivity.this, HdDetailsActivity.class);
                                intent.putExtra("info", hdInfos.get(position));
                                startActivity(intent);
                                finish();
                            }
                        });

                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).

                start();

    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        ivImage = findViewById(R.id.iv_image);
        tVMsg = findViewById(R.id.tV_msg);
        tjHdView = findViewById(R.id.tj_hd_view);
        tvPl = findViewById(R.id.tv_pl);
    }
}
