package com.superdroid.mybaseapplication.viewholders;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.superdroid.mybaseapplication.R;
import com.superdroid.mybaseapplication.entities.HomeData;
import com.superdroid.mybaseapplication.utils.BitmapUtilHelper;
import com.superdroid.mybaseapplication.utils.ViewUtil;

import java.util.List;

/**
 * Created by Administrator on 2015/8/23.
 */
public class HomeHolder extends BaseHolder<HomeData.Data> {
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
        BitmapUtilHelper.getBitmapUtils().display(icon, data.getIcon());
        name.setText(data.getName());
    }
}
