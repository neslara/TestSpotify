package com.example.neslaram.testspotify.beans.responses;

import com.example.neslaram.testspotify.beans.Artist;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by desarrollo on 7/6/16.
 */
public class SearchArtistResponse {
    @SerializedName("artists")
    private SearchArtist artists;

    protected class SearchArtist {
        @SerializedName("items")
        private List<Artist> items;
    }

    public List<Artist> getArtists() {
        return artists != null ? artists.items : null;
    }
}
