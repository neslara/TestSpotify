package com.example.neslaram.testspotify.main;

import com.example.neslaram.testspotify.beans.Artist;
import com.example.neslaram.testspotify.beans.comparator.ArtistPopularityComparator;
import com.example.neslaram.testspotify.beans.responses.SearchArtistResponse;
import com.example.neslaram.testspotify.lib.CustomEventBus;
import com.example.neslaram.testspotify.lib.GreenRobotEventBus;
import com.example.neslaram.testspotify.service.SpotifyEvent;

import org.greenrobot.eventbus.Subscribe;

import java.util.Collections;
import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by desarrollo on 7/6/16.
 */
public class MainPresenterImpl implements MainPresenter {

    private static final String TAG = MainPresenterImpl.class.getSimpleName();
    private CustomEventBus eventBus;
    private MainView mainView;
    private MainInteractor mainInteractor;
    private CompositeSubscription compositeSubscription;


    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
        this.mainInteractor = new MainInteractorImpl();
        this.eventBus = GreenRobotEventBus.getInstance();
        this.compositeSubscription = new CompositeSubscription();

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
    public void onDestroyView() {
        compositeSubscription.unsubscribe();

    }

    @Override
    public void searchArtist(String artist) {
        mainInteractor.doSearchArtist(artist);

    }

    @Override
    public void searchArtistRX(String artist) {
        mainInteractor.doSearchArtistRX(artist);
        Subscription subscription = mainInteractor.doSearchArtistRX(artist)
                .subscribe(new Observer<SearchArtistResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mainView.showErrorMessage(e.getMessage());
                    }

                    @Override
                    public void onNext(SearchArtistResponse searchResponse) {
                        onSearchSuccess(searchResponse);
                    }
                });
        addSubscription(subscription);
    }

    @Subscribe
    @Override
    public void onEventMainThread(SpotifyEvent event) {
        switch (event.getEvenType()) {
            case SpotifyEvent.ON_SEARCH_SUCCESS:
                onSearchSuccess(event.getArtistResponse());
                break;
            case SpotifyEvent.ON_SEARCH_ERROR:
                onSearchError(event.getErrorMessage());
                break;
            case SpotifyEvent.ON_SEARCH_FAILURE:
                onSearchError(event.getErrorMessage());
                break;
        }
    }

    private void addSubscription(Subscription subscription) {
        compositeSubscription.add(subscription);
    }

    private void onSearchSuccess(SearchArtistResponse response) {
        if (mainView != null) {
            List<Artist> artistList = response.getArtists();
            Collections.sort(artistList, new ArtistPopularityComparator());
//            Collections.reverse(artistList);
            int size = artistList.size();
            if (size > 0) {
                mainView.setItems(artistList);
            } else {
                mainView.setNotFound();
            }
        }
    }

    private void onSearchError(String error) {
        if (mainView != null) {
            mainView.showErrorMessage(error);
        }
    }

}
