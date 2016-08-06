package com.kb.myzhihu.story;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kb.myzhihu.R;
import com.kb.myzhihu.data.Story;
import com.kb.myzhihu.data.TopStory;
import com.kb.myzhihu.util.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hello_kb on 2016/8/3.
 */
public class StoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_TOP_STORY = 0;
    private static final int TYPE_STORY = 1;

    private Context context;
    private List<Story> stories = new ArrayList<>();
    private OnClickListener listener;

    private TopStoryPagerAdapter topStoryPagerAdapter = null;

    public StoryAdapter() {

    }

    public void setData(List<Story> stories, List<TopStory> topStories) {
        this.stories = stories;
        notifyDataSetChanged();

        topStoryPagerAdapter = new TopStoryPagerAdapter(context, topStories);
        topStoryPagerAdapter.notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();

        if (viewType == TYPE_TOP_STORY) {
            View view = LayoutInflater.from(context)
                    .inflate(R.layout.vp_top_story, parent, false);

            return new ViewHolderTopStory(view);
        }

        View view = LayoutInflater.from(context)
                .inflate(R.layout.rv_item_story, parent, false);

        return new ViewHolderStory(view);

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_TOP_STORY;
        }

        return TYPE_STORY;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ViewHolderStory) {
            ViewHolderStory holderStory = (ViewHolderStory) holder;

            // Story starts at position 1
            position--;
            holderStory.tvStoryTitle.setText(stories.get(position).getTitle());
            new ImageLoader.Builder()
                    .with(context)
                    .load(stories.get(position).getImages().get(0))
                    .into(holderStory.ivStoryImage)
                    .build()
                    .showImage();
        } else if (holder instanceof ViewHolderTopStory){
            ViewHolderTopStory holderTopStory = (ViewHolderTopStory) holder;
            holderTopStory.viewPager.setAdapter(topStoryPagerAdapter);
        } else {
            throw new RuntimeException("View type not available");
        }

    }

    @Override
    public int getItemCount() {
        return stories.size() + 1;
    }

    public class ViewHolderStory extends RecyclerView.ViewHolder{

        @BindView(R.id.iv_story_image)
        ImageView ivStoryImage;
        @BindView(R.id.tv_story_title)
        TextView tvStoryTitle;

        public ViewHolderStory(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onClick(stories.get(getAdapterPosition() - 1).getId());
                    }
                }
            });
        }
    }

    public class ViewHolderTopStory extends RecyclerView.ViewHolder {

        @BindView(R.id.vp_top_story)
        ViewPager viewPager;

        public ViewHolderTopStory(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnClickListener {
        void onClick(int storyId);
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }
}
