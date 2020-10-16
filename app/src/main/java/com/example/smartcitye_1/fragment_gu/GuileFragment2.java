package com.example.smartcitye_1.fragment_gu;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.smartcitye_1.R;
import com.example.smartcitye_1.activity.AppHomeActivity;
import com.example.smartcitye_1.dialog.NetDialog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/14 at 17:41
 */
public class GuileFragment2 extends Fragment {
    private ImageView itemImage;
    private int layout;
    private Button btMain;
    private Button btSetting;

    public GuileFragment2(int layout) {
        this.layout = layout;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.guild_fragment2, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        itemImage.setImageResource(layout);
        btMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AppHomeActivity.class));
                getActivity().finish();

            }
        });
        btSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetDialog dialog = new NetDialog();
                dialog.show(getChildFragmentManager(), "");

            }
        });

    }

    private void initView() {
        itemImage = getView().findViewById(R.id.item_image);
        btMain = getView().findViewById(R.id.bt_main);
        btSetting = getView().findViewById(R.id.bt_setting);
    }
}
