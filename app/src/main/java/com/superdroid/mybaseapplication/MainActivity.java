package com.superdroid.mybaseapplication;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import com.superdroid.mybaseapplication.factorys.FragmentFactory;
import com.superdroid.mybaseapplication.fragments.BaseFragment;
import com.superdroid.mybaseapplication.utils.Constants;
import com.superdroid.mybaseapplication.utils.LogUtil;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    private Button home_btn;
    private Button service_btn;
    private Button mine_btn;

    private FragmentManager mFragmentManager;
    private BaseFragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogUtil.i("---" + getClass().getSimpleName() + " onCreate1");
        initView();
        registeListener();
        fragment = FragmentFactory.generateFragment(Constants.HOME_FRAG);
        LogUtil.i("---"+getClass().getSimpleName()+" onCreate2");
        mFragmentManager = getSupportFragmentManager();
        LogUtil.i("---"+getClass().getSimpleName()+" onCreate3");
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        LogUtil.i("---"+getClass().getSimpleName()+" onCreate4");
        transaction.add(R.id.home_container, fragment);
        LogUtil.i("---" + getClass().getSimpleName() + " onCreate5");
        transaction.commit();
        LogUtil.i("---" + getClass().getSimpleName() + " onCreate6");
        LogUtil.i("---" + getClass().getSimpleName() + " onCreate7");

    }

    private void initView() {
        home_btn = (Button) findViewById(R.id.home_btn);
        service_btn = (Button) findViewById(R.id.service_btn);
        mine_btn = (Button) findViewById(R.id.mine_btn);
    }

    private void registeListener() {
        home_btn.setOnClickListener(this);
        service_btn.setOnClickListener(this);
        mine_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        switch (v.getId()) {
            case R.id.home_btn:
                fragment = FragmentFactory.generateFragment(Constants.HOME_FRAG);
                transaction.replace(R.id.home_container, fragment);
                break;
            case R.id.service_btn:
                fragment = FragmentFactory.generateFragment(Constants.SERVICE_FRAG);
                transaction.replace(R.id.home_container, fragment);
                break;
            case R.id.mine_btn:
                fragment = FragmentFactory.generateFragment(Constants.MINE_FRAG);
                transaction.replace(R.id.home_container, fragment);
                break;
        }
        transaction.commit();
    }
}
