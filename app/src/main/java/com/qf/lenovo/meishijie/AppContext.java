package com.qf.lenovo.meishijie;

import android.app.Application;

import com.qf.lenovo.meishijie.util.FrescoUtil;
import com.qf.lenovo.meishijie.util.OkHttpUtil;

/**
 *初始化:图片下载和数据下载
 */
public class AppContext extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FrescoUtil.initFresco(this);
        OkHttpUtil.initOkHttp();
    }
}

