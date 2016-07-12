package com.example.neslaram.testspotify.album;

/**
 * Created by desarrollo on 7/6/16.
 */
public class AlbumInteractorImpl implements AlbumInteractor {

    private AlbumRepository albumRepository;


    public AlbumInteractorImpl() {
        this.albumRepository = new AlbumRepositoryImpl();
    }

    @Override
    public void getAlbums(String artistId) {
        albumRepository.getAlbums(artistId);

    }
}
