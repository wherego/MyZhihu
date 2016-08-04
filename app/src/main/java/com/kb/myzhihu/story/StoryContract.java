package com.kb.myzhihu.story;

import com.kb.myzhihu.data.Story;
import com.kb.myzhihu.data.TopStory;

import java.util.List;

/**
 * Created by hello_kb on 2016/8/3.
 */
public interface StoryContract {

    interface StoryModel {
        void getTopStories();
        void getZhihu();
    }

    interface StoryView {
        void setPresenter();
        void showTopStories(List<TopStory> topStories);
        void showStories(List<Story> stories);
        void refresh();
        void cancelRefresh();
    }

    interface StoryPresenter {
        void getStoriesFromModel();
        void getTopStoriesFromModel();
        void sendStoriesToView(List<Story> stories);
        void sendTopStoriesToView(List<TopStory> topStories);
    }
}
