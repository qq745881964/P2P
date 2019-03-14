package com.example.wwwqq.p2p.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wwwqq.p2p.R;
import com.example.wwwqq.p2p.common.BaseFragment;
import com.example.wwwqq.p2p.ui.FlowLayout;
import com.example.wwwqq.p2p.util.UIUtils;
import com.loopj.android.http.RequestParams;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ProductHotFragment extends BaseFragment {

    FlowLayout flowHot;
    Unbinder unbinder;

    //提供装配的数据
    private String[] datas = new String[]{"新手福利计划", "财神道90天计划", "硅谷钱包计划", "30天理财计划(加息2%)", "180天理财计划(加息5%)", "月月升理财计划(加息10%)",
            "中情局投资商业经营", "大学老师购买车辆", "屌丝下海经商计划", "美人鱼影视拍摄投资", "Android培训老师自己周转", "养猪场扩大经营",
            "旅游公司扩大规模", "铁路局回款计划", "屌丝迎娶白富美计划"
    };

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
        for (int i = 0; i < datas.length; i++) {
            TextView tv = new TextView(getContext());

            //设置属性
            tv.setText(datas[i]);
            ViewGroup.MarginLayoutParams mp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mp.leftMargin = UIUtils.dp2px(10);
            mp.rightMargin = UIUtils.dp2px(10);
            mp.topMargin = UIUtils.dp2px(10);
            mp.bottomMargin = UIUtils.dp2px(10);
            tv.setLayoutParams(mp);//设置边距

            int padding = UIUtils.dp2px(5);
            tv.setPadding(padding,padding,padding,padding);//设置内边距

            tv.setTextSize(UIUtils.dp2px(15));

            flowHot.addView(tv);

        }
    }

    @Override
    protected void initTitle() {

    }

    @Override
    public int getlayoutId() {
        return R.layout.fragment_producthot;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        flowHot=rootView.findViewById(R.id.flow_hot);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
