package com.example.wwwqq.p2p.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.wwwqq.p2p.R;
import com.example.wwwqq.p2p.bean.User;
import com.example.wwwqq.p2p.common.AppNetConfig;
import com.example.wwwqq.p2p.common.BaseActivity;
import com.example.wwwqq.p2p.util.MD5Utils;
import com.example.wwwqq.p2p.util.UIUtils;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_title_setting)
    ImageView ivTitleSetting;
    @BindView(R.id.tv_login_number)
    TextView tvLoginNumber;
    @BindView(R.id.et_login_number)
    EditText etLoginNumber;
    @BindView(R.id.rl_login)
    RelativeLayout rlLogin;
    @BindView(R.id.tv_login_pwd)
    TextView tvLoginPwd;
    @BindView(R.id.et_login_pwd)
    EditText etLoginPwd;
    @BindView(R.id.btn_login)
    Button btnLogin;

    @Override
    protected void initData() {

    }

    @Override
    protected void initTitle() {
        ivTitleBack.setVisibility(View.VISIBLE);
        tvTitle.setText("用户登录");
        ivTitleSetting.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.iv_title_back)
    public void back(View view)
    {
        removeAll();
        goToActivity(MainActivity.class,null);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @OnClick(R.id.btn_login)
    public void login(View view)//登录按钮的点击事件
    {
        String number = etLoginNumber.getText().toString().trim();
        String pwd = etLoginPwd.getText().toString().trim();

        if(!TextUtils.isEmpty(number) && !TextUtils.isEmpty(pwd))
        {
            String url = AppNetConfig.LOGIN;
            RequestParams params = new RequestParams();
            params.put("phone",number);
            params.put("password",MD5Utils.MD5(pwd));
            client.post(url,params,new AsyncHttpResponseHandler()
            {
                @Override
                public void onSuccess(String content) {
                    //解析json
                    JSONObject jsonObject = JSON.parseObject(content);
                    Boolean success = jsonObject.getBoolean("success");
                    if(success)
                    {
                        //解析json数据，生成User对象
                        String data = jsonObject.getString("data");
                        User user = JSON.parseObject(data, User.class);

                        //保存用户信息
                        saveUser(user);
                        //重新加载界面
                        removeAll();
                        goToActivity(MainActivity.class,null);


                    }else
                        {
                            Toast.makeText(LoginActivity.this,"用户名不存在或密码不正确",Toast.LENGTH_SHORT).show();
                        }
                }

                @Override
                public void onFailure(Throwable error, String content) {
                    UIUtils.toast("联网失败",false);
                }
            });
        }else
            {
                UIUtils.toast("用户名或密码不能为空",false);
            }


    }

}