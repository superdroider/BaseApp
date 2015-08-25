package com.superdroid.mybaseapplication.dataprocessor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.superdroid.mybaseapplication.entities.Banner;
import com.superdroid.mybaseapplication.entities.CommentData;

import java.util.List;

/**
 * Created by GT on 2015/8/24.
 */
public class BannerDataProcessor extends BaseDataProcessor<List<Banner>> {
    @Override
    public String getRequestParameters() {
        return "map=api_slide_fetch&loc=0";
    }

    @Override
    public List<Banner> parseJson(String jsonStr) {
        CommentData mCommentData = new Gson().fromJson(jsonStr, new TypeToken<CommentData<Banner>>() {
        }.getType());
        return mCommentData.getData();
    }
}
