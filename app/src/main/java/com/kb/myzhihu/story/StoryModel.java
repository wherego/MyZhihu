package com.kb.myzhihu.story;

import com.kb.myzhihu.data.Zhihu;
import com.kb.myzhihu.util.ApiClient;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hello_kb on 2016/8/3.
 */
public class StoryModel implements StoryContract.StoryModel {

    private StoryPresenter presenter;

    public StoryModel(StoryPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getZhihu() {

        // Retrofit + RxJava
        ApiClient.getService()
                .getZhihuResponse()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Zhihu>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Zhihu zhihu) {
                        presenter.sendStoriesToView(zhihu);
                    }
                });
    }

    @Override
    public void getPreviousZhihu(String date) {

        ApiClient.getService()
                .getPreviousZhihu(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Zhihu>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Zhihu zhihu) {
                        presenter.sendPreviousStoriesToView(zhihu.getStories());
                    }
                });

    }
}
