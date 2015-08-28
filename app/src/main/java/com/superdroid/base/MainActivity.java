package com.superdroid.base;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.superdroid.base.customuis.BottomItemView;
import com.superdroid.base.factorys.FragmentFactory;
import com.superdroid.base.utils.Constants;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private BottomItemView home;
    private BottomItemView around;
    private BottomItemView mine;
    private BottomItemView more;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        registeListener();
        home.setSelected(true);
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.home_container, FragmentFactory.generateFragment(Constants.HOME_FRAG));
        transaction.commit();
    }

    private void initView() {
        home = (BottomItemView) findViewById(R.id.home);
        around = (BottomItemView) findViewById(R.id.around);
        mine = (BottomItemView) findViewById(R.id.mine);
        more = (BottomItemView) findViewById(R.id.more);
    }

    private void registeListener() {
        home.setOnClickListener(this);
        around.setOnClickListener(this);
        mine.setOnClickListener(this);
        more.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        resetSelectStatu();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (v.getId()) {
            case R.id.home:
                home.setSelected(!home.isSelected());
                transaction.replace(R.id.home_container,
                        FragmentFactory.generateFragment(Constants.HOME_FRAG));
                break;
            case R.id.around:
                around.setSelected(!mine.isSelected());
                transaction.replace(R.id.home_container,
                        FragmentFactory.generateFragment(Constants.SERVICE_FRAG));
                break;
            case R.id.mine:
                mine.setSelected(!mine.isSelected());
                transaction.replace(R.id.home_container,
                        FragmentFactory.generateFragment(Constants.MINE_FRAG));
                break;
            case R.id.more:
                more.setSelected(!more.isSelected());
                transaction.replace(R.id.home_container,
                        FragmentFactory.generateFragment(Constants.MINE_FRAG));
                break;
        }
        transaction.commit();
    }

    private void resetSelectStatu() {
        home.setSelected(false);
        around.setSelected(false);
        mine.setSelected(false);
        more.setSelected(false);
    }
}
