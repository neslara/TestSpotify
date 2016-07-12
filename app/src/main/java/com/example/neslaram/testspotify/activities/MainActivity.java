package com.example.neslaram.testspotify.activities;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.neslaram.testspotify.R;
import com.example.neslaram.testspotify.adapters.ArtistAdapter;
import com.example.neslaram.testspotify.adapters.OnItemClickListener;
import com.example.neslaram.testspotify.beans.Artist;
import com.example.neslaram.testspotify.main.MainPresenter;
import com.example.neslaram.testspotify.main.MainPresenterImpl;
import com.example.neslaram.testspotify.main.MainView;
import com.example.neslaram.testspotify.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView, SearchView.OnQueryTextListener, OnItemClickListener<Artist> {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String KEY_ARTIST = "key_artist";

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recycler_artists)
    RecyclerView recyclerView;
    @Bind(R.id.txtVw_main)
    TextView txtVwMain;

    private SearchView searchView;

    private ArtistAdapter adapter;
    private MainPresenter mainPresenter;
    private String searchQuery;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        adapter = new ArtistAdapter(new ArrayList<Artist>(), this);
        recyclerView.setAdapter(adapter);
        txtVwMain.setText(R.string.search_title);

        mainPresenter = new MainPresenterImpl(this);
        mainPresenter.onCreate();
    }

    @Override
    protected void onDestroy() {
        mainPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_search, menu);

        searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint(getText(R.string.search_title));
        searchView.setOnQueryTextListener(this);
        if (searchQuery != null && !searchQuery.isEmpty()) {
            searchView.setQuery(searchQuery, true);
        }
        return true;
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        searchQuery = savedInstanceState.getString(KEY_ARTIST);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(KEY_ARTIST, searchView.getQuery().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void setItems(List<Artist> items) {
        showRecycler();
        hideText();
        adapter.addItems(items);
        recyclerView.scrollToPosition(0);
    }

    @Override
    public void setNotFound() {
        showText();
        hideRecycler();
        txtVwMain.setText(R.string.not_found);
        txtVwMain.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_library_music, 0, 0);

    }

    @Override
    public void showErrorMessage(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClicked(int position, Artist artist) {
        Intent intent= new Intent(this, AlbumsActivity.class);
        intent.putExtra(Constants.KEY_ARTIST_ID, artist.getId());
        intent.putExtra(Constants.KEY_ARTIST_NAME, artist.getName());
        startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        if (!query.isEmpty()) {
            mainPresenter.searchArtist(query);
        }

        return false;
    }


    @Override
    public boolean onQueryTextChange(String newText) {
        hideText();
        return false;
    }

    private void hideRecycler() {
        this.recyclerView.setVisibility(View.GONE);
    }

    private void showRecycler() {
        this.recyclerView.setVisibility(View.VISIBLE);
    }

    private void hideText() {
        this.txtVwMain.setVisibility(View.GONE);
    }

    private void showText() {
        this.txtVwMain.setVisibility(View.VISIBLE);
    }
}
