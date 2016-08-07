package com.kb.myzhihu.story;

import com.kb.myzhihu.data.Story;
import com.kb.myzhihu.data.Zhihu;

import java.util.List;

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

    @Override
    public void getPreviousStories(String date) {
        storyModel.getPreviousZhihu(date);
    }

    @Override
    public void sendPreviousStoriesToView(List<Story> stories) {
        storyView.showPreviousStories(stories);
    }
}
