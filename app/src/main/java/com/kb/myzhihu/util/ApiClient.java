package com.kb.myzhihu.util;

import com.kb.myzhihu.data.Story;
import com.kb.myzhihu.data.Zhihu;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by hello_kb on 2016/8/3.
 */
public class ApiClient {

    private static final String BASE_URL = "http://news-at.zhihu.com/api/4/news/";

    private static Retrofit retrofit = null;

    public interface ApiService {

        @GET("latest")
        Observable<Zhihu> getZhihuResponse();

        @GET("{id}")
        Observable<Story> getStory(@Path("id") int id);
    }

    private static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }

        return retrofit;
    }

    public static ApiService getService() {
        return getClient().create(ApiService.class);
    }
}
