package com.example.a11699.mydemo.ui.shopflipper;

import android.content.Context;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.a11699.mydemo.R;
import com.example.a11699.mydemo.base.BaseActivity;
import com.example.a11699.mydemo.widget.TitleView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShowShopActivity extends BaseActivity<ShowShopPresenter> implements ShowShopView, GestureDetector.OnGestureListener {
    @BindView(R.id.vf_showshop)
    ViewFlipper vfShowshop;
    @BindView(R.id.tv_showshop)
    TextView tv_showshop;

    int page = 1;//当前页数
    int pagenumm = 0;//总的页数
    private GestureDetector detector;
    List<Shopbean.ShopBean> list = new ArrayList<>();
    String[] layoutStyle = {"1245", "1423", "2145", "2413", "4123", "4213", "1345", "1435", "3124", "3421", "4351", "4153"};

    @Override
    protected ShowShopPresenter createPresenter() {
        return new ShowShopPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_show_shop;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void initData() {
        detector = new GestureDetector(this);
        presenter.getShop(page, "9");
    }

    public void showresult(Shopbean shopbean, int pagenum) {
        pagenumm = (pagenum / 9) + (pagenum % 9 == 0 ? 0 : 1);
        if (shopbean == null) {
            showMsg("网络请求失败!");
        } else {
            this.list.clear();
            this.list.addAll(shopbean.getShop());
            vfShowshop.addView(initLinearLayout());
        }
        vfShowshop.setInAnimation(this, R.anim.right_in);
        vfShowshop.setOutAnimation(this, R.anim.righut_out);
        vfShowshop.showNext();
    }

    LinearLayout initLinearLayout() {
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setBackgroundColor(getResources().getColor(R.color.fff));
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        String style = layoutStyle[new Random().nextInt(layoutStyle.length)];
        for (int i = 0; i < style.length(); i++) {
            View child = null;
            switch (style.charAt(i)) {
                case '1':
                    child = presenter.init1(linearLayout, R.layout.hots_list_item1);
                    break;
                case '2':
                    child = presenter.init2(linearLayout, R.layout.hots_list_item2);
                    break;
                case '3':
                    child = presenter.init3(linearLayout, R.layout.hots_list_item3);
                    break;
                case '4':
                    child = presenter.init4(linearLayout, R.layout.hots_list_item4);
                    break;
                case '5':
                    child = presenter.init4(linearLayout, R.layout.hots_list_item4);
                    break;
            }

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(child.getLayoutParams());
            child.setLayoutParams(layoutParams);
            linearLayout.addView(child);

        }
        return linearLayout;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return this.detector.onTouchEvent(event);
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (e1.getX() - e2.getX() > 120) {
            if (page < pagenumm) {
                page++;
                presenter.getShop(page, "9");
                tv_showshop.setText(page + "/" + pagenumm);
                return true;
            }
        } else if (e1.getX() - e2.getX() < -120) {
            if (page > 1) {
                page--;
                tv_showshop.setText(page + "/" + pagenumm);
                vfShowshop.setInAnimation(this, R.anim.left_in);
                vfShowshop.setOutAnimation(this, R.anim.left_out);
                vfShowshop.showPrevious();

                return true;
            }
        }
        return false;
    }



    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }


}
