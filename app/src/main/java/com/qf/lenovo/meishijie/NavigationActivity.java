package com.qf.lenovo.meishijie;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.qf.lenovo.meishijie.base.BaseActivity;

/**
 * 导航页面
 */
public class NavigationActivity extends BaseActivity {
    //0、准备好数据源(模拟出来)
    //1、准备图片ID
    //2、查找控件ViewPager
    //3、准备适配器
    //4、适配器一般写成内部类
    //5、给ViewPager控件设置监听
    //6、滑动最好一张图片要跳转页面
    private int[] imagesId = {R.drawable.introduct_1,R.drawable.introduct_2,
            R.drawable.introduct_3,R.drawable.introduct_4};
//    @Bind(R.id.vp_id)
//    public ViewPager viewPager;
    private ViewPager viewPager;
    @Override
    protected int contenView() {
        return R.layout.activity_navigation;
    }

    //初始化控件
    @Override
    protected void init() {
        //适配器的传送参数有几种
        viewPager = (ViewPager) findViewById(R.id.vp_id);
        viewPager.setAdapter(new MyPagerAdapter());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //viewpager滑动一直回调该方法
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                   if(position == imagesId.length-1){
                       startActivity(new Intent(NavigationActivity.this,MainActivity.class));
                       finish();
                   }

            }
            //viewpager选中回调
            @Override
            public void onPageSelected(int position) {

            }
            //viewpager滑动改变回调该方法
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
    //把数据源传过来
    class MyPagerAdapter extends PagerAdapter{
        //必须重写以下四个方法
        @Override
        public int getCount() {
            return imagesId.length;
        }
        //一般都这样写的
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
        //删除控件,删除对象就删除控件了
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
        //将控件添加到父容器，利用上下文获得控件
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView iv = new ImageView(getApplicationContext());
            iv.setBackgroundResource(imagesId[position]);
            container.addView(iv);
            return iv;
        }
    }
}
