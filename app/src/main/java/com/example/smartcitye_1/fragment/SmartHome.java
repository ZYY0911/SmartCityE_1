package com.example.smartcitye_1.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.smartcitye_1.R;
import com.example.smartcitye_1.activity.AppHomeActivity;
import com.example.smartcitye_1.activity.CarManagerActivity;
import com.example.smartcitye_1.activity.KjglActivity;
import com.example.smartcitye_1.activity.SqdtdetailsActivity;
import com.example.smartcitye_1.activity.SqtbActivity;
import com.example.smartcitye_1.activity.SytgActivity;
import com.example.smartcitye_1.activity.WyfwActivity;
import com.example.smartcitye_1.bean.CarManager;
import com.example.smartcitye_1.bean.Smart_home;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/16 at 7:54
 */
public class SmartHome extends Fragment {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private ViewFlipper viewFlipper;
    private LinearLayout layoutWyfu;
    private LinearLayout layoutKjgl;
    private LinearLayout layoutYlsj;
    private LinearLayout layoutSytg;
    private LinearLayout layoutClgl;
    private LinearLayout layoutMore;

    public SmartHome() {
    }

    private AppHomeActivity appHomeActivity;

    public SmartHome(AppHomeActivity appHomeActivity) {
        this.appHomeActivity = appHomeActivity;
    }

    public static SmartHome newInstance(AppHomeActivity appHomeActivity) {
        return new SmartHome(appHomeActivity);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.smart_home, container, false);
    }

    List<Smart_home> smart_homes;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        title.setText("智慧社区");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appHomeActivity.backOnClick();
            }
        });
        smart_homes = new ArrayList<>();
        smart_homes.add(new Smart_home(R.mipmap.a, "2020-10-10"));
        smart_homes.add(new Smart_home(R.mipmap.b, "2020-10-12"));
        smart_homes.add(new Smart_home(R.mipmap.c, "2020-10-12"));
        smart_homes.add(new Smart_home(R.mipmap.d, "2020-10-16"));
        for (int i = 0; i < smart_homes.size(); i++) {
            final Smart_home smart_home = smart_homes.get(i);
            View view = LayoutInflater.from(getContext()).inflate(R.layout.sqdt_layout, null);
            ImageView itemImage = view.findViewById(R.id.item_image);
            TextView itemName = view.findViewById(R.id.item_name);
            itemImage.setImageResource(smart_home.getImage());
            itemName.setText("日期:" + smart_home.getMsg());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), SqdtdetailsActivity.class);
                    intent.putExtra("index", smart_home.getImage());
                    intent.putExtra("date", smart_home.getMsg());
                    startActivity(intent);
                }
            });
            viewFlipper.addView(view);
        }
        layoutWyfu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), WyfwActivity.class));
            }
        });
        layoutKjgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), KjglActivity.class));
            }
        });
        layoutYlsj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SqtbActivity.class));
            }
        });
        layoutSytg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SytgActivity.class));
            }
        });
        layoutClgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CarManagerActivity.class));

            }
        });
    }


    private void initView() {
        itemChange = getView().findViewById(R.id.item_change);
        title = getView().findViewById(R.id.title);
        title1 = getView().findViewById(R.id.title1);
        viewFlipper = getView().findViewById(R.id.view_flipper);
        layoutWyfu = getView().findViewById(R.id.layout_wyfu);
        layoutKjgl = getView().findViewById(R.id.layout_kjgl);
        layoutYlsj = getView().findViewById(R.id.layout_ylsj);
        layoutSytg = getView().findViewById(R.id.layout_sytg);
        layoutClgl = getView().findViewById(R.id.layout_clgl);
        layoutMore = getView().findViewById(R.id.layout_more);
    }
}

