package com.jiutong.meipengdai.http.model.services;

import com.jiutong.meipengdai.entity.BufferRespond;
import com.jiutong.meipengdai.entity.MovieBean;

import java.util.List;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by suxi on 16/3/9.
 */
public interface MovieService {

    @POST("top250")
    Observable<BufferRespond<List<MovieBean>>> getTopMovie(@Query("start") int start, @Query("count") int count);
}
