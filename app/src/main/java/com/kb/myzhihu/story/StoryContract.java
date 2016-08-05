package com.kb.myzhihu.story;

import com.kb.myzhihu.data.Story;
import com.kb.myzhihu.data.TopStory;
import com.kb.myzhihu.data.Zhihu;

import java.util.List;

/**
 * Created by hello_kb on 2016/8/3.
 */
public interface StoryContract {

    interface StoryModel {
        void getZhihu();
    }

    interface StoryView {
        void setPresenter();
        void showStories(List<Story> stories, List<TopStory> topStories);
        void cancelRefresh();
    }

    interface StoryPresenter {
        void getStoriesFromModel();
        void sendStoriesToView(Zhihu zhihu);
    }
}
