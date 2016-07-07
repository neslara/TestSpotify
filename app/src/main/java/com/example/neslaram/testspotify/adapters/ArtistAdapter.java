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

/**
 * Created by desarrollo on 7/7/16.
 */
public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ViewHolder> {


    private List<Artist> artists;

    public ArtistAdapter(List<Artist> artists) {
        this.artists = artists;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_artist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final int index = holder.getAdapterPosition();

        Artist artist = artists.get(index);

        holder.txtArtistName.setText(artist.getName());
        if (artist.getImages().size() > 1) {
            Glide.with(holder.imgVwCover.getContext())
                    .load(artist.getImages().get(1).getUrl())
                    .into(holder.imgVwCover);
        }

    }

    @Override
    public int getItemCount() {
        return artists.size();
    }

    public void addItems(List<Artist> artists) {
        this.artists = artists;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgVwCover;
        TextView txtArtistName;

        public ViewHolder(View itemView) {
            super(itemView);
            imgVwCover = (ImageView) itemView.findViewById(R.id.img_cover);
            txtArtistName = (TextView) itemView.findViewById(R.id.txt_artistname);
        }
    }
}