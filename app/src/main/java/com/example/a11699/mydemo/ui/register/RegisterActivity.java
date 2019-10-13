package com.example.a11699.mydemo.ui.register;

import android.content.Context;
import android.widget.EditText;

import com.example.a11699.mydemo.R;
import com.example.a11699.mydemo.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterView {

    @BindView(R.id.ed_username)
    EditText edUsername;
    @BindView(R.id.ed_password)
    EditText edPassword;
    @BindView(R.id.ed_repeatpassword)
    EditText edRepeatpassword;

    @Override
    protected RegisterPresenter createPresenter() {
        return new RegisterPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @OnClick(R.id.button_register)
    public void onViewClicked() {
        register();
    }

   void register(){
        //判断用户名是否符合3-11位大小写字母或者数字
        if(!edUsername.getText().toString().trim().matches("^[a-zA-Z0-9_][a-zA-Z0-9_]{3,10}$")){
            showMsg("用户名不符合要求");
            return;
        }
        if(!edPassword.getText().toString().trim().matches("^[\\@A-Za-z0-9\\!\\#\\$\\%\\^\\&\\*\\.\\~]{5,22}$")){
            showMsg("密码不符合要求");
            return;
        }
        if(!edRepeatpassword.getText().toString().trim().equals(edPassword.getText().toString().trim())){
            showMsg("两次密码不相同");
            return;
        }
        presenter.register(edUsername.getText().toString().trim(),edPassword.getText().toString().trim());
   }

    @Override
    public void showresult(String content) {
        showMsg(content);
    }




}
