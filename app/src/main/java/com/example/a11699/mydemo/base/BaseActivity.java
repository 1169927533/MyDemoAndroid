package com.example.a11699.mydemo.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：余智强
 * 2019/9/17
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView{
    public Context context;
    private ProgressDialog dialog;
    private Toast toast;
    protected P presenter;
    private Unbinder unbinder;

    protected abstract  P createPresenter();
    protected abstract int getLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        context = this;
        presenter = createPresenter();
        initData();
    }
    public void initData(){

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
        unbinder.unbind();
    }
    public void showtoast(String s) {
        if (toast == null) {
            toast = Toast.makeText(this, s, Toast.LENGTH_LONG);
        } else {
            toast.cancel();
            toast = Toast.makeText(this, s, Toast.LENGTH_LONG);
        }
        toast.show();
    }

    @Override
    public void showMsg(String msg) {
        showtoast(msg);
    }
}
