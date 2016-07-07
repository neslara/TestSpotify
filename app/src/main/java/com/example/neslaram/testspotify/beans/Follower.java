package com.example.neslaram.testspotify.beans;

import com.google.gson.annotations.SerializedName;

/**
 * Created by desarrollo on 7/6/16.
 */
public class Follower {
    @SerializedName("href")
    public String href;
    @SerializedName("total")
    public int total;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
