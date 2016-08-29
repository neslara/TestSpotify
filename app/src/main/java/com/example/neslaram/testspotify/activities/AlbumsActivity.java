package com.example.neslaram.testspotify.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.neslaram.testspotify.R;
import com.example.neslaram.testspotify.adapters.AlbumAdapter;
import com.example.neslaram.testspotify.album.AlbumPresenter;
import com.example.neslaram.testspotify.album.AlbumPresenterImpl;
import com.example.neslaram.testspotify.album.AlbumView;
import com.example.neslaram.testspotify.beans.Album;
import com.example.neslaram.testspotify.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AlbumsActivity extends AppCompatActivity implements AlbumView {

    private static final String TAG = AlbumsActivity.class.getSimpleName();
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recycler_albums)
    RecyclerView recyclerAlbums;

    private String artistId;
    private String artistName;
    private AlbumAdapter adapter;
    private AlbumPresenter albumPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);
        ButterKnife.bind(this);
        artistId = getIntent().getStringExtra(Constants.KEY_ARTIST_ID);
        artistName = getIntent().getStringExtra(Constants.KEY_ARTIST_NAME);

        toolbar.setTitle(artistName);
        setSupportActionBar(toolbar);



        albumPresenter = new AlbumPresenterImpl(this);
        albumPresenter.onCreate();
        albumPresenter.getAlbums(artistId);

        adapter= new AlbumAdapter(new ArrayList<Album>());
        recyclerAlbums.setAdapter(adapter);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        artistId = savedInstanceState.getString(Constants.KEY_ARTIST_ID);
        artistName = savedInstanceState.getString(Constants.KEY_ARTIST_NAME);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(Constants.KEY_ARTIST_ID, artistId);
        outState.putString(Constants.KEY_ARTIST_NAME, artistName);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        albumPresenter.onDestroy();
        super.onDestroy();

    }

    @Override
    public void setItems(List<Album> items) {
        adapter.addItems(items);
    }

    @Override
    public void setNotFound() {

    }

    @Override
    public void showErrorMessage(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}
