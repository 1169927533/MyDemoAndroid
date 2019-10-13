package com.example.a11699.mydemo.base;

import android.content.Context;

/**
 * 作者：余智强
 * 2019/9/17
 */
public interface BaseView {
    Context getContext();
    void showMsg(String msg);//toast消息展示
}
