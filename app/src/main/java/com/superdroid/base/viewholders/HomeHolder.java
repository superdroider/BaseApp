package com.superdroid.base.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.superdroid.base.R;
import com.superdroid.base.entities.HomeData;
import com.superdroid.base.utils.BitmapUtilHelper;
import com.superdroid.base.utils.ViewUtil;

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
