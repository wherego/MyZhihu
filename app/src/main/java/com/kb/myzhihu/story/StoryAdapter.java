package com.kb.myzhihu.story;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kb.myzhihu.R;
import com.kb.myzhihu.data.Story;
import com.kb.myzhihu.util.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hello_kb on 2016/8/3.
 */
public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ViewHolder> {

    private Context context;
    private List<Story> stories = new ArrayList<>();
    private OnClickListener listener;

    public StoryAdapter() {
    }

    public void setData(List<Story> stories) {
        this.stories = stories;
        notifyDataSetChanged();
    }

    @Override
    public StoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.rv_item_story, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StoryAdapter.ViewHolder holder, int position) {
        holder.tvStoryTitle.setText(stories.get(position).getTitle());
        new ImageLoader.Builder()
                .with(context)
                .load(stories.get(position).getImages().get(0))
                .into(holder.ivStoryImage)
                .build()
                .showImage();
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.iv_story_image)
        ImageView ivStoryImage;
        @BindView(R.id.tv_story_title)
        TextView tvStoryTitle;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onClick(getAdapterPosition());
                    }
                }
            });
        }
    }

    public interface OnClickListener {
        void onClick(int position);
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }
}
