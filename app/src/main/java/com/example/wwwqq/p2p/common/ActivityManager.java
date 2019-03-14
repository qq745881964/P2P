package com.example.wwwqq.p2p.common;

import android.app.Activity;

import java.util.Stack;

public class ActivityManager {
    public ActivityManager() {

    }

    private static ActivityManager activityManager=new ActivityManager();

    public static ActivityManager getInstance()
    {
        return activityManager;
    }

    //提供栈的对象
    private Stack<Activity> activityStack = new Stack<>();

    public void add(Activity activity)
    {
        if(activity!=null)
        {
            activityStack.add(activity);
        }
    }

    //插曲:[12,3,44,6,332,65,-56,1]
    //删除指定的activity
    public void remove(Activity activity)
    {
        if(activity!=null)
        {
//            //方法一
//            for (int i = 0; i < activityStack.size(); i++) {
//                Activity activity1 = activityStack.get(i);
//                if(activity1.getClass().equals(activity.getClass()))
//                {
//                    activity1.finish();//销毁当前activity
//                    activityStack.remove(i);//从栈空间移除
//                }
//            }

//            方法二
            for (int i = activityStack.size(); i >=0 ; i++) {
                Activity activity1 = activityStack.get(i-1);
                if(activity1.getClass().equals(activity.getClass()))
                {
                    activity1.finish();//销毁当前activity
                    activityStack.remove(i-1);//从栈空间移除
                }
            }
        }
        
    }

    //删除当前的activity
    public void removeCurrent()
    {
        //方法一
//        Activity activity = activityStack.get(activityStack.size() - 1);
//        activity.finish();
//        activityStack.remove(activityStack.size()-1);

        //方法二
        Activity activity = activityStack.lastElement();
        activity.finish();
        activityStack.remove(activity);
    }

    //删除所有的activity
    public void removeAll()
    {
        for (int i = activityStack.size()-1; i >= 0; i++) {
            Activity activity = activityStack.get(i);
            activity.finish();
            activityStack.remove(activity);
        }
    }

    //返回栈大小
    public int size()
    {
        return activityStack.size();
    }

}
