package com.qf.lenovo.meishijie.util;

import android.os.Handler;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *下载数据工具类:谷歌的，简单点的第三方网络请求方式，更快的，节省用户流量，2015年兴起的框架
 */
public class OkHttpUtil {

    private static OkHttpClient okHttpClient;//只需创建一个全局对象
    private static Handler handler = new Handler();
    //初始化
    public static void initOkHttp(){
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();
    }



    /**
     * 下载json
     * @param url
     */
    //如果要下载5个json对象，就只需一个okHttpClient对象
    public static void downJSON(final String url, final OnDownDataListener onDownDataListener){
        //每次发一次请求就创建一个request对象
        Request request = new Request.Builder()//利用Builder对象里的方法
                .url(url)//请求路径
                .build();//创建

        Call call = okHttpClient.newCall(request);
        //使用内部类，回调该方法
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if(onDownDataListener != null){
                    onDownDataListener.onFailure(url, e.getMessage());
                }
            }
            //请求成功
            //服务器返回的数据全部放在response对象中
            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                //解析下载json字符串，就返回接收字符串
                final String str = response.body().string();
                //如果下载是大的文件，就用文件流
//                String data = response.body().byteStream();
                //如果返回的是字节数组
//                String [] bys=response.body().bytes();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(onDownDataListener != null){
                            onDownDataListener.onResponse(url, str);
                        }
                    }
                });
            }
        });
    }


    /**
     * 同步get请求 -- 让子类调用
     * @return
     */
    public static Response downResponse(String url){
        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = okHttpClient.newCall(request);
        try {
            return call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * post提交表单
     */
    public static void postSubmitForm(final String url, Map<String, String> params, final OnDownDataListener onDownDataListener){
        if(params.size() > 0) {
            FormBody.Builder builder = new FormBody.Builder();
            for(String key : params.keySet()){
                String value = params.get(key);
                builder.add(key, value);
            }
            FormBody formBody = builder.build();

            final Request request = new Request.Builder()
                    .url(url)
                    .post(formBody)
                    .build();

            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, final Response response) throws IOException {
                    final String str = response.body().string();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if(onDownDataListener != null){
                                onDownDataListener.onResponse(url, str);
                            }
                        }
                    });
                }
            });
        }
    }



    public interface OnDownDataListener{
        void onResponse(String url, String json);
        void onFailure(String url, String error);
    }
}
