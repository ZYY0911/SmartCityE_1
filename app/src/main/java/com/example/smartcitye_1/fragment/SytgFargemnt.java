package com.example.smartcitye_1.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcitye_1.R;
import com.example.smartcitye_1.activity.SytgDetailsActivity;
import com.example.smartcitye_1.bean.Sytg;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/16 at 9:35
 */
public class SytgFargemnt extends Fragment {
    private ImageView itemImag;
    private TextView itemTitle;
    private TextView itemMsg;

    Sytg sytg;

    public SytgFargemnt(Sytg sytg) {
        this.sytg = sytg;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sytg_item, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        itemTitle.setText(sytg.getTitle());
        itemMsg.setText(sytg.getMsg());
        itemImag.setImageResource(sytg.getImage());
//        itemImag.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), SytgDetailsActivity.class);
//                intent.putExtra("info", sytg);
//                startActivity(intent);
//            }
//        });
        getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SytgDetailsActivity.class);
                intent.putExtra("info", sytg);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        itemImag = getView().findViewById(R.id.item_imag);
        itemTitle = getView().findViewById(R.id.item_title);
        itemMsg = getView().findViewById(R.id.item_msg);
    }
}
