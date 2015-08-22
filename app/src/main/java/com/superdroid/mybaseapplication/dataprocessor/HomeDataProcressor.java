package com.superdroid.mybaseapplication.dataprocessor;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.superdroid.mybaseapplication.entities.HomeData;
import com.superdroid.mybaseapplication.utils.Constants;
import com.superdroid.mybaseapplication.utils.LogUtil;

import java.util.List;

/**
 * Created by GT on 2015/8/22.
 */
public class HomeDataProcressor extends BaseDataProcessor<List<HomeData.Data>> {


    @Override
    public List<HomeData.Data> parseJson(String jsonStr) {
        if (!TextUtils.isEmpty(jsonStr)) {
            JsonElement jsonElement = new JsonParser().parse(jsonStr);
            LogUtil.i(jsonElement.toString());
            if (jsonElement.isJsonObject() || jsonElement.isJsonArray()) {
                HomeData homeData = new Gson().fromJson(jsonElement, HomeData.class);
                if (homeData.getEc() == Constants.REQUEST_OK) {
                    return homeData.getData();
                }
            }
        }
        return null;
    }

    @Override
    public String getRequestParameters() {
        return Constants.HOME_REQUEST_PARAMETERS;
    }

}
