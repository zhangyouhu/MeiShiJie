package com.qf.lenovo.meishijie;


import android.widget.RadioGroup;

import com.qf.lenovo.meishijie.base.BaseActivity;
import com.qf.lenovo.meishijie.fragment.HomePageFragment;


public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup radioGroup;
    @Override
    protected int contenView() {
        return R.layout.activity_main;
    }
    //初始化控件
    @Override
    protected void init() {
        radioGroup = (RadioGroup) findViewById(R.id.rg_id);
        //默认选中第一个RadioButton
        radioGroup.getChildAt(0).performClick();
        //设置监听方法1、写成匿名内部类2、设置监听者为this
       radioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch(checkedId){
            case R.id.recomment_id:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fl_fragment,new HomePageFragment())
                        .commit();
                break;
            case R.id.discover_id:
                break;
            case R.id.shop_id:
                break;
            case R.id.topic_id:
                break;
            case R.id.wode_id:
                break;
        }
    }
}
