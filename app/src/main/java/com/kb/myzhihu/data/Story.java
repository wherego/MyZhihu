package com.kb.myzhihu.data;

import java.util.List;

public class Story {

    private List<String> images;
    private String id;
    private String title;
    private boolean multipic;

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int isMultipic() {
        return multipic ? 1 : 0;
    }

    public void setMultipic(boolean multipic) {
        this.multipic = multipic;
    }
}
