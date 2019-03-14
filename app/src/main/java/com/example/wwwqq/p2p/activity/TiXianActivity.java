package com.example.wwwqq.p2p.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wwwqq.p2p.R;
import com.example.wwwqq.p2p.common.BaseActivity;
import com.example.wwwqq.p2p.util.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TiXianActivity extends BaseActivity {


    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_title_setting)
    ImageView ivTitleSetting;
    @BindView(R.id.account_zhifubao)
    TextView accountZhifubao;
    @BindView(R.id.select_bank)
    RelativeLayout selectBank;
    @BindView(R.id.chongzhi_text)
    TextView chongzhiText;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.et_input_money)
    EditText etInputMoney;
    @BindView(R.id.chongzhi_text2)
    TextView chongzhiText2;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.btn_tixian)
    Button btnTixian;

    @Override
    protected void initData() {
        //设置当前的体现的button是不可操作的
        btnTixian.setClickable(false);
        etInputMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String money = etInputMoney.getText().toString().trim();
                if(TextUtils.isEmpty(money))
                {
                    //设置button不可操作的
                    btnTixian.setClickable(false);
                    //修改背景颜色
                    btnTixian.setBackgroundResource(R.drawable.btn_02);
                }else
                    {
                        //设置button可操作的
                        btnTixian.setClickable(true);
                        //修改背景颜色
                        btnTixian.setBackgroundResource(R.drawable.btn_01);
                    }
            }
        });
    }

    @Override
    protected void initTitle() {
        ivTitleBack.setVisibility(View.VISIBLE);
        tvTitle.setText("提现");
        ivTitleSetting.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.iv_title_back)
    public void back(View view)
    {
        removeCurrentActivity();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ti_xian;
    }

    @OnClick(R.id.btn_tixian)
    public void tiXian(View view)
    {
        //将要提现的数据数额发送给后台,由后台连接第三方支付平台,完成金额的提现操作.(略)
        UIUtils.toast("您的提现申请已被成功受理.审核通过后,24小时内,你的钱自然会到账",false);

        UIUtils.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                removeCurrentActivity();
            }
        },2000);
    }
}
