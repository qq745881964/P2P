package com.example.wwwqq.p2p.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wwwqq.p2p.R;
import com.example.wwwqq.p2p.common.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class GuiGuInvestActivity extends BaseActivity {

    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_title_setting)
    ImageView ivTitleSetting;

    @Override
    protected void initData() {

    }

    @Override
    protected void initTitle() {
        ivTitleBack.setVisibility(View.VISIBLE);
        tvTitle.setText("关于硅谷理财");
        ivTitleSetting.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.iv_title_back)
    public void back(View view){
        removeCurrentActivity();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gui_gu_invest;
    }

}
