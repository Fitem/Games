package com.fitem.games.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * des:不能滑动ViewPager
 * Created by xsf
 * on 2016.07.15:33
 */
public class NoScrollViewPager extends android.support.v4.view.ViewPager {

    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
//        try {
//            return super.onTouchEvent(ev);
//        } catch (IllegalArgumentException ex) {
//            ex.printStackTrace();
//        }
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        try {
//            return super.onInterceptTouchEvent(ev);
//        } catch (IllegalArgumentException ex) {
//            ex.printStackTrace();
//        }
        return false;
    }
}
