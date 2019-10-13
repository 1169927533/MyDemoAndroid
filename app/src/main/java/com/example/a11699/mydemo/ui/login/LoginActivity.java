package com.example.a11699.mydemo.ui.login;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.a11699.mydemo.R;
import com.example.a11699.mydemo.base.BaseActivity;
import com.example.a11699.mydemo.ui.shopflipper.ShowShopActivity;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

public class LoginActivity extends BaseActivity<LoginPreseter> implements LoginView {

    @BindView(R.id.ed_login_username)
    EditText edLoginUsername;
    @BindView(R.id.ed_login_password)
    EditText edLoginPassword;

    @Override
    protected LoginPreseter createPresenter() {
        return new LoginPreseter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @OnClick({R.id.button_login, R.id.button_threelogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_login:
                login();
                break;
            case R.id.button_threelogin:
                threeLogin();
                break;
        }
    }

    void login() {
        //判断用户名是否符合3-11位大小写字母或者数字
        if (!edLoginUsername.getText().toString().trim().matches("^[a-zA-Z0-9_][a-zA-Z0-9_]{3,10}$")) {
            showMsg("用户名不符合要求");
            return;
        }
        if (!edLoginPassword.getText().toString().trim().matches("^[\\@A-Za-z0-9\\!\\#\\$\\%\\^\\&\\*\\.\\~]{5,22}$")) {
            showMsg("密码不符合要求");
            return;
        }
        presenter.userlogin(edLoginUsername.getText().toString().trim(), edLoginPassword.getText().toString().trim());
    }

    @Override
    public void showresult(String msg) {
        if (msg.equals("100")) {
            showMsg("登录成功");
            startActivity(new Intent(this, ShowShopActivity.class));
        } else {
            showMsg("登录失败");
        }
    }

    void threeLogin() {
        Platform plat = ShareSDK.getPlatform(QQ.NAME);
        //移除授权状态和本地缓存，下次授权会重新授权
        plat.removeAccount(true);
        //SSO授权，传false默认是客户端授权
        plat.SSOSetting(false);
        //授权回调监听，监听oncomplete，onerror，oncancel三种状态
        plat.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                showMsg("授权完成");
                startActivity(new Intent(LoginActivity.this, ShowShopActivity.class));
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                showMsg("失败");
            }

            @Override
            public void onCancel(Platform platform, int i) {
                showMsg("取消");
            }
        });
        ShareSDK.setActivity(LoginActivity.this);
        //要数据不要功能，主要体现在不会重复出现授权界面
        plat.showUser(null);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_login,null);
    }
}
