package com.example.neslaram.testspotify.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.neslaram.testspotify.R;
import com.example.neslaram.testspotify.beans.Album;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {

    private List<Album> albums;

    public AlbumAdapter(List<Album> albums) {
        this.albums = albums;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_album, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final int index = holder.getAdapterPosition();

        holder.setAlbum(albums.get(index));
        holder.bindViewHolder();
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    public void addItems(List<Album> artists) {
        this.albums = artists;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.img_cover)
        ImageView imgCover;
        @Bind(R.id.txt_album_name)
        TextView txtAlbumName;

        private Album album;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindViewHolder() {
            txtAlbumName.setText(album.getName());
            if (album.getImages().size() > 0) {
                Glide.with(imgCover.getContext())
                        .load(album.getImages().get(0).getUrl())
                        .into(imgCover);
            }
        }

        public void setAlbum(Album album) {
            this.album = album;
        }
    }
}