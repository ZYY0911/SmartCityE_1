package com.example.smartcitye_1.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcitye_1.AppClient;
import com.example.smartcitye_1.R;
import com.example.smartcitye_1.adapter.FbAdapter;
import com.example.smartcitye_1.adapter.FbAdapter2;
import com.example.smartcitye_1.bean.Sqtb;
import com.example.smartcitye_1.dialog.PLDialog2;
import com.example.smartcitye_1.util.MyGirdView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/16 at 9:11
 */
public class TbDetalisActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private TextView tvTitle;
    private TextView tvContext;
    private TextView tvMsg;
    private ImageView ivImage;
    private Sqtb sqtb;
    private TextView tvSearch;
    private MyGirdView photGirder;
    private AppClient appClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tb_details_layout);
        appClient = (AppClient) getApplication();
        sqtb = appClient.getSqtbs().get(getIntent().getIntExtra("info", 1));
        initView();
        title.setText("社区贴吧");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvContext.setText(sqtb.getCotent());
        tvTitle.setText(sqtb.getTitle());
        tvMsg.setText("日期：" + sqtb.getTime() + " 发布人：" + sqtb.getPeople());
        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PLDialog2 dialog2 = new PLDialog2(1,getWindowManager().getDefaultDisplay().getWidth());
                dialog2.show(getSupportFragmentManager(), "");

            }
        });
        if (sqtb.getBitmaps() != null) {
            photGirder.setAdapter(new FbAdapter2(TbDetalisActivity.this, sqtb.getBitmaps()));
            ivImage.setVisibility(View.GONE);
        } else {
            ivImage.setImageResource(R.mipmap.a);
            photGirder.setVisibility(View.GONE);
        }

    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        tvTitle = findViewById(R.id.tv_title);
        tvContext = findViewById(R.id.tv_context);
        tvMsg = findViewById(R.id.tv_msg);
        ivImage = findViewById(R.id.iv_image);
        tvSearch = findViewById(R.id.tv_search);
        photGirder = findViewById(R.id.phot_girder);
    }
}

