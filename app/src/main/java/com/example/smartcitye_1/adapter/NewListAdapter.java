package com.example.smartcitye_1.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcitye_1.R;
import com.example.smartcitye_1.activity.EmptyActivity;
import com.example.smartcitye_1.bean.NewComment;
import com.example.smartcitye_1.bean.NewList;
import com.example.smartcitye_1.net.VolleyLo;
import com.example.smartcitye_1.net.VolleyTo;
import com.example.smartcitye_1.util.MyImageView;
import com.example.smartcitye_1.util.Util;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.sql.NClob;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/15 at 8:28
 */
public class NewListAdapter extends ArrayAdapter<NewList> {

    public NewListAdapter(@NonNull Context context, @NonNull List<NewList> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder holder;
//        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.news_item, parent, false);
            holder = new ViewHolder();
            holder.itemImage = convertView.findViewById(R.id.item_image);
            holder.itemTitle = convertView.findViewById(R.id.item_title);
            holder.itemContext = convertView.findViewById(R.id.item_context);
            holder.itemMsg = convertView.findViewById(R.id.item_msg);
            convertView.setTag(holder);
//        } else {
//            holder = (ViewHolder) convertView.getTag();
//        }
        final NewList newList = getItem(position);
        holder.itemTitle.setText(newList.getTitle());
        holder.itemContext.setText(newList.getAbstractX());
        holder.itemImage.setMyUrl(newList.getPicture());
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getNewsInfoById")
                .setJsonObject("newsid", newList.getNewsid())
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        NewComment newComment = new Gson().fromJson(jsonObject.optJSONArray(Util.Rows).optJSONObject(0).toString()
                                , NewComment.class);
                        holder.itemMsg.setText("评论：" + newComment.getPraiseCount() + "  时间：" + newComment.getPublicTime());

                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EmptyActivity.class);
                intent.putExtra("info", newList.getTitle());
                getContext().startActivity(intent);
            }
        });
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
