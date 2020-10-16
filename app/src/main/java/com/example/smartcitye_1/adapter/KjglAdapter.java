package com.example.smartcitye_1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.smartcitye_1.R;
import com.example.smartcitye_1.bean.Kjgl;
import com.example.smartcitye_1.util.MyImageView;
import com.example.smartcitye_1.util.OnClickItem;
import com.example.smartcitye_1.util.Util;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/16 at 8:49
 */
public class KjglAdapter extends ArrayAdapter<Kjgl> {

    public KjglAdapter(@NonNull Context context, @NonNull List<Kjgl> objects) {
        super(context, 0, objects);
    }

    private OnClickItem onClickItem;

    public void setOnClickItem(OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.kd_item, parent, false);
            holder = new ViewHolder();
            holder.itemTitle = convertView.findViewById(R.id.item_title);
            holder.itemContext = convertView.findViewById(R.id.item_context);
            holder.itemMsg = convertView.findViewById(R.id.item_msg);
            holder.itemImage = convertView.findViewById(R.id.item_image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Kjgl kjgl = getItem(position);
        holder.itemTitle.setText("" + kjgl.getName());
        holder.itemContext.setText("日期：" + kjgl.getTime() + "\n收件人：" + kjgl.getPeople());
        holder.itemMsg.setText("单号：" + kjgl.getNum());
        final String msg = "快递名称:" + kjgl.getName() + "日期：" + kjgl.getTime() + "收件人：" + kjgl.getPeople() + "单号：" + kjgl.getNum();
        holder.itemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItem.onClick(position, msg);
            }
        });
        holder.itemImage.setImageBitmap(Util.getBitMat(msg));

        return convertView;
    }


    static class ViewHolder {

        private TextView itemTitle;
        private TextView itemContext;
        private TextView itemMsg;
        private MyImageView itemImage;
    }

    private void initView() {

    }
}
