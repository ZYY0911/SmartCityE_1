package com.example.smartcitye_1.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smartcitye_1.R;
import com.example.smartcitye_1.bean.RlBeasn;
import com.example.smartcitye_1.util.OnClickItem;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/15 at 19:13
 */
public class RlAdapter extends ArrayAdapter<RlBeasn> {

    public RlAdapter(@NonNull Context context, @NonNull List<RlBeasn> objects) {
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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.rl_item, parent, false);
            holder = new ViewHolder();
            holder.bgColor = convertView.findViewById(R.id.bg_color);
            holder.itemMsg = convertView.findViewById(R.id.item_msg);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final RlBeasn rlBeasn = getItem(position);
        if (rlBeasn.getBg() == 3) {
            holder.bgColor.setBackgroundResource(R.drawable.rl_1_3);
            holder.itemMsg.setText("");
        } else {
            holder.itemMsg.setText(rlBeasn.getDay() + "");
            holder.bgColor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickItem.onClick(position, rlBeasn.getBg() + "");
                }
            });
            holder.bgColor.setBackgroundResource(rlBeasn.getBg() == 0 ? R.drawable.rl_0 : rlBeasn.getBg() == 1 ? R.drawable.rl_1_3 : R.drawable.rl_2);
        }
        return convertView;
    }

    static class ViewHolder {

        private LinearLayout bgColor;
        private TextView itemMsg;
    }

    private void initView() {
    }
}
