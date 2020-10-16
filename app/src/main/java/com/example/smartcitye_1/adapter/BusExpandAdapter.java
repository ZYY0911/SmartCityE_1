package com.example.smartcitye_1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcitye_1.R;
import com.example.smartcitye_1.bean.BusDetails;
import com.example.smartcitye_1.bean.BusTitle;
import com.example.smartcitye_1.util.OnClickItem;

import java.util.List;
import java.util.Map;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/15 at 17:14
 */
public class BusExpandAdapter extends BaseExpandableListAdapter {
    private Map<BusTitle, List<BusDetails>> map;
    private List<BusTitle> busTitles;

    public BusExpandAdapter(Map<BusTitle, List<BusDetails>> map, List<BusTitle> busTitles) {
        this.map = map;
        this.busTitles = busTitles;
    }

    @Override
    public int getGroupCount() {
        return busTitles.size();
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

    private OnClickItem onClickItem;

    public void setOnClickItem(OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolderFu holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.bus_item_fu, parent, false);
            holder = new ViewHolderFu();
            holder.itemName = convertView.findViewById(R.id.item_name);
            holder.itemLine = convertView.findViewById(R.id.item_line);
            holder.itemMsg = convertView.findViewById(R.id.item_msg);
            holder.itemStart = convertView.findViewById(R.id.item_start);
            holder.itemEnd = convertView.findViewById(R.id.item_end);
            holder.itemIv = convertView.findViewById(R.id.item_iv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolderFu) convertView.getTag();
        }
        BusTitle busTitle = busTitles.get(groupPosition);
        holder.itemName.setText(busTitle.getPathName());
        holder.itemLine.setText(busTitle.getStartSite() + "--" + busTitle.getEndSite());
        holder.itemMsg.setText("票价：￥" + busTitle.getPrice() + ".0    里程：" + busTitle.getMileage() + ".0km");
        holder.itemStart.setText(busTitle.getRunTime1());
        holder.itemEnd.setText(busTitle.getRunTime2());
        if (isExpanded) {
            holder.itemIv.setImageResource(R.mipmap.xiajiantou);
        } else {
            holder.itemIv.setImageResource(R.mipmap.youjiantou);
        }
        holder.itemName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItem.onClick(groupPosition, "");
            }
        });
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.bus_item_zi, parent, false);
        TextView itemName = convertView.findViewById(R.id.item_name);
        List<BusDetails> busDetails = map.get(busTitles.get(groupPosition));
        String str = "";
        for (int i = 0; i < busDetails.size(); i++) {
            if (i == 0) {
                str = busDetails.get(i).getSiteName();
            } else {
                str += "\r\n" + busDetails.get(i).getSiteName();
            }
        }
        itemName.setText(str);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    static class ViewHolderFu {

        private TextView itemName;
        private TextView itemLine;
        private TextView itemMsg;
        private TextView itemStart;
        private TextView itemEnd;
        private ImageView itemIv;
    }

    private void initView() {
    }
}
