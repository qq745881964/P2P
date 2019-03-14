package com.example.wwwqq.p2p.activity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wwwqq.p2p.R;
import com.example.wwwqq.p2p.common.BaseActivity;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LineChartActivity extends BaseActivity {


    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_title_setting)
    ImageView ivTitleSetting;
    @BindView(R.id.line_chart)
    LineChart lineChart;

    private Typeface mTf;//声明字体库

    @Override
    protected void initData() {
        mTf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        //设置当前的折线图的描述
        lineChart.setDescription("林丹出轨事件的关注度");
        //是否会之网格背景
        lineChart.setDrawGridBackground(false);
        //获取当前的X轴对象
        XAxis xAxis = lineChart.getXAxis();
        //设置X轴的显示位置
        xAxis.setPosition(XAxis.XAxisPosition.TOP);
        //设置X轴的字体
        xAxis.setTypeface(mTf);
        //是否绘制X轴的网格线
        xAxis.setDrawGridLines(false);
        //是否绘制X轴的轴线
        xAxis.setDrawAxisLine(true);

        //获取左边的Y轴对象
        YAxis leftAxis = lineChart.getAxisLeft();
        //设置左边Y轴的字体
        leftAxis.setTypeface(mTf);
        //参数1:左边Y轴提供的区间的个数.  参数2:是否均匀分布这几个区间.   false:均匀.   true:不均匀.
        leftAxis.setLabelCount(5, false);

        //获取右边的Y轴对象
        YAxis rightAxis = lineChart.getAxisRight();
        //将右边的Y轴设置为不显示的
        rightAxis.setEnabled(false);

        //提供折现数据 (通常情况下,折现的数据来自于服务器的数据)
        LineData lineData = generateDataLine(1);
        lineChart.setData(lineData);

        //设置X轴方向的动画,执行时间是750.
        //不需要在执行,invalidate();
        lineChart.animateX(750);
    }

    @Override
    protected void initTitle() {
        ivTitleBack.setVisibility(View.VISIBLE);
        tvTitle.setText("折线图demo");
        ivTitleSetting.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.iv_title_back)
    public void back(View view)
    {
        removeCurrentActivity();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_line_chart;
    }

    /**
     * generates a random ChartData object with just one DataSet
     *
     * @return
     */
    private LineData generateDataLine(int cnt) {
        //折线1
        ArrayList<Entry> e1 = new ArrayList<Entry>();
        //提供折现中点的数据
        for (int i = 0; i < 12; i++) {
            e1.add(new Entry((int) (Math.random() * 65) + 40, i));
        }

        LineDataSet d1 = new LineDataSet(e1, "New DataSet " + cnt + ", (1)");
        //设置折现的宽度
        d1.setLineWidth(2.5f);
        //设置小圆圈的尺寸
        d1.setCircleSize(4.5f);
        //设置高亮的颜色
        d1.setHighLightColor(Color.rgb(244, 117, 117));
        //是否显示小圆圈对应的数据
        d1.setDrawValues(false);

        //折线2
        ArrayList<Entry> e2 = new ArrayList<Entry>();

        for (int i = 0; i < 12; i++) {
            e2.add(new Entry(e1.get(i).getVal() - 30, i));
        }

        LineDataSet d2 = new LineDataSet(e2, "New DataSet " + cnt + ", (2)");
        d2.setLineWidth(2.5f);
        d2.setCircleSize(4.5f);
        d2.setHighLightColor(Color.rgb(244, 117, 117));
        d2.setColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        d2.setCircleColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        d2.setDrawValues(false);

        ArrayList<LineDataSet> sets = new ArrayList<LineDataSet>();
        sets.add(d1);
        sets.add(d2);

        LineData cd = new LineData(getMonths(), sets);
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
