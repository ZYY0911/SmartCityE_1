package com.example.smartcitye_1.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.smartcitye_1.R;
import com.example.smartcitye_1.bean.HdInfo;
import com.example.smartcitye_1.bean.HdList;
import com.example.smartcitye_1.bean.HomeImage;
import com.example.smartcitye_1.util.MyImageView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/15 at 16:02
 */
public class HdADapter extends ArrayAdapter<HdInfo> {

    public HdADapter(@NonNull Context context, @NonNull List<HdInfo> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.news_item, parent, false);
            holder = new ViewHolder();
            holder.itemImage = convertView.findViewById(R.id.item_image);
            holder.itemTitle = convertView.findViewById(R.id.item_title);
            holder.itemContext = convertView.findViewById(R.id.item_context);
            holder.itemMsg = convertView.findViewById(R.id.item_msg);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        HdInfo hdInfo = getItem(position);
        holder.itemImage.setMyUrl(hdInfo.getImage());
        holder.itemContext.setText("报名人数：" + hdInfo.getCount() + "\n" + "点赞人数：" + hdInfo.getPraiseCount());
        holder.itemTitle.setText(hdInfo.getName());
        holder.itemMsg.setGravity(Gravity.RIGHT);
        holder.itemMsg.setText("时间：" +
                hdInfo.getTime());

        return convertView;
    }

    static class ViewHolder {

        private MyImageView itemImage;
        private TextView itemTitle;
        private TextView itemContext;
        private TextView itemMsg;
    }

    private void initView() {
    }
}
