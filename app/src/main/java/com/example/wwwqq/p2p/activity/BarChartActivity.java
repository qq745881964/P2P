package com.example.wwwqq.p2p.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wwwqq.p2p.R;
import com.example.wwwqq.p2p.common.BaseActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BarChartActivity extends BaseActivity {


    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_title_setting)
    ImageView ivTitleSetting;
    @BindView(R.id.bar_chart)
    BarChart barChart;

    private Typeface mTf;//声明字体库

    @Override
    protected void initData() {

        mTf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");

        // apply styling
        barChart.setDescription("三星note7爆炸门事件后,三星品牌度");
        barChart.setDrawGridBackground(false);
        //是否绘制柱状图的背景
        barChart.setDrawBarShadow(false);

        //获取X轴对象
        XAxis xAxis = barChart.getXAxis();
        //设置X轴的显示位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //设置X轴的字体
        xAxis.setTypeface(mTf);
        //是否绘制X轴的网格线
        xAxis.setDrawGridLines(false);
        //是否绘制X轴的轴线
        xAxis.setDrawAxisLine(true);

        //获取左边Y轴对象
        YAxis leftAxis = barChart.getAxisLeft();
        //设置Y轴的字体
        leftAxis.setTypeface(mTf);
        //参数1:左边Y轴提供的区间的个数.   参数2:是否均匀分布这几个区间.   false:均匀.   true:不均匀.
        leftAxis.setLabelCount(5, false);
        //设置最大值距离顶部的距离
        leftAxis.setSpaceTop(20f);

        YAxis rightAxis = barChart.getAxisRight();
        rightAxis.setEnabled(false);

        //提供柱状图的数据
        BarData barData = generateDataBar();
        barData.setValueTypeface(mTf);

        // set data
        barChart.setData(barData);

        //设置Y轴方向的动画
        barChart.animateY(700);

    }

    @Override
    protected void initTitle() {
        ivTitleBack.setVisibility(View.VISIBLE);
        tvTitle.setText("柱状图demo");
        ivTitleSetting.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.iv_title_back)
    public void back(View view)
    {
        removeCurrentActivity();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bar_chart;
    }

    /**
     * generates a random ChartData object with just one DataSet
     *
     * @return
     */
    private BarData generateDataBar() {

        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();

        for (int i = 0; i < 12; i++) {
            entries.add(new BarEntry((int) (Math.random() * 70) + 30, i));
        }

        BarDataSet d = new BarDataSet(entries, "New DataSet 1");
        //设置相邻的柱状图之间的距离
        d.setBarSpacePercent(20f);
        d.setColors(ColorTemplate.VORDIPLOM_COLORS);
        //设置高亮的透明度
        d.setHighLightAlpha(255);

        BarData cd = new BarData(getMonths(), d);
        return cd;
    }

    private ArrayList<String> getMonths() {

        ArrayList<String> m = new ArrayList<String>();
        m.add("Jan");
        m.add("Feb");
        m.add("Mar");
        m.add("Apr");
        m.add("May");
        m.add("Jun");
        m.add("Jul");
        m.add("Aug");
        m.add("Sep");
        m.add("Okt");
        m.add("Nov");
        m.add("Dec");

        return m;
    }

}
