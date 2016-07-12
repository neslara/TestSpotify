package com.example.neslaram.testspotify.album;

import com.example.neslaram.testspotify.beans.Album;
import com.example.neslaram.testspotify.beans.Artist;

import java.util.List;

/**
 * Created by desarrollo on 7/6/16.
 */
public interface AlbumView {
    void setItems(List<Album> items);
    void setNotFound();
    void showErrorMessage(String error);

}
