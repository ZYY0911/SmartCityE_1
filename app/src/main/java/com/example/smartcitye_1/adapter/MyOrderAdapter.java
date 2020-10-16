package com.example.smartcitye_1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcitye_1.R;
import com.example.smartcitye_1.bean.BusOrder;
import com.example.smartcitye_1.net.VolleyLo;
import com.example.smartcitye_1.net.VolleyTo;
import com.example.smartcitye_1.util.Util;

import org.json.JSONObject;

import java.util.List;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/15 at 19:58
 */
public class MyOrderAdapter extends BaseExpandableListAdapter {
    private List<BusOrder> busOrders;

    public MyOrderAdapter(List<BusOrder> busOrders) {
        this.busOrders = busOrders;
    }

    @Override
    public int getGroupCount() {
        return busOrders.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_order_item_fu, parent, false);
            holder = new ViewHolder();
            holder.itemNum = convertView.findViewById(R.id.item_num);
            holder.itemName = convertView.findViewById(R.id.item_name_bus);
            holder.itemLine = convertView.findViewById(R.id.item_line);
            holder.itemMsg = convertView.findViewById(R.id.item_msg);
            holder.itemIv = convertView.findViewById(R.id.item_iv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        BusOrder busOrder = busOrders.get(groupPosition);

        holder.itemLine.setText(busOrder.getUpsite() + "-" + busOrder.getDownsite());
        holder.itemNum.setText("订单编号：" + busOrder.getId());
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("busListById")
                .setJsonObject("busid", busOrder.getBusid())
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONObject jsonObject1 = jsonObject.optJSONArray(Util.Rows).optJSONObject(0);
                        holder.itemName.setText(jsonObject1.optString("pathName"));
//                        holder.itemLine.setText(jsonObject1.optString("startSite") + "-" +
//                                jsonObject1.optString("endSite"));
                        holder.itemMsg.setText("票价：" + jsonObject1.optString("price") + "元");
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
        if (isExpanded) {
            holder.itemIv.setImageResource(R.mipmap.xiajiantou);
        } else {
            holder.itemIv.setImageResource(R.mipmap.youjiantou);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_order_item_zi, parent, false);

        TextView itemName = convertView.findViewById(R.id.item_name);

        BusOrder myOrder = busOrders.get(groupPosition);
        itemName.setText(myOrder.getTravetime().replace(",", "\r\n"));


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    static class ViewHolder {

        private TextView itemNum;
        private TextView itemName;
        private TextView itemLine;
        private TextView itemMsg;
        private ImageView itemIv;
    }

    private void initView() {


    }
}
