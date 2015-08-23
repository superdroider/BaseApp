package com.superdroid.mybaseapplication.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.superdroid.mybaseapplication.R;
import com.superdroid.mybaseapplication.entities.MineData;
import com.superdroid.mybaseapplication.utils.BitmapUtilHelper;
import com.superdroid.mybaseapplication.utils.ViewUtil;

/**
 * Created by GT on 2015/8/23.
 */
public class MineHolder extends BaseHolder<MineData> {
    private ImageView icon;
    private TextView name;

    @Override
    public View initView() {
        View view = ViewUtil.inflate(R.layout.item_home_list);
        icon = (ImageView) view.findViewById(R.id.home_iv_icon);
        name = (TextView) view.findViewById(R.id.home_tv_name);
        return view;
    }

    @Override
    public void refreshView() {
        BitmapUtilHelper.getBitmapUtils().display(icon, data.getAvatar());
        name.setText(data.getNickname());
    }
}
