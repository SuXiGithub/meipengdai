package com.jiutong.meipengdai.http;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 为所有请求添加观察者以及回调
 * Created by suxi on 2017/5/22.
 */

public class SubscribeHandler {
    /**
     * @param o
     * @param s
     * @param <T>
     */
    public <T> void toSubscribe(Observable<T> o, Subscriber<T> s) {
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }
}
