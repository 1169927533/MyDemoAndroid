package com.example.a11699.mydemo.ui.register;

import com.example.a11699.mydemo.base.BaseBean;
import com.example.a11699.mydemo.base.BaseObserver;
import com.example.a11699.mydemo.base.BasePresenter;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者：余智强
 * 2019/9/17
 */
public class RegisterPresenter extends BasePresenter<RegisterView> {
    public RegisterPresenter(RegisterView baseView) {
        super(baseView);
    }

    public void register(String username,String password){
        Map map = new HashMap();
        map.put("username",username);
        map.put("userpassword",password);
        String s = new Gson().toJson(map);
        addDisponsable(apiServer.register(s),new BaseObserver<BaseBean<String>>(baseView){
            @Override
            public void onSuccess(BaseBean<String> o) {
                 baseView.showresult(o.getMsg());
            }

            @Override
            public void onError(String msg) {
                baseView.showresult(msg);
            }
        });
    }

}
