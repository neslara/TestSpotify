package com.example.neslaram.testspotify.beans;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by desarrollo on 7/6/16.
 */

public class Artist {
    private static final String TAG = Artist.class.getSimpleName();
    @SerializedName("followers")
    public Follower followers;
    @SerializedName("href")
    public String href;
    @SerializedName("id")
    public String id;
    @SerializedName("images")
    public List<Image> images = new ArrayList<>();
    @SerializedName("name")
    public String name;
    @SerializedName("popularity")
    public int popularity;
    @SerializedName("type")
    public String type;
    @SerializedName("uri")
    public String uri;

    public Follower getFollowers() {
        return followers;
    }

    public void setFollowers(Follower followers) {
        this.followers = followers;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
