package com.example.smartcitye_1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.smartcitye_1.R;
import com.example.smartcitye_1.bean.CarWzInfos;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/15 at 17:41
 */
public class CarInfoAdapter extends ArrayAdapter<CarWzInfos> {

    public CarInfoAdapter(@NonNull Context context, @NonNull List<CarWzInfos> objects, int count) {
        super(context, 0, objects);
        this.count = count;
    }

    private int count;

    @Override
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.wzcx_item, parent, false);
            holder = new ViewHolder();
            holder.itemCp = convertView.findViewById(R.id.item_cp);
            holder.itemZt = convertView.findViewById(R.id.item_zt);
            holder.itemSj = convertView.findViewById(R.id.item_sj);
            holder.itemJe = convertView.findViewById(R.id.item_je);
            holder.itemKf = convertView.findViewById(R.id.item_kf);
            holder.itemDd = convertView.findViewById(R.id.item_dd);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        CarWzInfos carPeal = getItem(position);
        holder.itemCp.setText("车牌号：" + carPeal.getCarid());
        holder.itemJe.setText("罚款金额：" + carPeal.getCost() + "元");
        holder.itemKf.setText("违章记分：" + carPeal.getDeductPoints() + "分");
        holder.itemZt.setText("处理状态：" + (carPeal.getStatus() == 0 ? "未处理" : "处理中"));
        holder.itemSj.setText("违章时间：" + carPeal.getTime());
        holder.itemDd.setText("违章地点：" + carPeal.getPlace());
        return convertView;
    }

    static class ViewHolder {

        private TextView itemCp;
        private TextView itemZt;
        private TextView itemSj;
        private TextView itemJe;
        private TextView itemKf;
        private TextView itemDd;
    }

    private void initView() {
    }
}
