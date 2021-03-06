package com.example.smartcitye_1.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcitye_1.AppClient;
import com.example.smartcitye_1.R;
import com.example.smartcitye_1.adapter.HdPlAdapter;
import com.example.smartcitye_1.bean.HdPl;
import com.example.smartcitye_1.net.VolleyLo;
import com.example.smartcitye_1.net.VolleyTo;
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
import androidx.fragment.app.DialogFragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/15 at 16:34
 */
public class PLDialog extends DialogFragment {
    private ImageView ivClose;
    private ListView plList;
    private EditText etPl;
    private ImageView ivFb;

    private int id;
    private TextView tvTitlePl;
    private RelativeLayout layoutWith;

    public PLDialog(int id, int width) {
        this.id = id;
        this.width = width;
    }

    private int width;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setGravity(Gravity.BOTTOM);
        View view = inflater.inflate(R.layout.pl_dialog, container, false);
        layoutWith = view.findViewById(R.id.layout_with);
        layoutWith.setLayoutParams(new LinearLayout.LayoutParams(width, dp2px(getContext(),40)));
        return view;

    }




    private int dp2px(Context context, int value) {
        float m = context.getResources().getDisplayMetrics().density;
        return (int) (value * m + 0.5f);
    }

    List<HdPl> hdPls;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        setVolley();
        ivFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VolleyTo volleyTo = new VolleyTo();
                volleyTo.setUrl("publicActionCommit")
                        //{"id":"2","userid":"4","commitTime":"2020-10-3 10:00:00","commitContent":"666"}
                        .setJsonObject("id", id)
                        .setJsonObject("userid", AppClient.getUserId(AppClient.username))
                        .setJsonObject("commitTime", Util.simpleDate("yyyy-MM-dd HH:mm:ss", new Date()))
                        .setJsonObject("commitContent", etPl.getText().toString())
                        .setVolleyLo(new VolleyLo() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                if (jsonObject.optString("RESULT").equals("S")) {
                                    Util.showToast("评论成功", getContext());
                                    setVolley();
                                } else {
                                    Util.showToast("评论失败", getContext());
                                }
                                etPl.setText("");
                            }

                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                Util.showToast("评论失败", getContext());
                                etPl.setText("");
                            }
                        }).start();
            }
        });

    }

    private void setVolley() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getActionCommitById")
                .setJsonObject("id", id)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        hdPls = new Gson().fromJson(jsonObject.optJSONArray(Util.Rows).toString()
                                , new TypeToken<List<HdPl>>() {
                                }.getType());
                        Collections.sort(hdPls, new Comparator<HdPl>() {
                            @Override
                            public int compare(HdPl o1, HdPl o2) {
                                Date date = null, date1 = null;
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                try {
                                    date = format.parse(o1.getCommitTime());
                                    date1 = format.parse(o2.getCommitTime());
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                return date1.compareTo(date);
                            }
                        });
                        tvTitlePl.setText("评论(" + hdPls.size() + ")");
                        plList.setAdapter(new HdPlAdapter(getActivity(), hdPls));
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }


    private void initView() {
        ivClose = getView().findViewById(R.id.iv_close);
        plList = getView().findViewById(R.id.pl_list);
        etPl = getView().findViewById(R.id.et_pl);
        ivFb = getView().findViewById(R.id.iv_fb);
        tvTitlePl = getView().findViewById(R.id.tv_title_pl);
    }
}
