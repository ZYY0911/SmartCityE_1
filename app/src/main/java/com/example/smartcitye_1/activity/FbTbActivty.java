package com.example.smartcitye_1.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcitye_1.AppClient;
import com.example.smartcitye_1.R;
import com.example.smartcitye_1.adapter.FbAdapter;
import com.example.smartcitye_1.bean.Sqtb;
import com.example.smartcitye_1.util.MyGirdView;
import com.example.smartcitye_1.util.OnClickItem;
import com.example.smartcitye_1.util.Util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/16 at 9:23
 */
public class FbTbActivty extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private EditText etMsg;
    private EditText etContent;
    private Button btSave;
    private MyGirdView photGirder;
    private List<Bitmap> bitmaps;
    private FbAdapter adapter;
    private AppClient appClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fbtb_layout);
        initView();
        appClient = (AppClient) getApplication();
        title.setText("发表贴吧");
        bitmaps = new ArrayList<>();
        bitmaps.add(null);
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sqtb sqtb = new Sqtb(etMsg.getText().toString()
                        , etContent.getText().toString()
                        , Util.simpleDate("yyyy-MM-dd", new Date())
                        , "张三");
                sqtb.setBitmaps(bitmaps);
                List<Sqtb> sqtbs = appClient.getSqtbs();
                sqtbs.add(sqtb);
                Intent intent = new Intent();
//                intent.putExtra("info", sqtb);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        adapter = new FbAdapter(getApplicationContext(), bitmaps);
        photGirder.setAdapter(adapter);
        adapter.setOnClickItem(new OnClickItem() {
            @Override
            public void onClick(int position, String name) {
                Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //系统常量， 启动相机的关键
                startActivityForResult(openCameraIntent, 1); // 参数常量为自定义的request code, 在取返回结果时有用

            }
        });
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    Bitmap bm = (Bitmap) data.getExtras().get("data");
                    bitmaps.add(0, bm);
                    adapter.notifyDataSetChanged();
                }
                break;
        }
    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        etMsg = findViewById(R.id.et_msg);
        etContent = findViewById(R.id.et_content);
        btSave = findViewById(R.id.bt_save);
        photGirder = findViewById(R.id.phot_girder);
    }
}
