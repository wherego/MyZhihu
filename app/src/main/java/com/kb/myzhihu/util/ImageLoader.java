package com.kb.myzhihu.util;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

/**
 * Created by hello_kb on 2016/8/3.
 */
public class ImageLoader {

    private Context context;
    private String imageUrl;
    private ImageView imageView;
    private boolean fit;
    private boolean centerCrop;

    public ImageLoader(Builder builder) {
        this.context = builder.context;
        this.imageUrl = builder.imageUrl;
        this.imageView = builder.imageView;
        this.fit = builder.fit;
        this.centerCrop = builder.centerCrop;
    }
    public void showImage() {
        RequestCreator creator = Picasso.with(context).load(imageUrl);

        if (fit) {
            creator.fit();
        }
        if (centerCrop) {
            creator.centerCrop();
        }

        creator.into(imageView);
    }

    public static class Builder {

        private Context context;
        private String imageUrl;
        private ImageView imageView;
        private boolean fit = false;
        private boolean centerCrop = false;

        public Builder with(Context context) {
            this.context = context;

            return this;
        }

        public Builder load(String imageUrl) {
            this.imageUrl = imageUrl;

            return this;
        }

        public Builder fit() {
            this.fit = true;

            return this;
        }

        public Builder centerCrop() {
            this.centerCrop = true;

            return this;
        }

        public Builder into(ImageView imageView) {
            this.imageView = imageView;

            return this;
        }

        public ImageLoader build() {
            return new ImageLoader(this);
        }
    }

}
