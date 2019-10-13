package com.example.a11699.mydemo.ui.merchant;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a11699.mydemo.R;
import com.example.a11699.mydemo.ui.Shoprecycleview.ShopActivityTwo;
import com.example.a11699.mydemo.ui.shopflipper.ShowShopActivity;
import com.example.a11699.mydemo.util.PopwindowUtil;
import com.example.a11699.mydemo.widget.TitleAdapter;

import java.util.ArrayList;
import java.util.List;

public class MerchantActivity extends AppCompatActivity {
    EditText editText1, editText2, editText3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant);
        initview();
        initclick();
    }

    void initview() {
        addinformation(R.id.include_name, "商户名称:");
        addinformation(R.id.include_type, "商户类型:");
        addinformation(R.id.include_area, "商户区域:");
    }

    void initclick() {
        editText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final List<String> list = new ArrayList<>();
                list.add("餐饮美食");
                list.add("休息娱乐");
                list.add("全部类型");
                list.add("取消");
                initPop( list,editText2);
            }
        });
        editText3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final List<String> list = new ArrayList<>();
                list.add("附近5000米");
                list.add("附近1000米");
                list.add("全部区域");
                list.add("取消");
                initPop(list,editText3);
            }
        });
    }

    void addinformation(int id, String name) {
        View v = findViewById(id);
        TextView textView = v.findViewById(R.id.tv_search_shopname);
        textView.setText(name);
        switch (name) {
            case "商户名称:":
                editText1 = v.findViewById(R.id.ed_search_shopname);
                break;
            case "商户类型:":
                editText2 = v.findViewById(R.id.ed_search_shopname);
                editText2.setFocusable(false);
                editText2.setInputType(InputType.TYPE_NULL);
                break;
            case "商户区域:":
                editText3 = v.findViewById(R.id.ed_search_shopname);
                editText3.setFocusable(false);
                editText3.setInputType(InputType.TYPE_NULL);
                break;
        }
    }

    private void initPop(  List<String> lists, final EditText editText) {
        View view = PopwindowUtil.showShop(MerchantActivity.this,R.layout.search_bottom, findViewById(R.id.ly_search), 1);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycleview_search);
        TitleAdapter titleAdapter = new TitleAdapter(R.layout.itemtitle, lists);
        recyclerView.setAdapter(titleAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        titleAdapter.setOnClick(new TitleAdapter.OnClick() {
            @Override
            public void OnItemClick(String content) {
                if (!content.equals("取消")) {
                    editText.setText(content);
                }
                PopwindowUtil.dissmiss();
            }
        });
    }

}
