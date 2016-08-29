package com.example.neslaram.testspotify.main;

import com.example.neslaram.testspotify.beans.responses.SearchArtistResponse;

import rx.Observable;

/**
 * Created by desarrollo on 7/6/16.
 */
public interface MusicRepository {
    void searchArtist(String artist);
    Observable<SearchArtistResponse> searchArtistRX(String artist);

    void getAlbums(String artist);

}
