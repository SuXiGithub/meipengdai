package com.jiutong.meipengdai.http.model.requests;

import com.jiutong.meipengdai.entity.MovieBean;
import com.jiutong.meipengdai.http.HttpRequest;
import com.jiutong.meipengdai.http.HttpResultFunc;
import com.jiutong.meipengdai.http.SubscribeHandler;
import com.jiutong.meipengdai.http.model.services.MovieService;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by suxi on 2017/5/22.
 */

public class MovieRequest extends SubscribeHandler {

    /**
     * 用于获取豆瓣电影Top250的数据
     *
     * @param subscriber 由调用者传过来的观察者对象
     * @param start      起始位置
     * @param count      获取长度
     */
    public void getTopMovie(Subscriber<List<MovieBean>> subscriber, int start, int count) {
        Observable observable = HttpRequest.getInstance().retrofit.create(MovieService.class)
                .getTopMovie(start, count)
                .map(new HttpResultFunc<List<MovieBean>>());//统一处理服务器返回码，如需单独处理可以不加。

        toSubscribe(observable, subscriber);
    }

}
