package com.example.smartcitye_1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.smartcitye_1.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/15 at 8:14
 */
public class SubjectAdapter extends ArrayAdapter<String> {


    public SubjectAdapter(@NonNull Context context, @NonNull List<String> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.subject_item, parent, false);
            holder = new ViewHolder();
            holder.itemTitle = convertView.findViewById(R.id.item_title);
            holder.itemName = convertView.findViewById(R.id.item_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.itemTitle.setText(getItem(position).trim().substring(0, 1));
        holder.itemName.setText(getItem(position).trim());
        return convertView;
    }

    static class ViewHolder {

        private TextView itemTitle;
        private TextView itemName;
    }

    private void initView() {
    }
}
