package com.example.smartcitye_1.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcitye_1.R;
import com.example.smartcitye_1.activity.AppHomeActivity;
import com.example.smartcitye_1.activity.CarInfoAcvitiy;
import com.example.smartcitye_1.bean.CarPlace;
import com.example.smartcitye_1.net.VolleyLo;
import com.example.smartcitye_1.net.VolleyTo;
import com.example.smartcitye_1.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/15 at 17:27
 */
public class WzcxFragment extends Fragment {
    private Spinner carType;
    private Spinner carNum;
    private EditText etNum;
    private Button btQuery;
    private ImageView itemChange;
    private TextView title;
    private TextView title1;

    public WzcxFragment() {

    }

    private AppHomeActivity appHomeActivity;

    public WzcxFragment(AppHomeActivity appHomeActivity) {
        this.appHomeActivity = appHomeActivity;
    }

    public static WzcxFragment newInstance(AppHomeActivity appHomeActivity) {
        return new WzcxFragment(appHomeActivity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.wzcx_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        title.setText("违章查询");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appHomeActivity.backOnClick();
            }
        });
        btQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cp = carNum.getSelectedItem().toString() + etNum.getText().toString().toUpperCase();
                if (cp.length() == 7) {
                    Intent intent = new Intent(getActivity(), CarInfoAcvitiy.class);
                    intent.putExtra("info", cp);
                    startActivity(intent);

                } else {
                    Util.showDialog("车牌号不正确", appHomeActivity);
                    return;
                }
            }
        });
        setShow();

    }


    @Override
    public void onResume() {
        super.onResume();
        etNum.setText("");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            etNum.setText("");
            setShow();
        }
        super.onHiddenChanged(hidden);
    }

    private void setShow() {
        setVolley_Place();
        setVolley_Type();
    }

    List<CarPlace> carTypes;
    List<String> strings1;

    private void setVolley_Type() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getAllCarType")
                .setDialog(getActivity())
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        carTypes = new Gson().fromJson(jsonObject.optJSONArray(Util.Rows).toString()
                                , new TypeToken<List<CarPlace>>() {
                                }.getType());
                        strings1 = new ArrayList<>();
                        for (int i = 0; i < carTypes.size(); i++) {
                            strings1.add(carTypes.get(i).getName());
                        }
                        carType.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, strings1));

                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    List<CarPlace> carPlaces;

    List<String> strings;

    private void setVolley_Place() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getCarPlace")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        carPlaces = new Gson().fromJson(jsonObject.optJSONArray(Util.Rows).toString()
                                , new TypeToken<List<CarPlace>>() {
                                }.getType());
                        strings = new ArrayList<>();
                        for (int i = 0; i < carPlaces.size(); i++) {
                            strings.add(carPlaces.get(i).getName());
                        }
                        carNum.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, strings));
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void initView() {
        carType = getView().findViewById(R.id.car_type);
        carNum = getView().findViewById(R.id.car_num);
        etNum = getView().findViewById(R.id.et_num);
        btQuery = getView().findViewById(R.id.bt_query);
        itemChange = getView().findViewById(R.id.item_change);
        title = getView().findViewById(R.id.title);
        title1 = getView().findViewById(R.id.title1);
    }
}

