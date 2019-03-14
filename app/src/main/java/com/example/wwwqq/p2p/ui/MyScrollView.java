package com.example.wwwqq.p2p.ui;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

public class MyScrollView extends ScrollView {

    private View childView;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    @Override
//    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        super.onLayout(changed, l, t, r, b);
//    }

    //获取子视图
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if(getChildCount()>0)
        {
            childView = getChildAt(0);
        }
    }
    private int lastY;//上一次y轴方向操作的坐标位置
    private Rect normal = new Rect();//用于记录临界状态的,左,上,右,下
    private boolean isFinishAnimation;
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(childView==null || !isFinishAnimation)
        {
            return super.onTouchEvent(ev);
        }
        int eventY = (int) ev.getY();//获取当前的y轴坐标
        switch (ev.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                lastY=eventY;
                break;
            case MotionEvent.ACTION_MOVE:
                int dy = eventY - lastY;//微小的移动量
                if(isNeedMove())
                {
                    if(isNeedMove())
                    {
                        if(normal.isEmpty())
                        {
                            //记录了childView的临界状态的,左,上,右,下
                            normal.set(childView.getLeft(),childView.getTop(),childView.getRight(),childView.getBottom());
                        }
                    }
                    //重新布局
                    childView.layout(childView.getLeft(),childView.getTop() + dy/2,childView.getRight(),childView.getBottom() + dy/2);
                }
                lastY=eventY;//重新赋值
                break;
            case MotionEvent.ACTION_UP:

                //使用平移动画
                int translateY = childView.getBottom() - normal.bottom;
                TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 0, -translateY);
                translateAnimation.setDuration(200);
                translateAnimation.setFillAfter(true);//停留在最终位置上

                translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        isFinishAnimation=false;
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        isFinishAnimation=true;
                        childView.clearAnimation();//清楚动画
                        //重新布局
                        childView.layout(normal.left,normal.top,normal.right,normal.bottom);
                        //清除normal的数据
                        normal.setEmpty();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                //启动动画
                childView.startAnimation(translateAnimation);

                break;
        }

        return super.onTouchEvent(ev);

    }

    private boolean isNeedMove() {
        int childMeasuredHeight = childView.getMeasuredHeight();//获取子视图的高度
        int scrollViewMeasuredHeight = this.getMeasuredHeight();//获取布局的高度

        int dy = childMeasuredHeight - scrollViewMeasuredHeight;//dy >= 0

        float scrollY = this.getScrollY();//获取用户在y轴方向上的偏移量(上+下-)

        if(scrollY<=0||scrollY>=dy)
        {
            return true;//按照我们自定义的MyScrollView的方式处理
        }
        //其他处在临界范围内的，返回false，即表示，仍按照ScrollView的方式处理
        return false;
    }
}
