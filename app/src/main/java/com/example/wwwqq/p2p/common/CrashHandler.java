package com.example.wwwqq.p2p.common;

import android.animation.AnimatorSet;
import android.os.Build;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.example.wwwqq.p2p.util.UIUtils;

public class CrashHandler implements Thread.UncaughtExceptionHandler{

    //系统默认的处理未捕获异常的处理器
    private Thread.UncaughtExceptionHandler exceptionHandler;

    //单例模式(懒汉式)
    //本身实例化未捕获异常的处理器的操作就是系统在一个单独的线程中完成，所有不涉及到多线程的问题，所以使用懒汉式最好
    private CrashHandler()
    {

    }

    private static CrashHandler crashHandler = null;
    public static CrashHandler getInstamce()
    {
        if (crashHandler==null)
        {
            crashHandler=new CrashHandler();
        }

        return crashHandler;
    }

    public void init()
    {
        exceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        //将当前类设置为出现未捕获异常的处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    //一旦系统出现未捕获的异常，就会调用如下的回调方法
    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
//        Log.e("TAG", "亲，出现了未捕获的异常了！" +throwable.getMessage());

        new Thread()
        {
            @Override
            public void run() {
                //prepare()和Loop之间的操作就是在主线程中执行的
                //在android系统中，默认情况下，一个线程中是不可以调用Looper进行消息的处理的，除非是主线程
                Looper.prepare();
                Toast.makeText(UIUtils.getcontext(), "亲，出现了未捕获的异常了！", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();

        //收集异常信息
        collectionException(throwable);

        try {
            Thread.sleep(2000);
            //移除当前activity
            ActivityManager.getInstance().removeCurrent();
            //结束当前的进程
            android.os.Process.killProcess(android.os.Process.myPid());
            //结束虚拟机
            System.exit(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void collectionException(Throwable ex) {
        final String exMessage = ex.getMessage();
        //收集具体的客户的手机,系统的信息
        final String message=Build.DEVICE + ":" + Build.MODEL + ":" + Build.PRODUCT + ":" + Build.VERSION.SDK_INT;
        //发送给后台此异常信息
        new Thread()
        {
            @Override
            public void run() {
                //需要按照指定的url，访问后台的sevlet,将异常信息发送过去
                Log.e("TAG","exception = " + exMessage);
                Log.e("TAG","message = " + message);
            }
        }.start();

    }
}
