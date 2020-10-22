package com.example.smartcitye_1.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcitye_1.R;
import com.example.smartcitye_1.net.VolleyLo;
import com.example.smartcitye_1.net.VolleyTo;
import com.example.smartcitye_1.util.Util;

import org.json.JSONObject;

import java.util.Date;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/15 at 9:29
 */
public
class YjfkActivity2 extends AppCompatActivity {


    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private EditText etMsg;
    private TextView tvNum;
    private Button btSave;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yjfk_layout);
        initView();
        title.setText("评价");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        etMsg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) {
                    String length = s.toString();
                    if (length.length() >= 151) {
                        Util.showToast("只能输入150字", YjfkActivity2.this);
                        etMsg.setText(length.substring(0, 150));
                        etMsg.setSelection(150);
                        return;
                    }
                    tvNum.setText(length.length() + "/150字");
                }
            }
        });
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Util.showDialog("保存成功", YjfkActivity2.this);
                etMsg.setText("");
                finish();

            }
        });
    }


    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        etMsg = findViewById(R.id.et_msg);
        tvNum = findViewById(R.id.tv_num);
        btSave = findViewById(R.id.bt_save);
    }
}
