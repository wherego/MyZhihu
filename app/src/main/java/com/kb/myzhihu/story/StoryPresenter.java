package com.kb.myzhihu.story;

import com.kb.myzhihu.data.Zhihu;

/**
 * Created by hello_kb on 2016/8/3.
 */
public class StoryPresenter implements StoryContract.StoryPresenter {

    private final StoryContract.StoryView storyView;
    private final StoryContract.StoryModel storyModel;

    public StoryPresenter(StoryContract.StoryView storyView) {
        this.storyView = storyView;
        this.storyModel = new StoryModel(this);
    }

    @Override
    public void getStoriesFromModel() {
        storyModel.getZhihu();
    }

    @Override
    public void sendStoriesToView(Zhihu zhihu) {
        storyView.showStories(zhihu.getStories(), zhihu.getTopStories());
    }

}
