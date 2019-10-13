package com.example.a11699.mydemo.base;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：余智强
 * 2019/9/23
 */
public abstract class BaseAdapter<RR,W extends BaseViewHolder> extends RecyclerView.Adapter<W> {

    public List<RR> mData;
    private int mLayoutResId;

    public BaseAdapter(@LayoutRes int layoutResId,   List<RR> data) {
        this.mData = data == null ? new ArrayList<RR>() : data;
        if (layoutResId != 0) {
            this.mLayoutResId = layoutResId;
        }
    }


    @Override
    public W onCreateViewHolder(  ViewGroup viewGroup, int i) {
        View view =  LayoutInflater.from(viewGroup.getContext()).inflate(mLayoutResId,viewGroup,false);
        return getViewHolder(view);
    }

    public abstract W getViewHolder(View view);

    @Override
    public void onBindViewHolder( W kqq, int i) {
            convert(kqq,mData.get(i));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    protected abstract void convert(W helper, RR item);

    public interface OnItemClick {
        void OnClick(Object item);
    }

    public OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }


}
