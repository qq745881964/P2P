package com.example.wwwqq.p2p.common;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wwwqq.p2p.ui.LoadingPage;
import com.loopj.android.http.RequestParams;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {

    Unbinder unbinder;
    LoadingPage loadingPage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        loadingPage = new LoadingPage(container.getContext()) {

            @Override
            public int layoutId() {
                return getlayoutId();
            }

            @Override
            protected void onSuccss(LoadingPage.ResultState resultState, View view_success) {
                unbinder=ButterKnife.bind(this, view_success);

                initTitle();//初始化Title

                initData(resultState.getContent());//初始化数据
            }

            @Override
            protected RequestParams layoutParams() {
                return getParams();
            }

            @Override
            public String url() {
                return getUrl();
            }
        };

        return loadingPage;
    }
    //为了保证LoadingPage不为null
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    protected abstract RequestParams getParams();

    protected abstract String getUrl();

    //初始化界面的数据
    protected abstract void initData(String content);

    //初始化title
    protected abstract void initTitle();

    //提供布局
    public abstract int getlayoutId();

    public void show()
    {
        loadingPage.show();
    }
}
