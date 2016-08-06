package com.kb.myzhihu.storydetail;

import com.kb.myzhihu.data.Story;

import com.kb.myzhihu.storydetail.DetailContract.DetailView;

/**
 * Created by hello_kb on 2016/8/6.
 */
public class DetailPresenter implements DetailContract.DetailPresenter {

    private DetailView detailView;
    private DetailModel detailModel;

    public DetailPresenter(DetailView detailView) {
        this.detailView = detailView;
        this.detailModel = new DetailModel(this);
    }

    @Override
    public void getStoryFromModel(int storyId) {
            detailModel.getStory(storyId);
    }

    @Override
    public void sendStoryToView(Story story) {
        detailView.showStoryDetail(story);
    }
}
