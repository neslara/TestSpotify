package com.example.neslaram.testspotify.beans.comparator;

import com.example.neslaram.testspotify.beans.Artist;

import java.util.Comparator;

/**
 * Created by desarrollo on 7/27/16.
 */
public class ArtistNameComparator implements Comparator<Artist> {

    @Override
    public int compare(Artist lhs, Artist rhs) {
        return lhs.getName().compareTo(rhs.getName());

    }
}
