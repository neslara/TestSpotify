package com.example.neslaram.testspotify.album;

import com.example.neslaram.testspotify.service.SpotifyEvent;

/**
 * Created by desarrollo on 7/6/16.
 */
public interface AlbumPresenter {
    void onCreate();
    void onDestroy();
    void getAlbums(String artistId);
    void onEventMainThread(SpotifyEvent event);

}
