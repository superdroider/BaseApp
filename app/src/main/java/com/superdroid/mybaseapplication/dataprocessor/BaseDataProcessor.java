package com.superdroid.mybaseapplication.dataprocessor;

import android.os.SystemClock;
import android.text.TextUtils;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.ResponseStream;
import com.lidroid.xutils.http.client.HttpRequest;
import com.superdroid.mybaseapplication.utils.Constants;
import com.superdroid.mybaseapplication.utils.FileUtil;
import com.superdroid.mybaseapplication.utils.IOUtil;
import com.superdroid.mybaseapplication.utils.LogUtil;
import com.superdroid.mybaseapplication.utils.MD5Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * Created by GT on 2015/8/22.
 * 数据处理基类
 */
public abstract class BaseDataProcessor<T> {
    private static final long KEEP_TIME = 60 * 1000;

    /**
     * 加载数据,支持从缓存加载
     */
    public T loadData() {
        SystemClock.sleep(1000);
        // 从本地加载数据
        String jsonStr = loadDataFromLocal(getRequestParameters());
        if (TextUtils.isEmpty(jsonStr)) {
            // 从网络加载数据
            jsonStr = loadDataFromNet();
            if (TextUtils.isEmpty(jsonStr)) {
                // 加载出错
            } else {
                saveDataToLocal(jsonStr, getRequestParameters());
            }
        }
        if (!TextUtils.isEmpty(jsonStr)) {
            return parseJson(jsonStr);
        }
        return null;
    }

    /**
     * 加载数据,不支持从缓存加载
     */
    public T loadDataUnuseCache() {
        String jsonStr = loadDataFromNet();
        if (!TextUtils.isEmpty(jsonStr)) {
            return parseJson(jsonStr);
        } else {
            return null;
        }
    }

    /**
     * 解析json数据
     *
     * @param jsonStr
     * @return
     */
    public abstract T parseJson(String jsonStr);

    /**
     * 从本地加载数据
     *
     * @return
     */
    public String loadDataFromLocal(String key) {
        String path = FileUtil.getJsonCachePath() + generateFileName(key);
        File file = new File(path);
        BufferedReader bufferedReader = null;
        if (file.exists()) {
            try {
                bufferedReader = new BufferedReader(new FileReader(file));
                String keepTimeStr = bufferedReader.readLine();
                if (!TextUtils.isEmpty(keepTimeStr)) {
                    long keepTime = Long.parseLong(keepTimeStr);
                    String line = null;
                    StringBuffer strBuff = new StringBuffer();
                    if (System.currentTimeMillis() < keepTime) {
                        while ((line = bufferedReader.readLine()) != null) {
                            strBuff.append(line);
                        }
                        LogUtil.i(strBuff.toString());
                        return strBuff.toString();
                    }
                }
            } catch (Exception e) {
                LogUtil.e(e);
            } finally {
                IOUtil.close(bufferedReader);
            }
        }

        return null;
    }

    /**
     * 从网络加载数据,不缓存
     *
     * @return
     */
    private String loadDataFromNet() {
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.configHttpCacheSize(0);
        httpUtils.configDefaultHttpCacheExpiry(0);
        String resultStr = null;
        try {
            LogUtil.d(Constants.BASE_URL + getRequestParameters());
            ResponseStream responseStream = httpUtils.sendSync(HttpRequest.HttpMethod.GET,
                    Constants.BASE_URL + getRequestParameters());
            resultStr = responseStream.readString();
        } catch (Exception e) {
            LogUtil.e(e);
        }
        return resultStr;
    }


    /**
     * 保存数据到本地
     *
     * @param jsonStr
     */
    public void saveDataToLocal(String jsonStr, String key) {
        String cachePath = FileUtil.getJsonCachePath();// 缓存路径
        File file = new File(cachePath + generateFileName(key));
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
            long currentTimeMillis = System.currentTimeMillis() + KEEP_TIME;
            fw.write(currentTimeMillis + "\r\n");
            fw.flush();
            fw.write(jsonStr);
            fw.flush();
        } catch (Exception e) {
            LogUtil.e(e);
            IOUtil.close(fw);
        }

    }

    /**
     * 根据key生成相应的缓存文件名
     *
     * @param key
     * @return
     */
    private String generateFileName(String key) {
        if (key == null)
            return null;
        else
            return MD5Util.MD5(key);
    }


    /**
     * 网络请求的参数
     *
     * @return
     */
    public abstract String getRequestParameters();

}
