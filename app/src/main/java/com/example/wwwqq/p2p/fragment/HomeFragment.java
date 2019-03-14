package com.example.wwwqq.p2p.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.wwwqq.p2p.R;
import com.example.wwwqq.p2p.bean.Index;
import com.example.wwwqq.p2p.common.AppNetConfig;
import com.example.wwwqq.p2p.common.BaseFragment;
import com.example.wwwqq.p2p.ui.RoundProgress;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@SuppressLint("ValidFragment")
public class HomeFragment extends BaseFragment {

    ImageView ivTitleBack;
    TextView tvTitle;
    ImageView ivTitleSetting;
    Banner bnHome;
    TextView tvHomeQi;
    TextView tvHomeYearrate;
    RoundProgress roundPro_home;
    Unbinder unbinder;
    int currentProgress;
    private ArrayList<String> arrayList_tu = new ArrayList<>();
    private ArrayList<String> arrayList_zi = new ArrayList<>();
    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
            roundPro_home.setMax(100);
            for (int i = 0; i <currentProgress; i++) {
                roundPro_home.setProgress(i+1);

                SystemClock.sleep(20);
                //强制绘制
//                roundPro_home.invalidate();//只有主线程才可以如此调用
                roundPro_home.postInvalidate();//主线程，分线程都可以如此调用
            }
        }
    };

    @Override
    protected RequestParams getParams() {
        return null;
    }

    @Override
    protected String getUrl() {
        return AppNetConfig.INDEX;
    }

    @Override
    protected void initData(String content) {
        if (!TextUtils.isEmpty(content)) {
            Log.i("hahaha",content);
            arrayList_tu.clear();
            arrayList_zi.clear();

            //解析json数据
            Index index1 = new Gson().fromJson(content, Index.class);
            for (int i = 0; i < index1.getImageArr().size(); i++) {
                arrayList_tu.add(index1.getImageArr().get(i).getIMAURL());
            }
            tvHomeQi.setText(index1.getProInfo().getName() + "");
            tvHomeYearrate.setText(index1.getProInfo().getYearRate() + "%");
            //获取数据中的进度值
            currentProgress = Integer.parseInt(index1.getProInfo().getProgress());
            //在分线程中，实现进度的动态变化
            new Thread(runnable).start();

            arrayList_zi.add("分享砍学费");
            arrayList_zi.add("人脉总动员");
            arrayList_zi.add("想不到你是这样的App");
            arrayList_zi.add("购物节，爱不单行");

            //设置内置样式，共有六种可以点入方法内逐一体验使用。
            bnHome.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
            //设置图片加载器，图片加载器在下方
            bnHome.setImageLoader(new MyLoader());
            //设置图片网址或地址的集合
            bnHome.setImages(arrayList_tu);
            //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
            bnHome.setBannerAnimation(Transformer.Default);
            //设置轮播图的标题集合
            bnHome.setBannerTitles(arrayList_zi);
            //设置轮播间隔时间
            bnHome.setDelayTime(2000);
            //设置是否为自动轮播，默认是“是”。
            bnHome.isAutoPlay(true);
            //设置指示器的位置，小点点，左中右。
            bnHome.setIndicatorGravity(BannerConfig.CENTER)
                    //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                    .setOnBannerListener(new OnBannerListener() {
                        @Override
                        public void OnBannerClick(int position) {
                            Toast.makeText(getActivity(), arrayList_zi.get(position) + "", Toast.LENGTH_SHORT).show();
                        }
                    })
                    //必须最后调用的方法，启动轮播图。
                    .start();
        }
    }

    @Override
    protected void initTitle() {
        ivTitleBack.setVisibility(View.GONE);
        tvTitle.setText("首页");
        ivTitleSetting.setVisibility(View.GONE);
    }

    @Override
    public int getlayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        ivTitleBack=rootView.findViewById(R.id.iv_title_back);
        tvTitle=rootView.findViewById(R.id.tv_title);
        ivTitleSetting=rootView.findViewById(R.id.iv_title_setting);
        bnHome=rootView.findViewById(R.id.bn_home);
        tvHomeQi=rootView.findViewById(R.id.tv_home_qi);
        tvHomeYearrate=rootView.findViewById(R.id.tv_home_yearrate);
        roundPro_home=rootView.findViewById(R.id.roundPro_home);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    //自定义的图片加载器
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load((String) path).into(imageView);
        }
    }

}
