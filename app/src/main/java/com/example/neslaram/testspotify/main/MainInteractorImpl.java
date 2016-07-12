package com.example.neslaram.testspotify.main;

/**
 * Created by desarrollo on 7/6/16.
 */
public class MainInteractorImpl implements MainInteractor {

    private MainRepository mainRepository;


    public MainInteractorImpl() {
        this.mainRepository = new MainRepositoryImpl();
    }

    @Override
    public void doSearchArtist(String artist) {
        mainRepository.searchArtist(artist);

    }
}
