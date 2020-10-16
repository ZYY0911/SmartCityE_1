package com.example.smartcitye_1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smartcitye_1.R;
import com.example.smartcitye_1.fragment.HdFragment;
import com.example.smartcitye_1.fragment.HomeFragment;
import com.example.smartcitye_1.fragment.MoreServic;
import com.example.smartcitye_1.fragment.MotifPwd;
import com.example.smartcitye_1.fragment.MyCenterFragment;
import com.example.smartcitye_1.fragment.MyInfoFragment;
import com.example.smartcitye_1.fragment.MyOderFragment;
import com.example.smartcitye_1.fragment.SmartBus;
import com.example.smartcitye_1.fragment.SmartHome;
import com.example.smartcitye_1.fragment.WzcxFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.StreamHandler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class AppHomeActivity extends AppCompatActivity {

    public Map<String, Fragment> map;
    private LinearLayout layoutSearch;
    private EditText etSearch;
    private FrameLayout frameLayout;
    private BottomNavigationView navBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        map = new HashMap<>();
        map.put("1", HomeFragment.newInstance(this));
        map.put("个人中心", MyCenterFragment.newInstance(this));
        map.put("个人信息", MyInfoFragment.newInstance(this));
        map.put("修改密码", MotifPwd.newInstance(this));
        map.put("订单列表", MyOderFragment.newInstance(this));
        map.put("活动", HdFragment.newInstance(this));
        map.put("智慧巴士", SmartBus.newInstance(this));
        map.put("违章查询", WzcxFragment.newInstance(this));
        map.put("全部服务", MoreServic.newInstance(this));
        map.put("智慧社区", SmartHome.newInstance(this));
        initView();
        switchFragment(map.get("1"));
        navBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        switchFragment(map.get("1"));
                        break;
                    case R.id.action_center:
                        switchFragment(map.get("个人中心"));
                        break;
                    case R.id.action_service:
                        switchFragment(map.get("全部服务"));
                        break;
                    case R.id.action_fp:
                        switchFragment(map.get("智慧社区"));
                        break;
                }
                return true;
            }
        });
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    Intent intent = new Intent(AppHomeActivity.this, SearchNewList.class);
                    intent.putExtra("info", etSearch.getText().toString());
                    startActivity(intent);
                    etSearch.setText("");
                    return true;
                }
                return false;
            }
        });
    }

    public void backOnClick() {
        switchFragment(map.get("1"));

    }

    private Fragment currentFragment = new Fragment();

    public void switchFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!fragment.isAdded()) {
            if (currentFragment != null) {
                transaction.hide(currentFragment);
            }
            transaction.add(R.id.frame_layout, fragment, fragment.getClass().getName());
        } else {
            transaction.hide(currentFragment).show(fragment);
        }
        if (fragment.getClass().getName().equals(map.get("1").getClass().getName())) {
            layoutSearch.setVisibility(View.VISIBLE);
        } else {
            layoutSearch.setVisibility(View.GONE);
        }
        currentFragment = fragment;
        transaction.commit();
    }

    private void initView() {
        layoutSearch = findViewById(R.id.layout_search);
        etSearch = findViewById(R.id.et_search);
        frameLayout = findViewById(R.id.frame_layout);
        navBottom = findViewById(R.id.nav_bottom);
    }
}