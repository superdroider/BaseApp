package com.superdroid.base.dataprocessor;

import com.google.gson.Gson;
import com.superdroid.base.entities.HomeData;
import com.superdroid.base.utils.Constants;

import java.util.List;

/**
 * Created by GT on 2015/8/22.
 */
public class HomeDataProcressor extends BaseDataProcessor<List<HomeData.Data>> {

    @Override
    public List<HomeData.Data> parseJson(String jsonStr) {
        HomeData homeData = new Gson().fromJson(jsonStr, HomeData.class);
        if (homeData.getEc() == Constants.REQUEST_OK) {
            return homeData.getData();
        } else {
            return null;
        }
    }
    @Override
    public String getRequestParameters() {
        return Constants.HOME_REQUEST_PARAMETERS;
    }

}
