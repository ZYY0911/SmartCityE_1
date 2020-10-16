package com.example.smartcitye_1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.smartcitye_1.R;
import com.example.smartcitye_1.adapter.KjglAdapter;
import com.example.smartcitye_1.bean.Kjgl;
import com.example.smartcitye_1.util.OnClickItem;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/16 at 8:44
 */
public class KjglActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private ListView wlListView;
    List<Kjgl> kjgls;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kjgl_layout);
        initView();
        title.setText("快件管理");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        kjgls = new ArrayList<>();
        kjgls.add(new Kjgl("顺风快递", "2020-10-1", "张三", "231564564894561"));
        kjgls.add(new Kjgl("韵达快递", "2020-10-5", "张三", "2145484612323123"));
        kjgls.add(new Kjgl("中通快递", "2020-10-4", "王五", "784894561864561"));
        kjgls.add(new Kjgl("EMS", "2020-10-8", "李四", "7881212389789"));

        KjglAdapter adapter = new KjglAdapter(this, kjgls);
        wlListView.setAdapter(adapter);
        adapter.setOnClickItem(new OnClickItem() {
            @Override
            public void onClick(int position, String name) {
                Intent intent = new Intent(KjglActivity.this, KjflImageActivity.class);
                intent.putExtra("info", name);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        wlListView = findViewById(R.id.wl_list_view);
    }
}
