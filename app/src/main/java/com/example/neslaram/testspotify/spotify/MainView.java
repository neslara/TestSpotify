package com.example.neslaram.testspotify.spotify;

import com.example.neslaram.testspotify.beans.Artist;

import java.util.List;

/**
 * Created by desarrollo on 7/6/16.
 */
public interface MainView {
    void setItems(List<Artist> items);
    void showErrorMessage(String error);

}
