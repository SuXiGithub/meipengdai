package com.jiutong.meipengdai.http;

import com.jiutong.meipengdai.entity.BufferRespond;

import rx.functions.Func1;

/**
 * Created by suxi on 2017/5/22.
 */

public class HttpResultFunc<T> implements Func1<BufferRespond<T>, T> {

    @Override
    public T call(BufferRespond<T> httpResult) {
        if (httpResult.getCount() != 20) {
            throw new ResultCodeException(httpResult.getCount());
        }
        return httpResult.getSubjects();
    }
}
