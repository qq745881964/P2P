package com.example.contexttest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.contexttest.R;

@SuppressLint("Registered")
public class MainActivity extends Activity {

    public MainActivity()
    {
        Log.e("TAG","MainActivity MainActivity()");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取Application实例
        Context context = this.getApplicationContext();
        MyApplication application = (MyApplication) this.getApplication();
        Log.e("TAG","context==application:"+(context == application));

        //设置其内部的数据
        application.setData("友谊的小船说翻就翻");


    }

    public void startMyservice(View view) {
        //启动服务
        startService(new Intent(this,MyService.class));

        new AlertDialog.Builder(this)
                .setTitle("测试")
//                .setView()
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNegativeButton("取消",null)
                .show();
    }
}
