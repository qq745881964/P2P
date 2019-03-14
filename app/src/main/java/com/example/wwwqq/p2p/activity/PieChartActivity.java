package com.example.wwwqq.p2p.activity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wwwqq.p2p.R;
import com.example.wwwqq.p2p.common.BaseActivity;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PieChartActivity extends BaseActivity {


    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_title_setting)
    ImageView ivTitleSetting;
    @BindView(R.id.pie_chart)
    PieChart pieChart;

    private Typeface mTf;//声明字体库

    @Override
    protected void initData() {

        mTf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");

        // apply styling
        pieChart.setDescription("Android厂商2016年手机占有率");
        pieChart.setHoleRadius(52f);//最内层的圆的半径
        pieChart.setTransparentCircleRadius(57f);
        pieChart.setCenterText("Android\n厂商占比");
        pieChart.setCenterTextTypeface(mTf);
        pieChart.setCenterTextSize(18f);
        //是否使用百分比.   true:各部分的百分比的和是100%
        pieChart.setUsePercentValues(true);

        PieData pieData = generateDataPie();

        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTypeface(mTf);
        pieData.setValueTextSize(11f);
        pieData.setValueTextColor(Color.WHITE);
        // set data
        pieChart.setData(pieData);

        //获取右上角的描述结构的对象
        Legend l = pieChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        //相邻的entry在Y轴上的间距
        l.setYEntrySpace(0f);
        //第一个entry距离最顶端的间距
        l.setYOffset(0f);

        // do not forget to refresh the chart
        // holder.chart.invalidate();
        pieChart.animateXY(900, 900);
    }

    @Override
    protected void initTitle() {
        ivTitleBack.setVisibility(View.VISIBLE);
        tvTitle.setText("饼状图demo");
        ivTitleSetting.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.iv_title_back)
    public void back(View view)
    {
        removeCurrentActivity();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pie_chart;
    }

    /**
     * generates a random ChartData object with just one DataSet
     *
     * @return
     */
    private PieData generateDataPie() {

        ArrayList<Entry> entries = new ArrayList<Entry>();

        for (int i = 0; i < 4; i++) {
            entries.add(new Entry((int) (Math.random() * 70) + 30, i));
        }

        PieDataSet d = new PieDataSet(entries, "");

        //相邻模块的间距
        d.setSliceSpace(2f);
        d.setColors(ColorTemplate.VORDIPLOM_COLORS);

        PieData cd = new PieData(getQuarters(), d);
        return cd;
    }

    private ArrayList<String> getQuarters() {

        ArrayList<String> q = new ArrayList<String>();
        q.add("三星");
        q.add("华为");
        q.add("oppo");
        q.add("vivo");

        return q;
    }

}
