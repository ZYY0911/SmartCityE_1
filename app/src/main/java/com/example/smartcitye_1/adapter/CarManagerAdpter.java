package com.example.smartcitye_1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.smartcitye_1.R;
import com.example.smartcitye_1.bean.CarManager;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/16 at 9:55
 */
public class CarManagerAdpter extends ArrayAdapter<CarManager> {

    public CarManagerAdpter(@NonNull Context context, @NonNull List<CarManager> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.carmanage_item, parent, false);
            holder = new ViewHolder();
            holder.itemCp = convertView.findViewById(R.id.item_cp);
            holder.itemCw = convertView.findViewById(R.id.item_cw);
            holder.itemTckh = convertView.findViewById(R.id.item_tckh);
            holder.itemCz = convertView.findViewById(R.id.item_cz);
            holder.itemTel = convertView.findViewById(R.id.item_tel);
            holder.itemHome = convertView.findViewById(R.id.item_home);
            holder.itemAddress = convertView.findViewById(R.id.item_address);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        CarManager carManager = getItem(position);
        holder.itemCp.setText("车牌：" + carManager.getCp());
        holder.itemAddress.setText("地址：" + carManager.getAddress());
        holder.itemCw.setText("车位号：" + carManager.getCwh());
        holder.itemCz.setText("车主：" + carManager.getCz());
        holder.itemTckh.setText("停车卡号：" + carManager.getTch());
        holder.itemHome.setText("相关住户信息：" + carManager.getHome());
        holder.itemTel.setText("手机号：" + carManager.getTel());
        return convertView;
    }


    static class ViewHolder {

        private TextView itemCp;
        private TextView itemCw;
        private TextView itemTckh;
        private TextView itemCz;
        private TextView itemTel;
        private TextView itemHome;
        private TextView itemAddress;
    }

    private void initView() {
    }
}

