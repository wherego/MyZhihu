package com.kb.myzhihu.story;

import com.kb.myzhihu.data.Story;
import com.kb.myzhihu.data.TopStory;

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
    public void getTopStoriesFromModel() {
        storyModel.getTopStories();
    }

    @Override
    public void sendStoriesToView(List<Story> stories) {
        storyView.showStories(stories);
    }

    @Override
    public void sendTopStoriesToView(List<TopStory> topStories) {
        storyView.showTopStories(topStories);
    }
}
