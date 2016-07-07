package com.example.neslaram.testspotify.spotify.events;

import com.example.neslaram.testspotify.beans.SearchArtistResponse;

/**
 * Created by desarrollo on 7/6/16.
 */
public class SpotifyEvent {
    public final static int ON_SEARCH_SUCCESS = 0;
    public final static int ON_SEARCH_FAILURE = 1;
    public final static int ON_SEARCH_ERROR = 2;

    private int evenType;
    private String errorMessage;
    private SearchArtistResponse response;

    public SpotifyEvent(int evenType, String errorMessage) {
        this.evenType = evenType;
        this.errorMessage = errorMessage;
    }

    public SpotifyEvent(int evenType, SearchArtistResponse response) {
        this.evenType = evenType;
        this.response = response;
    }

    public SearchArtistResponse getResponse() {
        return response;
    }

    public void setResponse(SearchArtistResponse response) {
        this.response = response;
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
}
