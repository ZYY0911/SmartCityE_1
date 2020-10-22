package com.example.smartcitye_1.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcitye_1.AppClient;
import com.example.smartcitye_1.R;
import com.example.smartcitye_1.activity.AppHomeActivity;
import com.example.smartcitye_1.activity.YjfkActivity;
import com.example.smartcitye_1.bean.UserInfo;
import com.example.smartcitye_1.net.VolleyLo;
import com.example.smartcitye_1.net.VolleyTo;
import com.example.smartcitye_1.util.MyImageView;
import com.example.smartcitye_1.util.Util;
import com.google.gson.Gson;

import org.json.JSONObject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/15 at 8:41
 */
public class MyCenterFragment extends Fragment {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private MyImageView ivPhoto;
    private TextView tvName;
    private LinearLayout layoutInfo;
    private LinearLayout layoutPwd;
    private LinearLayout layoutOrder;
    private LinearLayout layoutYjfk;
    private Button btSave;

    public MyCenterFragment() {

    }

    private AppHomeActivity appHomeActivity;

    public MyCenterFragment(AppHomeActivity appHomeActivity) {
        this.appHomeActivity = appHomeActivity;
    }

    public static MyCenterFragment newInstance(AppHomeActivity appHomeActivity) {
        return new MyCenterFragment(appHomeActivity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.my_center_framgent, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        title.setText("个人中心");
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AppHomeActivity.class));
                appHomeActivity.finish();
            }
        });

        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appHomeActivity.backOnClick();
            }
        });
        layoutInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appHomeActivity.switchFragment(appHomeActivity.map.get("个人信息"));
            }
        });
        layoutPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appHomeActivity.switchFragment(appHomeActivity.map.get("修改密码"));
            }
        });
        layoutOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appHomeActivity.switchFragment(appHomeActivity.map.get("订单列表"));
            }
        });
        layoutYjfk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),YjfkActivity.class);
                intent.putExtra("info",userId);
                startActivity(intent);
//                startActivity(new Intent(getActivity(), YjfkActivity.class));
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

    public String userId;
    public UserInfo userInfo;

    private void setVolley() {
        userId = AppClient.getUserId(AppClient.username);
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getUserInfo")
                .setDialog(getActivity())
                .setJsonObject("userid", userId)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        userInfo = new Gson().fromJson(jsonObject.optJSONArray(Util.Rows).optJSONObject(0).toString()
                                , UserInfo.class);
                        ivPhoto.setMyUrl(userInfo.getAvatar());
                        tvName.setText("昵称：" + userInfo.getName());
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
        ivPhoto = getView().findViewById(R.id.iv_photo);
        tvName = getView().findViewById(R.id.tv_name);
        layoutInfo = getView().findViewById(R.id.layout_info);
        layoutPwd = getView().findViewById(R.id.layout_pwd);
        layoutOrder = getView().findViewById(R.id.layout_order);
        layoutYjfk = getView().findViewById(R.id.layout_yjfk);
        btSave = getView().findViewById(R.id.bt_save);
    }
}
