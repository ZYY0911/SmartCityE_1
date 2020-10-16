package com.example.smartcitye_1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.smartcitye_1.R;
import com.example.smartcitye_1.bean.Sqtb;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/16 at 9:09
 */
public class SqtbAdapter extends ArrayAdapter<Sqtb> {

    public SqtbAdapter(@NonNull Context context, @NonNull List<Sqtb> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.tb_item, parent, false);
        TextView itemTitle = convertView.findViewById(R.id.item_title);
        TextView itemContext = convertView.findViewById(R.id.item_context);
        TextView itemMsg = convertView.findViewById(R.id.item_msg);
        Sqtb sqtb = getItem(position);
        itemTitle.setText(sqtb.getTitle());
        itemContext.setText(sqtb.getCotent());
        itemMsg.setText("日期：" + sqtb.getTime() + " 发布人" + sqtb.getPeople());
        return convertView;
    }

    private void initView() {


    }
}
