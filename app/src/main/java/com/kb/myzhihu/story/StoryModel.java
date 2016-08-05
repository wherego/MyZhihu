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

    private ApiClient.ApiService zhihuStoryService;

    public StoryModel(StoryPresenter presenter) {
        this.presenter = presenter;
        zhihuStoryService = ApiClient.getService();
    }

    @Override
    public void getZhihu() {

        // RxJava
        zhihuStoryService
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
}
