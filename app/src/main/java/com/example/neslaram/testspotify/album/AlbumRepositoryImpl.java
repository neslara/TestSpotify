package com.example.neslaram.testspotify.album;

import com.example.neslaram.testspotify.beans.AlbumResponse;
import com.example.neslaram.testspotify.beans.SearchArtistResponse;
import com.example.neslaram.testspotify.lib.CustomEventBus;
import com.example.neslaram.testspotify.lib.GreenRobotEventBus;
import com.example.neslaram.testspotify.service.ServiceGenerator;
import com.example.neslaram.testspotify.service.SpotifyClient;
import com.example.neslaram.testspotify.service.SpotifyEvent;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumRepositoryImpl implements AlbumRepository {


    @Override
    public void getAlbums(String artistId) {
        SpotifyClient taskService = ServiceGenerator.getApiService();
        Call<AlbumResponse> call= taskService.getAlbums(artistId);
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

    private void postEvent(int type, String errorMessage) {
        SpotifyEvent loginEvent = new SpotifyEvent(type, errorMessage);
        CustomEventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(loginEvent);
    }
}
