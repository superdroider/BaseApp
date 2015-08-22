package com.superdroid.mybaseapplication.utils;

/**
 * Created by GT on 2015/8/20.
 */
public class Constants {
    /*Fragment标识*/
    public static final int HOME_FRAG = 1;
    public static final int SERVICE_FRAG = 2;
    public static final int MINE_FRAG = 3;
    /*页面状态*/
    public static final int PAGE_UNKNOWN = 0;
    public static final int PAGE_LOADING = 1;
    public static final int PAGE_ERROR = 2;
    public static final int PAGE_EMPTY = 3;
    public static final int PAGE_SUCCESS = 4;

    /*基本请求参数*/
    public static final String BASE_URL = "http://xyapp.ikinvin.net/api.php?";

    /*http 请求成功*/
    public static final int REQUEST_OK = 200;
    /*http 请求无效*/
    public static final int REQUEST_INVALID = 400;

    /*首页请求参数*/
    public static final String HOME_REQUEST_PARAMETERS = "map=api_category_main&type=0";
}
