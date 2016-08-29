package com.example.neslaram.testspotify.album;

import com.example.neslaram.testspotify.beans.responses.AlbumResponse;
import com.example.neslaram.testspotify.lib.CustomEventBus;
import com.example.neslaram.testspotify.lib.GreenRobotEventBus;
import com.example.neslaram.testspotify.service.SpotifyEvent;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by desarrollo on 7/6/16.
 */
public class AlbumPresenterImpl implements AlbumPresenter {

    private static final String TAG = AlbumPresenterImpl.class.getSimpleName();
    private CustomEventBus eventBus;
    private AlbumView albumView;
    private AlbumInteractor albumInteractor;

    public AlbumPresenterImpl(AlbumView albumView) {
        this.albumView = albumView;
        this.albumInteractor = new AlbumInteractorImpl();
        this.eventBus = GreenRobotEventBus.getInstance();

    }

    @Override
    public void onCreate() {
        eventBus.register(this);

    }

    @Override
    public void onDestroy() {
        this.albumView = null;
        eventBus.unregister(this);
    }

    @Override
    public void getAlbums(String artistId) {
        albumInteractor.getAlbums(artistId);

    }

    @Subscribe
    @Override
    public void onEventMainThread(SpotifyEvent event) {
        switch (event.getEvenType()) {
            case SpotifyEvent.ON_ALBUMS_SUCCESS:
                onSearchSuccess(event.getAlbumResponse());
                break;
            case SpotifyEvent.ON_SEARCH_ERROR:
                onSearchError(event.getErrorMessage());
                break;
            case SpotifyEvent.ON_SEARCH_FAILURE:
                onSearchError(event.getErrorMessage());
                break;
        }
    }

    private void onSearchSuccess(AlbumResponse response) {
        if (albumView != null) {
            albumView.setItems(response.getAlbums());
        }
    }

    private void onSearchError(String error) {
        if (albumView != null) {
            albumView.showErrorMessage(error);
        }
    }

}
