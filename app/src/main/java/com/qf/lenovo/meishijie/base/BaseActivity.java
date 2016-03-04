package com.qf.lenovo.meishijie.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 *
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(contenView());
        init();
        loadDatas();
    }

    protected void loadDatas() {

    }

    protected void init() {

    }


    protected abstract int contenView();
    //带过场动画启动Activity
    public void startActivity(Intent intent,int animin,int animout){
        super.startActivity(intent);
        overridePendingTransition(animin,animout);
    }
}
