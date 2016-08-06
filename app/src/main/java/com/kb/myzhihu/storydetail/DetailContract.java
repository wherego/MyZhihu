package com.kb.myzhihu.storydetail;

import com.kb.myzhihu.data.Story;

/**
 * Created by hello_kb on 2016/8/6.
 */
public interface DetailContract {

    interface DetailModel {
        void getStory(int storyId);
    }

    interface DetailView {
        void showStoryDetail(Story story);
    }

    interface DetailPresenter {
        void getStoryFromModel(int storyId);
        void sendStoryToView(Story story);
    }
}
