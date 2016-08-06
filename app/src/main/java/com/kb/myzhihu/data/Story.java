package com.kb.myzhihu.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Story {

    private List<String> images;
    private String image;
    private int id;
    private String title;
    private String body;
    @SerializedName("image_source") private String imageSource;
    private boolean multipic;

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public int isMultipic() {
        return multipic ? 1 : 0;
    }

    public void setMultipic(boolean multipic) {
        this.multipic = multipic;
    }
}
