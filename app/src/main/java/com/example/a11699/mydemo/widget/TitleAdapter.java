package com.example.a11699.mydemo.widget;

import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseViewHolder;
import com.example.a11699.mydemo.R;
import com.example.a11699.mydemo.base.BaseAdapter;

import java.util.List;

/**
 * 作者：余智强
 * 2019/9/25
 */
public class TitleAdapter extends BaseAdapter<String, BaseViewHolder> {
    public TitleAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    public BaseViewHolder getViewHolder(View view) {
        return new BaseViewHolder(view);
    }

    @Override
    protected void convert(BaseViewHolder helper, final String item) {
        helper.setText(R.id.tv_item_title, item);
        LinearLayout linearLayout = (LinearLayout)helper.getView(R.id.ly_item_title);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.OnItemClick(item);
            }
        });
    }

   public interface OnClick {
        void OnItemClick(String content);
    }
    public OnClick onClick;

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }
}
