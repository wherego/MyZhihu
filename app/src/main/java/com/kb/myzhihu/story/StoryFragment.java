package com.kb.myzhihu.story;

import android.content.Context;
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

    private OnReplaceFragmentListener listener;

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
        storyAdapter = new StoryAdapter();
        rvStory.setLayoutManager(new LinearLayoutManager(getContext()));
        rvStory.setAdapter(storyAdapter);
        rvStory.setHasFixedSize(true);

        storyAdapter.setOnClickListener(new StoryAdapter.OnClickListener() {
            @Override
            public void onClick(int storyId) {
                listener.onReplaceFragment(storyId);
            }
        });
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
    public void showStories(List<Story> stories, List<TopStory> topStories) {
        storyAdapter.setData(stories, topStories);
        cancelRefresh();
    }

    @Override
    public void cancelRefresh() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnReplaceFragmentListener) {
            listener = (OnReplaceFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
            + "must implement OnReplaceFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface OnReplaceFragmentListener {
        void onReplaceFragment(int storyId);
    }
}
