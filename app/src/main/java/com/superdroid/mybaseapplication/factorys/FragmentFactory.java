package com.superdroid.mybaseapplication.factorys;

import com.superdroid.mybaseapplication.fragments.BaseFragment;
import com.superdroid.mybaseapplication.fragments.HomeFragment;
import com.superdroid.mybaseapplication.fragments.MineFragment;
import com.superdroid.mybaseapplication.fragments.ServiceFragment;
import com.superdroid.mybaseapplication.utils.Constants;

import java.util.HashMap;

/**
 * Created by GT on 2015/8/20.
 * fragment ������
 */
public class FragmentFactory {

    /**
     * ���Fragment�ļ���
     */
    private static HashMap<Integer, BaseFragment> fragments = new HashMap<Integer, BaseFragment>();

    /**
     * ����key������Ӧ��Fragment
     *
     * @param key Fragment�ı�ʶ
     * @return ���ɵ�Fragment
     */
    public static BaseFragment generateFragment(int key) {
        BaseFragment fragment = fragments.get(key);
        if (fragment == null) {
            switch (key) {
                case Constants.HOME_FRAG:
                    fragment = new HomeFragment();
                    break;
                case Constants.SERVICE_FRAG:
                    fragment = new ServiceFragment();
                    break;
                case Constants.MINE_FRAG:
                    fragment = new MineFragment();
                    break;
            }
            fragments.put(key, fragment);
        }
        return fragment;
    }
}
