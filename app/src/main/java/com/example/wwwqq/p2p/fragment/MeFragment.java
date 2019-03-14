package com.example.wwwqq.p2p.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.UnicodeSet;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wwwqq.p2p.R;
import com.example.wwwqq.p2p.activity.BarChartActivity;
import com.example.wwwqq.p2p.activity.ChongZhiActivity;
import com.example.wwwqq.p2p.activity.LineChartActivity;
import com.example.wwwqq.p2p.activity.LoginActivity;
import com.example.wwwqq.p2p.activity.PieChartActivity;
import com.example.wwwqq.p2p.activity.TiXianActivity;
import com.example.wwwqq.p2p.activity.UserInfoActivity;
import com.example.wwwqq.p2p.bean.User;
import com.example.wwwqq.p2p.common.BaseActivity;
import com.example.wwwqq.p2p.common.BaseFragment;
import com.example.wwwqq.p2p.util.BitmapUtils;
import com.example.wwwqq.p2p.util.UIUtils;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.BindException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

@SuppressLint("ValidFragment")
public class MeFragment extends BaseFragment {

    ImageView ivTitleBack;
    TextView tvTitle;
    ImageView ivTitleSetting;
    Unbinder unbinder;
    ImageView ivMeIcon;
    RelativeLayout rlMeIcon;
    TextView tvMeName;
    RelativeLayout rlMe;
    ImageView recharge;
    ImageView withdraw;
    TextView llTouzi;
    TextView llTouziZhiguan;
    TextView llZichan;

    @Override
    protected RequestParams getParams() {
        return null;
    }

    @Override
    protected String getUrl() {
        return null;
    }

    @Override
    protected void initData(String content) {
        //判断用户是否已经登录
        isLogin();
    }

    private void isLogin() {
        //查看本地是否有用户的登录信息
        SharedPreferences sp = this.getActivity().getSharedPreferences("user_info", Context.MODE_PRIVATE);
        String name = sp.getString("name", "");
        if(TextUtils.isEmpty(name))
        {
            //本地没有保存过用户信息，给出提示，登录
            doLogin();
        }else
            {
                //已经登陆过，则直接加载用户的信息并显示
                doUser();
            }
    }

    //加载用户信息并显示
    private void doUser() {
        //读取本地保存的用户信息
        User user = ((BaseActivity) this.getActivity()).readUser();
        //获取对象信息，并设置给相应的视图显示
        tvMeName.setText(user.getName());

        //判断本地是否已经保存头像的图片,如果有,则不再执行联网操作
        boolean isExist = readImage();
        if(isExist)
        {
            return;
        }

        //使用Picasso联网获取图片
        Picasso.with(this.getActivity()).load(user.getImageurl()).transform(new Transformation() {
            @Override
            public Bitmap transform(Bitmap source) {//下载以后的内存中的Bitmap对象
                //压缩处理
                Bitmap bitmap = BitmapUtils.zoom(source, UIUtils.dp2px(62), UIUtils.dp2px(62));
                //圆形处理
                bitmap = BitmapUtils.circleBitmap(bitmap);
                //回收Bitmap资源
                source.recycle();
                return bitmap;
            }

            @Override
            public String key() {
                return "";//需要保证返回值不能为空，否则报错
            }
        }).into(ivMeIcon);
    }

    //给出提示，登录
    private void doLogin() {
        new AlertDialog.Builder(this.getActivity())
                .setTitle("提示")
                .setMessage("您还没有登录哦！")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        UIUtils.toast("进入登录页面",false);
                        ((BaseActivity)MeFragment.this.getActivity()).goToActivity(LoginActivity.class,null);
                    }
                })
                .setCancelable(false)
                .show();
    }

    public void initTitle() {
        ivTitleBack.setVisibility(View.INVISIBLE);
        tvTitle.setText("我的资产");
        ivTitleSetting.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.iv_title_setting)
    public void setting(View view)
    {
        //启动用户信息界面的Activity
        ((BaseActivity)this.getActivity()).goToActivity(UserInfoActivity.class,null);

    }

    @Override
    public int getlayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        ivTitleBack = rootView.findViewById(R.id.iv_title_back);
        tvTitle = rootView.findViewById(R.id.tv_title);
        ivTitleSetting = rootView.findViewById(R.id.iv_title_setting);
        ivMeIcon = rootView.findViewById(R.id.iv_me_icon);
        rlMeIcon = rootView.findViewById(R.id.rl_me_icon);
        tvMeName = rootView.findViewById(R.id.tv_me_name);
        rlMe = rootView.findViewById(R.id.rl_me);
        recharge = rootView.findViewById(R.id.recharge);
        withdraw = rootView.findViewById(R.id.withdraw);
        llTouzi = rootView.findViewById(R.id.ll_touzi);
        llTouziZhiguan = rootView.findViewById(R.id.ll_touzi_zhiguan);
        llZichan = rootView.findViewById(R.id.ll_zichan);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();

        //读取本地保存的图片
        readImage();
    }

    private boolean readImage() {
        File filesDir;
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))//判断sd卡是否挂载
        {
            //路径1:storage/sdcard/Android/data/包名/files
            filesDir = this.getActivity().getExternalFilesDir("");

        }else
        {//手机内部存储
            //路径:data/data/包名/files
            filesDir = this.getActivity().getFilesDir();
        }
        File file = new File(filesDir, "icon.png");

        if(file.exists())
        {
            //存储-->内存
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            ivMeIcon.setImageBitmap(bitmap);
            return true;
        }
        return false;
    }

    //设置"充值"操作
    @OnClick(R.id.recharge)
    public void reCharge(View view)
    {
        ((BaseActivity)this.getActivity()).goToActivity(ChongZhiActivity.class,null);
    }
    //设置"提现"操作
    @OnClick(R.id.withdraw)
    public void withdraw(View view)
    {
        ((BaseActivity)this.getActivity()).goToActivity(TiXianActivity.class,null);
    }
    //折线图
    @OnClick(R.id.ll_touzi)
    public void startLineChart(View view)
    {
        ((BaseActivity)this.getActivity()).goToActivity(LineChartActivity.class,null);
    }
    //柱状图
    @OnClick(R.id.ll_touzi_zhiguan)
    public void startBarChart(View view)
    {
        ((BaseActivity)this.getActivity()).goToActivity(BarChartActivity.class,null);
    }
    //饼图
    @OnClick(R.id.ll_zichan)
    public void startPieChart(View view)
    {
        ((BaseActivity)this.getActivity()).goToActivity(PieChartActivity.class,null);
    }

}
