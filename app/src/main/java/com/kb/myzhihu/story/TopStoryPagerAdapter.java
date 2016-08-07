package com.kb.myzhihu.story;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kb.myzhihu.R;
import com.kb.myzhihu.data.TopStory;
import com.kb.myzhihu.util.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hello_kb on 2016/8/5.
 */
public class TopStoryPagerAdapter extends PagerAdapter {

    @BindView(R.id.iv_top_story)
    ImageView ivTopStory;
    @BindView(R.id.tv_top_story)
    TextView tvTopStory;

    private Context context;
    private List<TopStory> topStories = new ArrayList<>();

    private OnClickListener listener;

    public TopStoryPagerAdapter(Context context, List<TopStory> topStories) {
        this.context = context;
        this.topStories = topStories;
    }

    public void setData(List<TopStory> topStories) {
        this.topStories = topStories;
    }

    @Override
    public int getCount() {
        return topStories.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = LayoutInflater.from(container.getContext())
                .inflate(R.layout.vp_item_top_story, container, false);

        ButterKnife.bind(this, itemView);

        new ImageLoader.Builder()
                .with(context)
                .load(topStories.get(position).getImage())
                .fit().centerCrop()
                .into(ivTopStory)
                .build().showImage();
        tvTopStory.setText(topStories.get(position).getTitle());

        container.addView(itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClick(position);
                }
            }
        });

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public interface OnClickListener {
        void onClick(int position);
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }
}
