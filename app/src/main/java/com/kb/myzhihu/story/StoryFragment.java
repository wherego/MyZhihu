package com.kb.myzhihu.story;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kb.myzhihu.R;
import com.kb.myzhihu.data.Story;
import com.kb.myzhihu.data.TopStory;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoryFragment extends Fragment implements StoryContract.StoryView{

    @BindView(R.id.srl)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.rv_story)
    RecyclerView rvStory;

    private StoryPresenter presenter;
    private StoryAdapter storyAdapter;

    public StoryFragment() {
        // Required empty public constructor
        setPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_story, container, false);

        ButterKnife.bind(this, view);

        setupRecyclerView();
        setupRefreshLayout();

        return view;
    }

    private void setupRecyclerView() {
        // Story recyclerView
        storyAdapter = new StoryAdapter();
        rvStory.setLayoutManager(new LinearLayoutManager(getContext()));
        rvStory.setAdapter(storyAdapter);
        rvStory.setHasFixedSize(true);

    }

    private void setupRefreshLayout() {
        refreshLayout.setColorSchemeResources(
                android.R.color.holo_red_light,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light
        );

        // auto refresh when app opened
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                presenter.getStoriesFromModel();
            }
        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getStoriesFromModel();
            }
        });
    }

    @Override
    public void setPresenter() {
        presenter = new StoryPresenter(this);
    }

    @Override
    public void showTopStories(List<TopStory> topStories) {
//        topStoryAdapter.setData(topStories);
    }

    @Override
    public void showStories(List<Story> stories) {
        storyAdapter.setData(stories);
        cancelRefresh();
    }

    @Override
    public void refresh() {

    }

    @Override
    public void cancelRefresh() {
        refreshLayout.setRefreshing(false);
    }
}
