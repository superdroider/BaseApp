package com.superdroid.mybaseapplication.factorys;

import com.superdroid.mybaseapplication.fragments.BaseFragment;
import com.superdroid.mybaseapplication.fragments.HomeFragment;
import com.superdroid.mybaseapplication.fragments.MineFragment;
import com.superdroid.mybaseapplication.fragments.ServiceFragment;
import com.superdroid.mybaseapplication.utils.Constants;
import com.superdroid.mybaseapplication.utils.LogUtil;

import java.util.HashMap;

/**
 * Created by GT on 2015/8/20.
 * fragment Fragment工厂类
 */
public class FragmentFactory {

    /**
     * 存放Fragment的集合
     */
    private static HashMap<Integer, BaseFragment> fragments = new HashMap<Integer, BaseFragment>();

    /**
     * 根据key创建相应的Fragment
     *
     * @param key
     * @return Fragment
     */
    public static BaseFragment generateFragment(int key) {
        LogUtil.i("generateFragment:key=" + key);
        BaseFragment fragment = fragments.get(key);
        if (fragment == null) {
            switch (key) {
                case Constants.HOME_FRAG:
                    LogUtil.i("generateFragment HomeFragment");
                    fragment = new HomeFragment();
                    break;
                case Constants.SERVICE_FRAG:
                    LogUtil.i("generateFragment ServiceFragment");
                    fragment = new ServiceFragment();
                    break;
                case Constants.MINE_FRAG:
                    LogUtil.i("generateFragment MineFragment");
                    fragment = new MineFragment();
                    break;
            }
            fragments.put(key, fragment);
        }
        return fragment;
    }
}
