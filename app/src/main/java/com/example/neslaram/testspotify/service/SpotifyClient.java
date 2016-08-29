package com.example.neslaram.testspotify.service;

import com.example.neslaram.testspotify.beans.responses.AlbumResponse;
import com.example.neslaram.testspotify.beans.responses.SearchArtistResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by desarrollo on 4/18/16.
 */
public interface SpotifyClient {
//
//    @GET("users/{username}")
//    Call<Contributor> getUser(@Path("username") String username);
//
//
//    @GET("/users/{owner}")
//    void postIt(@Path("owner") String owner, Callback<Contributor> response);
//
//
//    @GET("/users/{owner}")
//    Contributor contributors(
//            @Path("owner") String owner
////            @Path("repo") String repo
//    );
//
//    @GET("/appiphone/android/cancha/feeds/obtenerAlertasConfiguradas.xml")
//    Call<AlertaConfigurada> getAlertasConfigurdas();

    @GET("search?type=artist")
    Call<SearchArtistResponse> searchArtist(@Query("query") String artist);

    @GET("search?type=artist")
    Observable<SearchArtistResponse> searchArtistRX(@Query("query") String artist);

    @GET("artists/{id}/albums")
    Call<AlbumResponse> getAlbums(@Path("id") String artistId);
}