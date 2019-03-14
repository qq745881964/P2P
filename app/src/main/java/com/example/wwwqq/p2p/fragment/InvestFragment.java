package com.example.wwwqq.p2p.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wwwqq.p2p.R;
import com.example.wwwqq.p2p.common.BaseFragment;
import com.example.wwwqq.p2p.util.UIUtils;
import com.loopj.android.http.RequestParams;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@SuppressLint("ValidFragment")
public class InvestFragment extends BaseFragment {

    ImageView ivTitleBack;
    TextView tvTitle;
    ImageView ivTitleSetting;
    TabPageIndicator tabpageInvest;
    ViewPager vpInvest;

    Unbinder unbinder;

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
        //1.加载三个不同的Fragment：ProductListFragment,ProductRecommondFragment,ProductHotFragment.
        initFragments();
        //ViewPager设置三个Fragment的显示
        MyAdapter myAdapter = new MyAdapter(getFragmentManager());
        vpInvest.setAdapter(myAdapter);
        //将TabPagerIndicator与ViewPager关联
        tabpageInvest.setViewPager(vpInvest);
    }

    private List<Fragment> fragmentList = new ArrayList<>();

    private void initFragments() {
        ProductListFragment productListFragment = new ProductListFragment();
        ProductRecommondFragment productRecommondFragment = new ProductRecommondFragment();
        ProductHotFragment productHotFragment = new ProductHotFragment();
        //添加到集合中
        fragmentList.add(productListFragment);
        fragmentList.add(productRecommondFragment);
        fragmentList.add(productHotFragment);
    }

    public void initTitle() {
        ivTitleBack.setVisibility(View.GONE);
        tvTitle.setText("投资");
        ivTitleSetting.setVisibility(View.GONE);
    }

    @Override
    public int getlayoutId() {
        return R.layout.fragment_invest;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        ivTitleBack = rootView.findViewById(R.id.iv_title_back);
        tvTitle = rootView.findViewById(R.id.tv_title);
        ivTitleSetting = rootView.findViewById(R.id.iv_title_setting);
        tabpageInvest = rootView.findViewById(R.id.tabpage_invest);
        vpInvest = rootView.findViewById(R.id.vp_invest);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragmentList.get(i);
        }

        @Override
        public int getCount() {
            return fragmentList == null ? 0 : fragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {

            //方式一：
//            if(position == 0){
//                return "全部理财";
//            }else if(position == 1){
//                return "推荐理财";
//            }else if(position == 2){
//                return "热门理财";
//            }

            return UIUtils.getStringArr(R.array.invest_tab)[position];
        }
    }

}
