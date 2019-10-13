package com.example.a11699.mydemo.ui.shop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a11699.mydemo.R;
import com.example.a11699.mydemo.ui.shopflipper.Shopbean;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopInformationActivity extends AppCompatActivity {

    @BindView(R.id.img_shopitem)
    ImageView imgShopitem;
    @BindView(R.id.txt_shopitemtitle)
    TextView txtShopitemtitle;
    @BindView(R.id.txt_shopitemprice)
    TextView txtShopitemprice;
    @BindView(R.id.txt_shopiteminformation)
    TextView txtShopiteminformation;
    @BindView(R.id.tx_location)
    TextView txLocation;
    @BindView(R.id.tx_tel)
    TextView txTel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_information);
        ButterKnife.bind(this);
        Shopbean.ShopBean shopBean =(  Shopbean.ShopBean) getIntent().getSerializableExtra("obj");
        Glide.with(this).load(shopBean.getShopImg()).into(imgShopitem);
        txtShopitemtitle.setText(shopBean.getShopName());
        txtShopitemprice.setText("人均消费："+shopBean.getShopSpend());
        txtShopiteminformation.setText(shopBean.getShopDesc());
        txLocation.setText(shopBean.getShopAddress());
        txTel.setText(shopBean.getShopPhone());
    }
}
