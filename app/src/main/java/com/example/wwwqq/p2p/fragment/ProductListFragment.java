package com.example.wwwqq.p2p.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.wwwqq.p2p.R;
import com.example.wwwqq.p2p.adapter.ProductAdapter;
import com.example.wwwqq.p2p.bean.Product;
import com.example.wwwqq.p2p.common.AppNetConfig;
import com.example.wwwqq.p2p.common.BaseFragment;
import com.example.wwwqq.p2p.ui.MyTextView;
import com.loopj.android.http.RequestParams;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ProductListFragment extends BaseFragment {

    MyTextView tvProductTitle;
    ListView lvProductList;
    Unbinder unbinder;
    private List<Product> productList;

    @Override
    protected RequestParams getParams() {
        return null;
    }

    @Override
    protected String getUrl() {
        return AppNetConfig.PRODUCT;
    }

    @Override
    protected void initData(String content) {

        JSONObject jsonObject = JSON.parseObject(content);
        boolean success = jsonObject.getBoolean("success");
        if (success) {
            String data = jsonObject.getString("data");
            //获取集合数据
            productList = JSON.parseArray(data, Product.class);

            //方式四：抽取了，最好的方式.（可以作为选择）
            ProductAdapter productAdapter3 = new ProductAdapter(productList);
            lvProductList.setAdapter(productAdapter3);//显示列表
        }


    }

    @Override
    protected void initTitle() {

    }

    @Override
    public int getlayoutId() {
        return R.layout.fragment_productlist;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        tvProductTitle=rootView.findViewById(R.id.tv_product_title);
        lvProductList=rootView.findViewById(R.id.lv_product_list);
        return rootView;
    }
}
