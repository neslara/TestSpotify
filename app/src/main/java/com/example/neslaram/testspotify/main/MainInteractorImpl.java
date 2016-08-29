package com.example.neslaram.testspotify.main;

import com.example.neslaram.testspotify.beans.responses.SearchArtistResponse;

import rx.Observable;

/**
 * Created by desarrollo on 7/6/16.
 */
public class MainInteractorImpl implements MainInteractor {

    private MusicRepository musicRepository;


    public MainInteractorImpl() {
        this.musicRepository = new MusicRepositoryImpl();
    }

    @Override
    public void doSearchArtist(String artist) {
        musicRepository.searchArtist(artist);

    }

    @Override
    public Observable<SearchArtistResponse> doSearchArtistRX(String artist) {
       return musicRepository.searchArtistRX(artist);

    }
}
