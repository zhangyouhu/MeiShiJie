package com.qf.lenovo.meishijie.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 *注意v4包下的Fragment:别导错包
 */
public abstract class BaseFragment extends Fragment{
    //在fragment必须重写onCreateView:作用加载布局
    //紧接重写onViewCreated方法
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(contentResId(), container, false);
        return view;
    }

    protected abstract int contentResId();

    //该方法不属于生命周期里的方法,调用完onCreateView方法紧接调用onViewCreated方法
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        init();
        loadDatas();
    }

    protected void loadDatas() {
    }

    protected void init() {

    }
}
