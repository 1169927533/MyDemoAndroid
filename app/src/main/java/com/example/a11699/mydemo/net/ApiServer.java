package com.example.a11699.mydemo.net;

import com.example.a11699.mydemo.base.BaseBean;
import com.example.a11699.mydemo.ui.shopflipper.Shopbean;

import io.reactivex.Observable;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 作者：余智强
 * 2019/9/17
 */
public interface ApiServer {
    //注册
    @FormUrlEncoded
    @POST("register")
    Observable<BaseBean<String>> register(@Field("content")String content);

    //登录
    @FormUrlEncoded
    @POST("user_login")
    Observable<BaseBean<String>> login(@Field("content")String content);

    //获取商家信息
    @FormUrlEncoded
    @POST("findShop")
    Observable<BaseBean<Shopbean>> getShop(@Field("content")String content);

}
