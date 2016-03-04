package com.qf.lenovo.meishijie.util;

import android.content.Context;
import android.net.Uri;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

/**
 * 下载图片工具类
 */
public class FrescoUtil {
    //初始化
    public static void initFresco(Context context){
        Fresco.initialize(context);
    }

    public static void initFresco(Context context, ImagePipelineConfig imagePipelineConfig){
        Fresco.initialize(context, imagePipelineConfig);
    }

    /**
     * 普通的图片加载
     */
    public static void imageViewBind(String url, SimpleDraweeView simpleDraweeView){
        Uri uri = Uri.parse(url);
        simpleDraweeView.setImageURI(uri);
    }

    /**
     * 开启重试的图片加载
     */
    public static void imageViewBindRetry(String url, SimpleDraweeView simpleDraweeView){
        Uri uri = Uri.parse(url);
        AbstractDraweeController build = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setTapToRetryEnabled(true)//开启重试
                .setOldController(simpleDraweeView.getController())
                .build();
        simpleDraweeView.setController(build);
    }
}
