package com.kb.myzhihu.storydetail;

import com.kb.myzhihu.data.Story;
import com.kb.myzhihu.util.ApiClient;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hello_kb on 2016/8/6.
 */
public class DetailModel implements DetailContract.DetailModel {

    private DetailPresenter presenter;

    public DetailModel(DetailPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getStory(int storyId) {

        // Retrofit + RxJava
        ApiClient.getService().getStory(storyId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Story>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Story story) {
                        presenter.sendStoryToView(story);
                    }
                });
    }
}
