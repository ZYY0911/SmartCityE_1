package com.example.smartcitye_1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.smartcitye_1.AppClient;
import com.example.smartcitye_1.R;
import com.example.smartcitye_1.adapter.SqtbAdapter;
import com.example.smartcitye_1.bean.Sqtb;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/16 at 9:03
 */
public class SqtbActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private ListView liatView;
    private FloatingActionButton floatButton;
    List<Sqtb> sqtbList;
    SqtbAdapter adapter;
    private AppClient appClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqtb_layout);
        appClient = (AppClient) getApplication();
        initView();
        sqtbList = appClient.getSqtbs();
        title.setText("社区贴吧");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        adapter = new SqtbAdapter(SqtbActivity.this, sqtbList);
        liatView.setAdapter(adapter);
        liatView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SqtbActivity.this, TbDetalisActivity.class);
                intent.putExtra("info", position);
                startActivity(intent);
            }
        });
        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SqtbActivity.this, FbTbActivty.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
//                    Sqtb sqtb = (Sqtb) data.getSerializableExtra("info");
//                    sqtbList.add(0, sqtb);
                    adapter.notifyDataSetChanged();
                }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        liatView = findViewById(R.id.liat_view);
        floatButton = findViewById(R.id.float_button);
    }
}
