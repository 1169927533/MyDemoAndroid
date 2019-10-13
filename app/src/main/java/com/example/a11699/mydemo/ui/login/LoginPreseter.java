package com.example.a11699.mydemo.ui.login;

import com.example.a11699.mydemo.base.BaseBean;
import com.example.a11699.mydemo.base.BaseObserver;
import com.example.a11699.mydemo.base.BasePresenter;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者：余智强
 * 2019/9/18
 */
public class LoginPreseter extends BasePresenter<LoginView> {
    public LoginPreseter(LoginView baseView) {
        super(baseView);
    }
    public void userlogin(String username,String password){
        Map map = new HashMap();
        map.put("username",username);
        map.put("userpassword",password);
        String s = new Gson().toJson(map);
        addDisponsable(apiServer.login(s),new BaseObserver<BaseBean<String>>(baseView){
            @Override
            public void onSuccess(BaseBean<String> o) {
                baseView.showresult(o.getCode()+"");
            }

            @Override
            public void onError(String msg) {
                baseView.showresult(msg);
            }
        });
    }
}
