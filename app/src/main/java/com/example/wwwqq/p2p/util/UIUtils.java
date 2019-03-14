package com.example.wwwqq.p2p.util;

import android.content.Context;
import android.os.Handler;
import android.os.Process;
import android.view.View;
import android.widget.Toast;

import com.example.wwwqq.p2p.common.MyApplication;

public class UIUtils {
    public static Context getcontext()
    {
        return MyApplication.context;
    }

    public static Handler getHandler()
    {
        return MyApplication.handler;
    }

    //返回指定colorId对应的颜色值
    public static int getColor(int colorId)
    {
        return getcontext().getResources().getColor(colorId);
    }

    //加载指定viewId的视图对象，并返回
    public static View getview(int viewId)
    {
        View view=View.inflate(getcontext(),viewId,null);
        return view;
    }

    public static String[] getStringArr(int strArrId)
    {
        String[] stringArray=getcontext().getResources().getStringArray(strArrId);
        return stringArray;
    }

    //将dp转化为px
    public static int dp2px(int dp)
    {
        //获取手机密度
        float density = getcontext().getResources().getDisplayMetrics().density;
        return (int)(dp*density+0.5);
    }

    public static int px2dp(int px)
    {
        float density = getcontext().getResources().getDisplayMetrics().density;
        return (int)(px/density+0.5);
    }

    //保证runnable中的操作在主线程中执行
    public static void runOnUiThread(Runnable runnable) {
        if(isInMainThread())
        {
            runnable.run();
        }
        else
            {
                UIUtils.getHandler().post(runnable);
            }
    }

    //判断当前的线程是否是主线程
    private static boolean isInMainThread() {
        int i = Process.myTid();
        return MyApplication.mainThreadId == i;
    }

    public static void toast(String message,boolean islengthlong)
    {
        Toast.makeText(UIUtils.getcontext(), message,islengthlong? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
    }

}
