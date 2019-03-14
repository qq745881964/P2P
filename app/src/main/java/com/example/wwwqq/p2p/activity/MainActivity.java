package com.example.wwwqq.p2p.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wwwqq.p2p.R;
import com.example.wwwqq.p2p.common.ActivityManager;
import com.example.wwwqq.p2p.common.BaseActivity;
import com.example.wwwqq.p2p.fragment.HomeFragment;
import com.example.wwwqq.p2p.fragment.InvestFragment;
import com.example.wwwqq.p2p.fragment.MeFragment;
import com.example.wwwqq.p2p.fragment.MoreFragment;
import com.example.wwwqq.p2p.util.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.fl_main)
    FrameLayout flMain;
    @BindView(R.id.iv_main_home)
    ImageView ivMainHome;
    @BindView(R.id.tv_main_home)
    TextView tvMainHome;
    @BindView(R.id.ll_main_home)
    LinearLayout llMainHome;
    @BindView(R.id.iv_main_invest)
    ImageView ivMainInvest;
    @BindView(R.id.tv_main_invest)
    TextView tvMainInvest;
    @BindView(R.id.ll_main_invest)
    LinearLayout llMainInvest;
    @BindView(R.id.iv_main_me)
    ImageView ivMainMe;
    @BindView(R.id.tv_main_me)
    TextView tvMainMe;
    @BindView(R.id.ll_main_me)
    LinearLayout llMainMe;
    @BindView(R.id.iv_main_more)
    ImageView ivMainMore;
    @BindView(R.id.tv_main_more)
    TextView tvMainMore;
    @BindView(R.id.ll_main_more)
    LinearLayout llMainMore;

    private HomeFragment homeFragment;
    private InvestFragment investFragment;
    private MeFragment meFragment;
    private MoreFragment moreFragment;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void initData() {
        setSelect(0);//默认显示首页

//        String str=null;
//        if(str.equals("abc"))
//        {
//            Log.e("TAG","abc");
//        }
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @OnClick({R.id.ll_main_home,R.id.ll_main_invest,R.id.ll_main_me,R.id.ll_main_more})
    public void showTab(View view)
    {
        switch (view.getId())
        {
            case R.id.ll_main_home:
                setSelect(0);
                break;
            case R.id.ll_main_invest:
                setSelect(1);
                break;
            case R.id.ll_main_me:
                setSelect(2);
                break;
            case R.id.ll_main_more:
                setSelect(3);
                break;
        }
    }

    private void setSelect(int i) {

        FragmentManager fragmentManager=this.getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        //隐藏所有Fragment的显示
        hideFragments();
        //重置ImageView和TextView的显示状态
        resetTab();

        switch (i)
        {
            case 0:
                if(homeFragment==null)
                {
                    homeFragment=new HomeFragment();//创建对象后，并不会马上调用生命周期方法，而是在Commit()之后，方才调用
                    fragmentTransaction.add(R.id.fl_main,homeFragment);
                }
                fragmentTransaction.show(homeFragment);//显示当前的fragment

                //改变选中项的图片和文本颜色的变化
                ivMainHome.setImageResource(R.drawable.bottom02);
                tvMainHome.setTextColor(UIUtils.getColor(R.color.home_back_selected));
                break;
            case 1:
                if(investFragment==null)
                {
                    investFragment=new InvestFragment();//创建对象后，并不会马上调用生命周期方法，而是在Commit()之后，方才调用
                    fragmentTransaction.add(R.id.fl_main,investFragment);
                }
                fragmentTransaction.show(investFragment);//显示当前的fragment
                //改变选中项的图片和文本颜色的变化
                ivMainInvest.setImageResource(R.drawable.bottom04);
                tvMainInvest.setTextColor(UIUtils.getColor(R.color.home_back_selected));
                break;
            case 2:
                if(meFragment==null)
                {
                    meFragment=new MeFragment();//创建对象后，并不会马上调用生命周期方法，而是在Commit()之后，方才调用
                    fragmentTransaction.add(R.id.fl_main,meFragment);
                }
                fragmentTransaction.show(meFragment);//显示当前的fragment
                //改变选中项的图片和文本颜色的变化
                ivMainMe.setImageResource(R.drawable.bottom06);
                tvMainMe.setTextColor(UIUtils.getColor(R.color.home_back_selected01));
                break;
            case 3:
                if(moreFragment==null)
                {
                    moreFragment=new MoreFragment();//创建对象后，并不会马上调用生命周期方法，而是在Commit()之后，方才调用
                    fragmentTransaction.add(R.id.fl_main,moreFragment);
                }
                fragmentTransaction.show(moreFragment);//显示当前的fragment
                //改变选中项的图片和文本颜色的变化
                ivMainMore.setImageResource(R.drawable.bottom08);
                tvMainMore.setTextColor(UIUtils.getColor(R.color.home_back_selected));
                break;
        }
        fragmentTransaction.commit();//提交事务
    }

    private void resetTab() {
        ivMainHome.setImageResource(R.drawable.bottom01);
        ivMainInvest.setImageResource(R.drawable.bottom03);
        ivMainMe.setImageResource(R.drawable.bottom05);
        ivMainMore.setImageResource(R.drawable.bottom07);

        tvMainHome.setTextColor(UIUtils.getColor(R.color.home_back_unselected));
        tvMainInvest.setTextColor(UIUtils.getColor(R.color.home_back_unselected));
        tvMainMe.setTextColor(UIUtils.getColor(R.color.home_back_unselected));
        tvMainMore.setTextColor(UIUtils.getColor(R.color.home_back_unselected));
    }

    private void hideFragments() {
        if(homeFragment!=null)
        {
            fragmentTransaction.hide(homeFragment);
        }
        if(investFragment!=null)
        {
            fragmentTransaction.hide(investFragment);
        }
        if(meFragment!=null)
        {
            fragmentTransaction.hide(meFragment);
        }
        if(moreFragment!=null)
        {
            fragmentTransaction.hide(moreFragment);
        }
    }

   //重写onkeyUp(),实现连续两次点击方可退出当前应用

    private boolean flag=true;
    private static final int WHAT_RESET_BACK = 1;
    private Handler handler=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            Log.e("TAG","handleMessage");
            switch (msg.what)
            {
                case WHAT_RESET_BACK:
                    flag=true;//复原
                    break;
            }
        }
    };

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        if(keyCode==KeyEvent.KEYCODE_BACK && flag)
        {
            Toast.makeText(this, "再点击一次，退出当前应用 ", Toast.LENGTH_SHORT).show();
            flag=false;
            //发送延迟消息
            handler.sendEmptyMessageDelayed(WHAT_RESET_BACK,2000);
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    //为了避免出现内存的泄露，需要在onDestroy()中,移除所有未被执行的消息

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        //方式一
//        handler.removeMessages(WHAT_RESET_BACK);//移除指定id的所有消息
        //方 式二
        handler.removeCallbacksAndMessages(null);//移除所有的未被执行的消息
    }
}
