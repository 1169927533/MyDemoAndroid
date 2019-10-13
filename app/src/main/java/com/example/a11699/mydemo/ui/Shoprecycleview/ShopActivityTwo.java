package com.example.a11699.mydemo.ui.Shoprecycleview;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.a11699.mydemo.R;
import com.example.a11699.mydemo.base.BaseActivity;
import com.example.a11699.mydemo.ui.shopflipper.Shopbean;
import com.example.a11699.mydemo.ui.shopflipper.ShowShopPresenter;
import com.example.a11699.mydemo.ui.shopflipper.ShowShopView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopActivityTwo extends BaseActivity<ShowShopPresenter> implements ShowShopView {
    @BindView(R.id.re_shoptwo)
    RecyclerView reShoptwo;
    ShopAdapter shopAdapter;

    int page = 1;
    int lastVisibleItem = 0;
    LinearLayoutManager layoutManager;
    List<Shopbean.ShopBean> list = new ArrayList<>();

    @Override
    protected ShowShopPresenter createPresenter() {
        return new ShowShopPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop_two;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void initData() {
        super.initData();
        shopAdapter = new ShopAdapter(R.layout.ly_shopitem, list, this);
        presenter.getShop(page, "15");
        reShoptwo.setAdapter(shopAdapter);
        layoutManager = new LinearLayoutManager(this);
        reShoptwo.setLayoutManager(layoutManager);

        reShoptwo.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == shopAdapter.getItemCount()) {//判断是不是滑到了最后一项
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            page++;
                            presenter.getShop(page, "15");
                            shopAdapter.changeMoreStatus(ShopAdapter.PULLUP_LOAD_MORE);
                        }
                    }, 1000);
                }

            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
            }
        });
    }

    int sizeddd;
    @Override
    public void showresult(Shopbean shopbean, int pagenum) {
        if(shopbean!=null){
        this.list.addAll(shopbean.getShop());
        shopAdapter.notifyItemRangeChanged(shopbean.getShop().size(), list.size());//在两者之间添加数据
        if (sizeddd == list.size()) {
            page--;
            shopAdapter.changeMoreStatus(ShopAdapter.NONE_INFO);
        } else {
            shopAdapter.changeMoreStatus(ShopAdapter.PULLUP_LOAD_MORE);
        }
        sizeddd = this.list.size();
        }
    }

}
