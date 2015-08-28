package com.superdroid.base.dataprocessor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.superdroid.base.entities.CommentData;
import com.superdroid.base.entities.ServiceData;
import com.superdroid.base.utils.Constants;

import java.util.List;

/**
 * Created by GT on 2015/8/25.
 */
public class ServiceDataProcressor extends BaseDataProcessor<List<ServiceData>> {

    @Override
    public String getRequestParameters() {
        return mRequestParameters;
    }

    @Override
    public List<ServiceData> parseJson(String jsonStr) {
        CommentData commentData = new Gson().fromJson(jsonStr, new TypeToken<CommentData<ServiceData>>() {
        }.getType());

        if (commentData.getEc() == Constants.REQUEST_OK) {
            List<ServiceData> data = commentData.getData();
            if (data != null && data.size() != 0) {
                return data;
            }
        }
        return null;
    }
}
