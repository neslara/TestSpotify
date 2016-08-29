package com.example.neslaram.testspotify.service;

import com.example.neslaram.testspotify.beans.responses.AlbumResponse;
import com.example.neslaram.testspotify.beans.responses.SearchArtistResponse;

/**
 * Created by desarrollo on 7/6/16.
 */
public class SpotifyEvent {
    public final static int ON_SEARCH_SUCCESS = 0;
    public final static int ON_SEARCH_FAILURE = 1;
    public final static int ON_SEARCH_ERROR = 2;
    public static final int ON_ALBUMS_SUCCESS = 3;

    private int evenType;
    private String errorMessage;
    private SearchArtistResponse artistResponse;
    private AlbumResponse albumResponse;

    public SpotifyEvent(int evenType, String errorMessage) {
        this.evenType = evenType;
        this.errorMessage = errorMessage;
    }

    public SpotifyEvent(int evenType, SearchArtistResponse artistResponse) {
        this.evenType = evenType;
        this.artistResponse = artistResponse;
    }

    public SpotifyEvent(int evenType, AlbumResponse albumResponse) {
        this.evenType = evenType;
        this.albumResponse = albumResponse;
    }

    public SearchArtistResponse getArtistResponse() {
        return artistResponse;
    }
    public void setArtistResponse(SearchArtistResponse artistResponse) {
        this.artistResponse = artistResponse;
    }

    public int getEvenType() {
        return evenType;
    }
    public void setEvenType(int evenType) {
        this.evenType = evenType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public AlbumResponse getAlbumResponse() {
        return albumResponse;
    }
    public void setAlbumResponse(AlbumResponse albumResponse) {
        this.albumResponse = albumResponse;
    }
}
