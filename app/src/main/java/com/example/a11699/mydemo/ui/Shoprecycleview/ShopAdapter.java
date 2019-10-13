package com.example.a11699.mydemo.ui.Shoprecycleview;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a11699.mydemo.R;
import com.example.a11699.mydemo.base.BaseAdapter;
import com.example.a11699.mydemo.ui.shop.ShopInformationActivity;
import com.example.a11699.mydemo.ui.shopflipper.Shopbean;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

import okhttp3.Interceptor;

/**
 * 作者：余智强
 * 2019/9/24
 */
public class ShopAdapter extends BaseAdapter<Shopbean.ShopBean, BaseViewHolder> {
    private Context context;
    private static final int TYPE_ITEM = 0;  //普通Item View
    private static final int TYPE_FOOTER = 1;  //底部FootView
    private int load_more_status = 0; //上拉加载更多状态-默认为0
    public static final int PULLUP_LOAD_MORE = 0; //上拉加载更多
    public static final int LOADING_MORE = 1; //正在加载中
    public static final int NONE_INFO = 2;//暫無新數據
    Handler handler;
    public ShopAdapter(int layoutResId, List<Shopbean.ShopBean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    public BaseViewHolder getViewHolder(View view) {
        return new BaseViewHolder(view);
    }
    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    protected void convert(final BaseViewHolder helper, final Shopbean.ShopBean item) {
        RelativeLayout relativeLayout = (RelativeLayout) helper.getView(R.id.re_itemshop);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShopInformationActivity.class);
                intent.putExtra("obj",item);
                context.startActivity(intent);
            }
        });
        if (helper.getItemViewType() == TYPE_ITEM) {
            Glide.with(context).load(item.getShopImg()).into((ImageView) helper.getView(R.id.img_shopitem));
            helper.setText(R.id.txt_shopitemtitle, item.getShopName());
            helper.setText(R.id.txt_shopitemprice, "人均消費：" + item.getShopSpend());
            helper.setText(R.id.txt_shopiteminformation, item.getShopDesc());
        } else if (helper.getItemViewType() == TYPE_FOOTER) {
            helper.getView(R.id.re_normal).setVisibility(View.GONE);
            TextView textView = (TextView) helper.getView(R.id.tx_loadmore);
            if (load_more_status == PULLUP_LOAD_MORE) {
                textView.setText("正在加載。。。。");
            }
            helper.getView(R.id.ly_loadmore).setVisibility(View.VISIBLE);
            if (load_more_status == NONE_INFO) {
                textView.setText("已经是全部数据");
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        helper.getView(R.id.ly_loadmore).setVisibility(View.GONE);
                    }
                }, 1000);
            }
        }

    }

    public void changeMoreStatus(int status) {
        load_more_status = status;
        notifyDataSetChanged();
    }

}
