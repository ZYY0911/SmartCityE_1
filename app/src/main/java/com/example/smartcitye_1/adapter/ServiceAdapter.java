package com.example.smartcitye_1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcitye_1.R;
import com.example.smartcitye_1.bean.ServiceInfo;
import com.example.smartcitye_1.bean.ServiceOrder;
import com.example.smartcitye_1.net.VolleyLo;
import com.example.smartcitye_1.net.VolleyTo;
import com.example.smartcitye_1.util.MyImageView;
import com.example.smartcitye_1.util.OnClickItem;
import com.example.smartcitye_1.util.Util;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/15 at 8:02
 */
public class ServiceAdapter extends ArrayAdapter<ServiceOrder> {

    public ServiceAdapter(@NonNull Context context, @NonNull List<ServiceOrder> objects) {
        super(context, 0, objects);
    }

    @Override
    public int getCount() {
        return 10;
    }

    private OnClickItem onClickItem;

    public void setOnClickItem(OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.service_item, parent, false);
            holder = new ViewHolder();
            holder.itemImage = convertView.findViewById(R.id.item_image);
            holder.itemName = convertView.findViewById(R.id.item_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (position == 9) {
            holder.itemName.setText("更多服务");
            holder.itemImage.setImageResource(R.mipmap.more_service);
        } else {
            ServiceOrder serviceOrder = getItem(position);
            VolleyTo volleyTo = new VolleyTo();
            volleyTo.setUrl("service_info")
                    .setJsonObject("serviceid", serviceOrder.getId())
                    .setVolleyLo(new VolleyLo() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
                            ServiceInfo serviceInfo = new Gson().fromJson(jsonObject.optJSONArray(Util.Rows).optJSONObject(0).toString()
                                    , ServiceInfo.class);
                            holder.itemName.setText(serviceInfo.getServiceName());
                            holder.itemImage.setMyUrl(serviceInfo.getIcon());
                        }

                        @Override
                        public void onErrorResponse(VolleyError volleyError) {

                        }
                    }).start();
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItem.onClick(position, holder.itemName.getText().toString());
            }
        });
        return convertView;
    }

    static class ViewHolder {

        private MyImageView itemImage;
        private TextView itemName;
    }

    private void initView() {

    }
}
