package com.superdroid.mybaseapplication.dataprocessor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.superdroid.mybaseapplication.entities.CommentData;
import com.superdroid.mybaseapplication.entities.MineData;

import java.util.List;

/**
 * Created by Administrator on 2015/8/23.
 */
public class MineDataProcressor extends BaseDataProcessor<List<MineData>> {
    @Override
    public String getRequestParameters() {
        return "map=api_member_interest&plum_session_api=r2bcv8d33ohcugcv725im9m857&page=0";
    }

    @Override
    public List<MineData> parseJson(String jsonStr) {
        MineData mMineData = new Gson().fromJson(jsonStr, );
        return mMineData;
    }
}
