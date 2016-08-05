package com.kb.myzhihu.util;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by hello_kb on 2016/8/3.
 */
public class ImageLoader {

    private Context context;
    private String imageUrl;
    private ImageView imageView;

    public ImageLoader(Builder builder) {
        this.context = builder.context;
        this.imageUrl = builder.imageUrl;
        this.imageView = builder.imageView;
    }
    public void showImage() {
        Picasso.with(context)
                .load(imageUrl)
                .into(imageView);

    }

    public static class Builder {

        private Context context;
        private String imageUrl;
        private ImageView imageView;

        public Builder with(Context context) {
            this.context = context;

            return this;
        }

        public Builder load(String imageUrl) {
            this.imageUrl = imageUrl;

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
