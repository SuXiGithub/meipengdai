package com.jiutong.meipengdai.http.present;

import android.content.Context;

import com.jiutong.meipengdai.entity.MovieBean;
import com.jiutong.meipengdai.http.model.interfaces.IMovieView;
import com.jiutong.meipengdai.http.model.requests.MovieRequest;
import com.jiutong.meipengdai.http.subscribers.ProgressSubscriber;
import com.jiutong.meipengdai.http.subscribers.SubscriberOnNextListener;
import java.util.List;

/**
 * Created by suxi on 2017/5/18.
 */

public class MovieServicePresent {

    private final Context mContext;
    private final IMovieView imv;
    private final MovieRequest movieRequest;

    public MovieServicePresent(Context context, IMovieView imv){
        this.mContext = context;
        this.imv = imv;
        this.movieRequest = new MovieRequest();
    }

    public void getMovies(){
        SubscriberOnNextListener getTopMovieOnNext = new SubscriberOnNextListener<List<MovieBean>>() {
            @Override
            public void onNext(List<MovieBean> subjects) {
                imv.onSuccess(subjects);
            }

            @Override
            public void onError(Throwable e) {
                imv.onError(e);
            }
        };

        movieRequest.getTopMovie(new ProgressSubscriber(getTopMovieOnNext, mContext), 0, 10);
    }

}
