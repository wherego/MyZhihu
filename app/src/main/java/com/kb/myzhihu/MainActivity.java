package com.kb.myzhihu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.kb.myzhihu.story.StoryFragment;
import com.kb.myzhihu.storydetail.DetailFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements StoryFragment.OnReplaceFragmentListener{

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        replaceFragment(null, new StoryFragment());
    }

    @Override
    public void onReplaceFragment(StoryFragment oldFragment, int storyId) {
        replaceFragment(oldFragment, DetailFragment.newInstance(storyId));
    }

    public void replaceFragment(Fragment oldFragment, Fragment newFragment) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();

        if (newFragment instanceof DetailFragment) {
            transaction.hide(oldFragment)
                    .addToBackStack(null);
        }
        transaction.add(R.id.content, newFragment).commit();
    }
}