package com.example.neslaram.testspotify.spotify;

import com.example.neslaram.testspotify.beans.SearchArtistResponse;
import com.example.neslaram.testspotify.lib.CustomEventBus;
import com.example.neslaram.testspotify.lib.GreenRobotEventBus;
import com.example.neslaram.testspotify.spotify.events.SpotifyEvent;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by desarrollo on 7/6/16.
 */
public class MainPresenterImpl implements MainPresenter {

    private static final String TAG = MainPresenterImpl.class.getSimpleName();
    private CustomEventBus eventBus;
    private MainView mainView;
    private MainInteractor mainInteractor;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
        this.mainInteractor = new MainInteractorImpl();
        this.eventBus = GreenRobotEventBus.getInstance();

    }

    @Override
    public void onCreate() {
        eventBus.register(this);

    }

    @Override
    public void onDestroy() {
        this.mainView = null;
        eventBus.unregister(this);
    }

    @Override
    public void searchArtist(String artist) {
        mainInteractor.doSearchArtist(artist);

    }

    @Subscribe
    @Override
    public void onEventMainThread(SpotifyEvent event) {
        switch (event.getEvenType()) {
            case SpotifyEvent.ON_SEARCH_SUCCESS:
                onSearchSuccess(event.getResponse());
                break;
            case SpotifyEvent.ON_SEARCH_ERROR:
                onSearchError(event.getErrorMessage());
                break;
            case SpotifyEvent.ON_SEARCH_FAILURE:
                onSearchFailure(event.getErrorMessage());
                break;
        }
    }


    private void onSearchSuccess(SearchArtistResponse response) {
        if (mainView != null) {
            mainView.setItems(response.getArtists());
        }
    }


    private void onSearchError(String error) {
        if (mainView != null) {
            mainView.showErrorMessage(error);
        }
    }

    private void onSearchFailure(String error) {
        if (mainView != null) {
            mainView.showErrorMessage(error);
        }
    }


}
