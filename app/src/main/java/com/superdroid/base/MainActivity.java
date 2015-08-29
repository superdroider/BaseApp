package com.superdroid.base;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.superdroid.base.customuis.BadgeRadioButton;
import com.superdroid.base.factorys.FragmentFactory;
import com.superdroid.base.utils.Constants;


public class MainActivity extends ActionBarActivity implements RadioGroup.OnCheckedChangeListener {

    private BadgeRadioButton home;
    private BadgeRadioButton around;
    private BadgeRadioButton mine;
    private BadgeRadioButton more;
    private RadioGroup rg_main;
    private TextView topbar_title_tv;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        registeListener();
        topbar_title_tv.setText(R.string.home);
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.home_container, FragmentFactory.generateFragment(Constants.HOME_FRAG));
        transaction.commit();
    }

    private void initView() {
        topbar_title_tv = getViewById(R.id.topbar_title_tv);
        rg_main = getViewById(R.id.main_rg);
        home = getViewById(R.id.home);
        around = getViewById(R.id.around);
        mine = getViewById(R.id.mine);
        more = getViewById(R.id.more);

        topbar_title_tv.setVisibility(View.VISIBLE);
        home.showCirclePointBadge();
        around.showCirclePointBadge();
        mine.showCirclePointBadge();
        more.showCirclePointBadge();
    }

    private void registeListener() {
        rg_main.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (checkedId) {
            case R.id.home:
                topbar_title_tv.setText(R.string.home);
                around.hiddenBadge();
                transaction.replace(R.id.home_container,
                        FragmentFactory.generateFragment(Constants.HOME_FRAG));
                break;
            case R.id.around:
                topbar_title_tv.setText(R.string.service);
                home.hiddenBadge();
                around.showCirclePointBadge();
                transaction.replace(R.id.home_container,
                        FragmentFactory.generateFragment(Constants.SERVICE_FRAG));
                break;
            case R.id.mine:
                topbar_title_tv.setText(R.string.mine);
                transaction.replace(R.id.home_container,
                        FragmentFactory.generateFragment(Constants.MINE_FRAG));
                break;
            case R.id.more:
                topbar_title_tv.setText(R.string.mine);
                transaction.replace(R.id.home_container,
                        FragmentFactory.generateFragment(Constants.MINE_FRAG));
                break;
        }
        transaction.commit();
    }

    /**
     * 根据ID查找view
     *
     * @param id  view的ID
     * @param <T> view的类型
     * @return
     */
    protected <T extends View> T getViewById(int id) {
        return (T) findViewById(id);
    }
}
