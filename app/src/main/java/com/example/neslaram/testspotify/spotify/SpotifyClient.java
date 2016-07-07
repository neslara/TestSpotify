package com.example.neslaram.testspotify.spotify;

import com.example.neslaram.testspotify.beans.SearchArtistResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

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

    @GET("search?offset=0&limit=20&type=artist&market=US")
    Call<SearchArtistResponse> searchArtist(@Query("query") String artist);
}