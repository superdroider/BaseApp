package com.superdroid.base.viewholders;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

import com.superdroid.base.R;
import com.superdroid.base.events.RefreshAndLoadMoreEvent;
import com.superdroid.base.utils.Constants;
import com.superdroid.base.utils.ViewUtil;

import de.greenrobot.event.EventBus;


public class LoadMoreHolder extends BaseHolder<Integer> {
    private RelativeLayout rl_loading;
    private RelativeLayout rl_load_error;

    private View view;
    private Integer status;

    @Override
    public View initView() {
        view = ViewUtil.inflate(R.layout.item_more);
        rl_loading = (RelativeLayout) view.findViewById(R.id.rl_loading);
        rl_load_error = (RelativeLayout) view.findViewById(R.id.rl_load_error);
        rl_load_error.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                refreshView(Constants.HAVE_MOREDATA_STATUS);
                EventBus.getDefault().post(new RefreshAndLoadMoreEvent(Constants.PALM_LOADMORE, Constants.PALM_LOADMORE_BEGIN));
            }
        });
        return view;
    }

    @Override
    public void refreshView() {
        this.status = data;
        if (data == Constants.ERROR_STATUS) {
            rl_loading.setVisibility(View.GONE);
            rl_load_error.setVisibility(View.VISIBLE);
        } else if (data == Constants.HAVE_MOREDATA_STATUS) {
            rl_loading.setVisibility(View.VISIBLE);
            rl_load_error.setVisibility(View.GONE);
        } else if (data == Constants.NO_DATA_STATUS) {
            rl_loading.setVisibility(View.GONE);
            rl_load_error.setVisibility(View.GONE);
        }
    }
}
