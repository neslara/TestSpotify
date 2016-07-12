package com.example.neslaram.testspotify.beans;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by desarrollo on 7/6/16.
 */
public class AlbumResponse {
    @SerializedName("total")
    private int total;
    @SerializedName("items")
    private List<Album> albums;

    public List<Album> getAlbums() {
        return albums;
    }
}
