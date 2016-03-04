package com.qf.lenovo.meishijie;

import android.content.Intent;
import android.os.Handler;

import com.qf.lenovo.meishijie.base.BaseActivity;
/**
 *
 */
public class WelcomeActivity extends BaseActivity{
    //注意导包，容易导错import android.os.Handler
    private static Handler handler = new Handler();

    @Override
    protected int contenView() {
        return R.layout.activity_welcome;
    }
    @Override
    protected void init() {
        //延迟跳转页面
        //带有过场动画
       handler.postDelayed(new Runnable() {
           @Override
           public void run() {
               startActivity(new Intent(WelcomeActivity.this,NavigationActivity.class),R.anim.activity_in_right,R.anim.activity_out_down);
               finish();
           }
       },2000);
    }
}
