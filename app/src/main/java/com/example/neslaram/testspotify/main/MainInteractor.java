package com.example.neslaram.testspotify.main;

import com.example.neslaram.testspotify.beans.responses.SearchArtistResponse;

import rx.Observable;

/**
 * Created by desarrollo on 7/6/16.
 */
public interface MainInteractor {
    void doSearchArtist(String artist);
    Observable<SearchArtistResponse> doSearchArtistRX(String artist);
}
