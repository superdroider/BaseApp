package com.superdroid.base.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.superdroid.base.R;
import com.superdroid.base.entities.ServiceData;
import com.superdroid.base.utils.BitmapUtilHelper;
import com.superdroid.base.utils.ViewUtil;

/**
 * Created by GT on 2015/8/25.
 */
public class ServiceHolder extends BaseHolder<ServiceData> {
    private ImageView logo;
    private TextView name;

    @Override
    public View initView() {
        View view = ViewUtil.inflate(R.layout.item_home_list);
        logo = (ImageView) view.findViewById(R.id.home_iv_icon);
        name = (TextView) view.findViewById(R.id.home_tv_name);
        return view;
    }

    @Override
    public void refreshView() {
        BitmapUtilHelper.getBitmapUtils().display(logo, data.getLogo());
        name.setText(data.getName());
    }
}
