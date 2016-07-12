package com.example.neslaram.testspotify.main;

import com.example.neslaram.testspotify.service.SpotifyEvent;

/**
 * Created by desarrollo on 7/6/16.
 */
public interface MainPresenter {
    void onCreate();
    void onDestroy();
    void searchArtist(String artist);
    void onEventMainThread(SpotifyEvent event);

}
