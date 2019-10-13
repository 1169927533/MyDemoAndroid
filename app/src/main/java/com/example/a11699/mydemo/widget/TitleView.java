package com.example.a11699.mydemo.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a11699.mydemo.ui.Shoprecycleview.ShopActivityTwo;
import com.example.a11699.mydemo.ui.location.LocationActivity;
import com.example.a11699.mydemo.ui.merchant.MerchantActivity;
import com.example.a11699.mydemo.ui.register.RegisterActivity;
import com.example.a11699.mydemo.R;
import com.example.a11699.mydemo.ui.login.LoginActivity;
import com.example.a11699.mydemo.ui.shake.ShakeActivity;
import com.example.a11699.mydemo.ui.shopflipper.ShowShopActivity;
import com.example.a11699.mydemo.util.PopwindowUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：余智强
 * 2019/9/17
 */
public class TitleView extends RelativeLayout {

    TextView tvmenu;
    ImageView imgback;//返回键
    TextView tvTitle;//标题
    TextView tvInfo;//最右边的字
    Context context;

    public TitleView(Context context) {
        this(context, null);
        initview(context);
    }

    public TitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        initview(context);

    }

    String menustring = null;

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initview(context);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TitleView);
        String name = array.getString(R.styleable.TitleView_title);

        if (null != name) {
            tvTitle.setText(name);
        }

        String content = array.getString(R.styleable.TitleView_contentsss);
        if (null != content) {
            tvInfo.setText(content);
        }

        menustring = array.getString(R.styleable.TitleView_menustring);
        if (null != content) {
            tvmenu.setText(menustring);
        }
        array.recycle();
    }

    public String getTvInfo() {
        return tvInfo.getText().toString();
    }

    void initview(final Context context) {
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.title, this);
        tvTitle = findViewById(R.id.tv_title);
        imgback = findViewById(R.id.img_back);
        tvInfo = findViewById(R.id.tv_Info);
        tvmenu = findViewById(R.id.tx_menu);
        tvInfo.setTextColor(Color.BLACK);
        tvTitle.setTextColor(Color.BLACK);
        tvmenu.setText(menustring);
        if (menustring != null && menustring.equals("菜单")) {
            imgback.setBackgroundResource(R.drawable.menu);
        } else {
            imgback.setBackgroundResource(R.drawable.back);
        }

        imgback.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (menustring != null && menustring.equals("菜单")) {
                    showPopupWindow(tvmenu);
                } else {
                    ((Activity) getContext()).finish();
                }
            }
        });

        tvInfo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvInfo.getText().toString().equals("登录")) {
                    context.startActivity(new Intent(context, LoginActivity.class));
                } else if (tvInfo.getText().toString().equals("注册")) {
                    context.startActivity(new Intent(context, RegisterActivity.class));
                }
                ((Activity) getContext()).finish();
            }
        });

    }


    private void showPopupWindow(View vv) {
        View view = PopwindowUtil.showShop((Activity) context, R.layout.lymenu, vv,2);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycleview_title);
        final List<String> list = new ArrayList<>();
        list.add("切换Reycleview展示");
        list.add("切换ViewFlipper展示");
        list.add("查找商户");
        list.add("定位演示");
        list.add("摇一摇搜周边");
        TitleAdapter titleAdapter = new TitleAdapter(R.layout.itemtitle, list);
        recyclerView.setAdapter(titleAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        titleAdapter.setOnClick(new TitleAdapter.OnClick() {
            @Override
            public void OnItemClick(String content) {
                switch (content){
                    case "切换Reycleview展示":
                        context.startActivity(new Intent(context, ShopActivityTwo.class));
                        break;
                    case "切换ViewFlipper展示":
                        context.startActivity(new Intent(context, ShowShopActivity.class));
                        break;
                    case "查找商户":
                        context.startActivity(new Intent(context, MerchantActivity.class));
                        break;
                    case "定位演示":
                        context.startActivity(new Intent(context, LocationActivity.class));
                        break;
                    case "摇一摇搜周边":
                        context.startActivity(new Intent(context, ShakeActivity.class));
                        break;
                }
                ((Activity) context).finish();
            }
        });
    }
}
