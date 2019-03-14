package com.example.wwwqq.p2p.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.wwwqq.p2p.R;
import com.example.wwwqq.p2p.bean.Product;
import com.example.wwwqq.p2p.ui.RoundProgress;
import com.example.wwwqq.p2p.util.UIUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductAdapter extends BaseAdapter
{
    private List<Product> productList;

    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public int getCount() {
        return productList == null ? 0 : productList.size() + 1;
    }

    @Override
    public Object getItem(int i) {
        return productList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        int itemViewType = getItemViewType(i);
        if(itemViewType == 0)
        {
            TextView tv=new TextView(viewGroup.getContext());
            tv.setText("与子同行，奈何覆舟");
            tv.setTextColor(UIUtils.getColor(R.color.text_progress));
            tv.setTextSize(UIUtils.dp2px(20));
            return tv;
        }

        if(i>3)
        {
            i--;
        }

        ViewHolder holder;
        if(view==null)
        {
            view = View.inflate(viewGroup.getContext(),R.layout.item_product_list , null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else
            {
                holder = (ViewHolder) view.getTag();
            }

        //装配数据
        Product product = productList.get(i);
        holder.pMinnum.setText(product.memberNum);
        holder.pMinzouzi.setText(product.minTouMoney);
        holder.pMoney.setText(product.money);
        holder.pName.setText(product.name);
        holder.pProgresss.setProgress(Integer.parseInt(product.progress));
        holder.pSuodingdays.setText(product.suodingDays);
        holder.pYearlv.setText(product.yearRate);
        return view;
    }

    //不同的position位置上，显示的具体的item的item的type值
    @Override
    public int getItemViewType(int position) {
        if(position==3)
        {
            return 0;
        }else
            {
                return 1;
            }
    }

    //返回不同类型的item的个数
    @Override
    public int getViewTypeCount() {
        return 2;
    }

    static class ViewHolder {
        @BindView(R.id.p_name)
        TextView pName;
        @BindView(R.id.p_money)
        TextView pMoney;
        @BindView(R.id.p_yearlv)
        TextView pYearlv;
        @BindView(R.id.p_suodingdays)
        TextView pSuodingdays;
        @BindView(R.id.p_minzouzi)
        TextView pMinzouzi;
        @BindView(R.id.p_minnum)
        TextView pMinnum;
        @BindView(R.id.p_progresss)
        RoundProgress pProgresss;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
