package com.example.neslaram.testspotify.spotify;

import com.example.neslaram.testspotify.beans.SearchArtistResponse;
import com.example.neslaram.testspotify.lib.CustomEventBus;
import com.example.neslaram.testspotify.lib.GreenRobotEventBus;
import com.example.neslaram.testspotify.spotify.events.SpotifyEvent;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by desarrollo on 7/6/16.
 */
public class MainRepositoryImpl implements MainRepository {


    @Override
    public void searchArtist(String artist) {
        SpotifyClient taskService = ServiceGenerator.createService(SpotifyClient.class);

        Call<SearchArtistResponse> call = taskService.searchArtist(artist);
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
