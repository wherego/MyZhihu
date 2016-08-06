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

        replaceFragment(new StoryFragment());
    }

    @Override
    public void onReplaceFragment(int storyId) {
        replaceFragment(DetailFragment.newInstance(storyId));
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();

        if (fragment instanceof DetailFragment) {
            transaction.addToBackStack(null);
        }
        transaction.replace(R.id.content, fragment).commit();
    }
}