package com.example.a11699.mydemo.base;

import android.content.Context;

import com.example.a11699.mydemo.net.ApiRetrofit;
import com.example.a11699.mydemo.net.ApiServer;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者：余智强
 * 2019/9/17
 */
public class BasePresenter<T extends BaseView> {
    private CompositeDisposable compositeDisposable;
    public T baseView;
    public Context context;
    public ApiServer apiServer = ApiRetrofit.getInstance().getApiService();

    public BasePresenter(T baseView) {
        this.baseView = baseView;
        this.context = baseView.getContext();

    }
    public void detachView(){
        baseView = null;
        removeDisposable();
    }

    public T getBaseView() {
        return baseView;
    }

    public void addDisponsable(Observable<?>observable,BaseObserver observer){
        if (compositeDisposable == null){
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(
                observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer));
    }
    public void removeDisposable() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
            compositeDisposable.clear();

        }
    }


}
