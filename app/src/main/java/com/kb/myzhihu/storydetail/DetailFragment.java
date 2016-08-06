package com.kb.myzhihu.storydetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.kb.myzhihu.R;
import com.kb.myzhihu.data.Story;
import com.kb.myzhihu.util.HtmlUtil;
import com.kb.myzhihu.util.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment implements DetailContract.DetailView{

    @BindView(R.id.iv_story_header)
    ImageView ivStoryHeader;
    @BindView(R.id.tv_story_title)
    TextView tvStoryTitle;
    @BindView(R.id.tv_copyright)
    TextView tvCopyright;
    @BindView(R.id.wb_story)
    WebView wbStory;

    private int storyId;

    private DetailPresenter presenter;

    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment newInstance(int storyId) {
        DetailFragment detailFragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("storyId", storyId);
        detailFragment.setArguments(bundle);

        return detailFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new DetailPresenter(this);
        if (getArguments() != null) {
            storyId = getArguments().getInt("storyId");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_story_detail, container, false);

        ButterKnife.bind(this, view);

        presenter.getStoryFromModel(storyId);

        return view;
    }

    @Override
    public void showStoryDetail(Story story) {
        String body = HtmlUtil.formatHtml(story.getBody());

        wbStory.loadDataWithBaseURL("file:///android_asset/", body, "text/html", "UTF-8", "");
        tvStoryTitle.setText(story.getTitle());
        tvCopyright.setText(story.getImageSource());
        new ImageLoader.Builder()
                .with(getContext())
                .load(story.getImage())
                .fit().centerCrop()
                .into(ivStoryHeader)
                .build().showImage();
    }
}
