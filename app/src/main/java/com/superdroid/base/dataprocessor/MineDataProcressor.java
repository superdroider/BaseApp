package com.superdroid.base.dataprocessor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.superdroid.base.entities.CommentData;
import com.superdroid.base.entities.MineData;
import com.superdroid.base.utils.Constants;

import java.util.List;

/**
 * Created by Administrator on 2015/8/23.
 */
public  class MineDataProcressor extends BaseDataProcessor<List<MineData>> {
    @Override
    public String getRequestParameters() {
        return "map=api_member_interest&plum_session_api=ojvtm6vp08n4uej2mcu8e0vjd7&page=0";
    }

    @Override
    public List<MineData> parseJson(String jsonStr) {
        CommentData data = new Gson().fromJson(jsonStr, new TypeToken<CommentData<MineData>>() {
        }.getType());
        if (data.getEc() == Constants.REQUEST_OK) {
            return data.getData();
        } else {
            return null;
        }
    }
}
