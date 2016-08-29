package com.example.neslaram.testspotify.album;

import com.example.neslaram.testspotify.main.MusicRepository;
import com.example.neslaram.testspotify.main.MusicRepositoryImpl;

/**
 * Created by desarrollo on 7/6/16.
 */
public class AlbumInteractorImpl implements AlbumInteractor {

    private MusicRepository musicRepository;


    public AlbumInteractorImpl() {
        this.musicRepository = new MusicRepositoryImpl();
    }

    @Override
    public void getAlbums(String artistId) {
        musicRepository.getAlbums(artistId);

    }
}
