package com.example.a11699.mydemo.ui.shopflipper;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a11699.mydemo.R;
import com.example.a11699.mydemo.base.BaseBean;
import com.example.a11699.mydemo.base.BaseObserver;
import com.example.a11699.mydemo.base.BasePresenter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者：余智强
 * 2019/9/19
 */
public class ShowShopPresenter extends BasePresenter<ShowShopView> {
    public ShowShopPresenter(ShowShopView baseView) {
        super(baseView);
    }

    int wherenum = 0;
    int allnum = 0;
    List<Shopbean.ShopBean> list = new ArrayList<>();

    public void getShop(int page, String pagesize) {
        Map map = new HashMap();
        map.put("page", page);
        map.put("pagesize", pagesize);
        String s = new Gson().toJson(map);

        addDisponsable(apiServer.getShop(s), new BaseObserver<BaseBean<Shopbean>>(baseView) {
            @Override
            public void onSuccess(BaseBean<Shopbean> o) {
                list.clear();
                wherenum = 0;
                list.addAll(o.getExtend().getShop());
                baseView.showresult(o.getExtend(), o.getExtend().pageallnum);
                allnum = o.getExtend().pageallnum;
            }

            @Override
            public void onError(String msg) {
                list.clear();
                baseView.showresult(null, 0);
            }
        });
    }

    View init1(LinearLayout linearLayout, int layout) {
        View view = LayoutInflater.from(context).inflate(layout, linearLayout, false);

        ImageView item1_img1 = view.findViewById(R.id.item1_img1);
        TextView item1_txt1 = view.findViewById(R.id.item1_txt1);
        add(item1_img1, item1_txt1);
        wherenum++;


        ImageView item1_img2 = view.findViewById(R.id.item1_img2);
        TextView item1_txt2 = view.findViewById(R.id.item1_txt2);
        add(item1_img2, item1_txt2);
        wherenum++;

        ImageView item1_img3 = view.findViewById(R.id.item1_img3);
        TextView item1_txt3 = view.findViewById(R.id.item1_txt3);
        add(item1_img3, item1_txt3);
        wherenum++;

        return view;
    }

    View init2(LinearLayout linearLayout, int layout) {
        View view = LayoutInflater.from(context).inflate(layout, linearLayout, false);

        ImageView item1_img1 = view.findViewById(R.id.item2_img1);
        TextView item1_txt1 = view.findViewById(R.id.item2_txt1);
        add(item1_img1, item1_txt1);
        wherenum++;


        ImageView item1_img2 = view.findViewById(R.id.item2_img2);
        TextView item1_txt2 = view.findViewById(R.id.item2_txt2);
        add(item1_img2, item1_txt2);
        wherenum++;

        ImageView item1_img3 = view.findViewById(R.id.item2_img3);
        TextView item1_txt3 = view.findViewById(R.id.item2_txt3);
        add(item1_img3, item1_txt3);
        wherenum++;

        return view;
    }

    View init3(LinearLayout linearLayout, int layout) {
        View view = LayoutInflater.from(context).inflate(layout, linearLayout, false);

        ImageView item1_img1 = view.findViewById(R.id.item3_img1);
        TextView item1_txt1 = view.findViewById(R.id.item3_txt1);
        add(item1_img1, item1_txt1);
        wherenum++;


        ImageView item1_img2 = view.findViewById(R.id.item3_img2);
        TextView item1_txt2 = view.findViewById(R.id.item3_txt2);
        add(item1_img2, item1_txt2);
        wherenum++;

        return view;
    }

    View init4(LinearLayout linearLayout, int layout) {
        View view = LayoutInflater.from(context).inflate(layout, linearLayout, false);

        ImageView item1_img1 = view.findViewById(R.id.item4_img1);
        TextView item1_txt1 = view.findViewById(R.id.item4_txt1);
        add(item1_img1, item1_txt1);
        wherenum++;

        return view;
    }

    void add(ImageView imageView, TextView textView) {
        if (wherenum < list.size()) {
            if (list.get(wherenum) != null) {
                imageView.setWillNotDraw(false);
                Glide.with(context).load(list.get(wherenum).getShopImg()).into(imageView);
                textView.setText(list.get(wherenum).getShopName());
            }
        }
    }
}
