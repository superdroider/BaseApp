package com.superdroid.mybaseapplication;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    private Button home_btn;
    private Button service_btn;
    private Button mine_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        registeListener();

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
        switch (v.getId()) {
            case R.id.home_btn:

                break;
            case R.id.service_btn:

                break;
            case R.id.mine_btn:

                break;
        }
    }
}
