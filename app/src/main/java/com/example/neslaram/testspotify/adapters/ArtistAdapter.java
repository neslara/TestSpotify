package com.example.neslaram.testspotify.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.neslaram.testspotify.R;
import com.example.neslaram.testspotify.beans.Artist;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ViewHolder> {


    private List<Artist> artists;
    private OnItemClickListener<Artist> mItemClickListener;


    public ArtistAdapter(List<Artist> artists, OnItemClickListener<Artist> itemClickListener) {
        this.artists = artists;
        this.mItemClickListener = itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_artist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final int index = holder.getAdapterPosition();

        holder.setArtist(artists.get(index));
        holder.bindViewHolder();
    }

    @Override
    public int getItemCount() {
        return artists.size();
    }

    public void addItems(List<Artist> artists) {
        this.artists = artists;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.img_cover)
        ImageView imgCover;
        @Bind(R.id.txt_artistname)
        TextView txtArtistname;

        private Artist artist;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void bindViewHolder() {
            txtArtistname.setText(artist.getName());
            if (artist.getImages().size() > 0) {
                Glide.with(imgCover.getContext())
                        .load(artist.getImages().get(0).getUrl())
                        .into(imgCover);
            }
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null)
                mItemClickListener.onItemClicked(getAdapterPosition(), artist);
        }

        public void setArtist(Artist artist) {
            this.artist = artist;
        }
    }
}