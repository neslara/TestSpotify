package com.example.neslaram.testspotify.main;

import com.example.neslaram.testspotify.beans.responses.AlbumResponse;
import com.example.neslaram.testspotify.beans.responses.SearchArtistResponse;
import com.example.neslaram.testspotify.lib.CustomEventBus;
import com.example.neslaram.testspotify.lib.GreenRobotEventBus;
import com.example.neslaram.testspotify.service.ServiceGenerator;
import com.example.neslaram.testspotify.service.SpotifyClient;
import com.example.neslaram.testspotify.service.SpotifyEvent;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by desarrollo on 7/6/16.
 */
public class MusicRepositoryImpl implements MusicRepository {
    private SpotifyClient service;
    public MusicRepositoryImpl() {
        service = ServiceGenerator.getApiService();

    }

    @Override
    public void searchArtist(String artist) {

        Call<SearchArtistResponse> call = service.searchArtist(artist);
        call.enqueue(new Callback<SearchArtistResponse>() {
            @Override
            public void onResponse(Call<SearchArtistResponse> call, Response<SearchArtistResponse> response) {
                if (response.isSuccessful()) {

                    SearchArtistResponse body = response.body();
                    postEvent(SpotifyEvent.ON_SEARCH_SUCCESS, body);
                } else {
                    postEvent(SpotifyEvent.ON_SEARCH_FAILURE, response.toString());
                    // error response, no access to resource?
                }
            }

            @Override
            public void onFailure(Call<SearchArtistResponse> call, Throwable t) {
                postEvent(SpotifyEvent.ON_SEARCH_ERROR, t.getMessage());
            }
        });
    }

    @Override
    public Observable<SearchArtistResponse> searchArtistRX(String artist) {
        return service.searchArtistRX(artist)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io());
    }

    @Override
    public void getAlbums(String artistId) {

        Call<AlbumResponse> call= service.getAlbums(artistId);
        call.enqueue(new Callback<AlbumResponse>() {
            @Override
            public void onResponse(Call<AlbumResponse> call, Response<AlbumResponse> response) {
                if (response.isSuccessful()) {

                    AlbumResponse body = response.body();
                    postEvent(SpotifyEvent.ON_ALBUMS_SUCCESS, body);
                } else {
                    postEvent(SpotifyEvent.ON_SEARCH_FAILURE, response.toString());
                    // error response, no access to resource?
                }
            }

            @Override
            public void onFailure(Call<AlbumResponse> call, Throwable t) {
                postEvent(SpotifyEvent.ON_SEARCH_ERROR, t.getMessage());

            }
        });
    }

    private void postEvent(int type, AlbumResponse response) {
        SpotifyEvent loginEvent = new SpotifyEvent(type, response);
        CustomEventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(loginEvent);
    }

    private void postEvent(int type, SearchArtistResponse response) {
        SpotifyEvent loginEvent = new SpotifyEvent(type, response);
        CustomEventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(loginEvent);
    }

    private void postEvent(int type, String errorMessage) {
        SpotifyEvent loginEvent = new SpotifyEvent(type, errorMessage);
        CustomEventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(loginEvent);
    }
}
