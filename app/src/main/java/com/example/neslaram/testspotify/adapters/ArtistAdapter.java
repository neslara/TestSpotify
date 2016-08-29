package com.example.neslaram.testspotify.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.neslaram.testspotify.R;
import com.example.neslaram.testspotify.beans.Artist;
import com.example.neslaram.testspotify.utils.BlurBuilder;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ViewHolder> {


    private static final String TAG = ArtistAdapter.class.getSimpleName();
    private List<Artist> artists;
    private OnItemClickListener<Artist> mItemClickListener;
    private Context context;


    public ArtistAdapter(List<Artist> artists, OnItemClickListener<Artist> itemClickListener, Context context) {
        this.artists = artists;
        this.mItemClickListener = itemClickListener;
        this.context = context;

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
            ((CardView) itemView).setPreventCornerOverlap(false);
            itemView.setOnClickListener(this);
        }

        public void bindViewHolder() {
            txtArtistname.setText(artist.getName());
            imgCover.setImageResource(R.drawable.ic_library_music);
            imgCover.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

            if (artist.getImages().size() > 1) {
                Glide.with(imgCover.getContext())
                        .load(artist.getImages().get(1).getUrl())
                        .asBitmap()
                        .centerCrop()
                        .listener(new RequestListener<String, Bitmap>() {
                            @Override
                            public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
//                                int height = imgCover.getHeight();
//                                Bitmap rectangle = createRect(0, height - txtArtistname.getHeight(), imgCover.getWidth(),height);
//                                Bitmap icon = BitmapFactory.decodeResource(imgCover.getContext().getResources(),
//                                        R.drawable.ic_library_music);
//                                imgCover.setImageBitmap(circleDimAround(icon, rectangle));
                                return true;
                            }

                            @Override
                            public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
//                                int height = imgCover.getHeight();
//                                Bitmap rectangle = createRect(0, height - txtArtistname.getHeight(), imgCover.getWidth(), height);
//                                imgCover.setImageBitmap(circleDimAround(resource, rectangle, imgCover.getContext()));
//                                Bitmap
                                if(resource.getConfig()== Bitmap.Config.ARGB_8888) {
                                    Bitmap blurredBitmap = BlurBuilder.blur(context, resource);

                                    imgCover.setBackgroundDrawable(new BitmapDrawable(context.getResources(), blurredBitmap));
                                }
                                else{
                                    imgCover.setImageBitmap(resource);
                                }
                                return true;
                            }
                        })
                        .into(imgCover);
            }
//            else{
//                int height = imgCover.getHeight();
//                Bitmap rectangle = createRect(0, height - txtArtistname.getHeight(), imgCover.getWidth(), height);
//                Bitmap icon = BitmapFactory.decodeResource(imgCover.getContext().getResources(),
//                        R.drawable.ic_library_music);
//                imgCover.setImageBitmap(circleDimAround(icon, rectangle));
//
//            }
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null)
                mItemClickListener.onItemClicked(getAdapterPosition(), artist);
        }

        public void setArtist(Artist artist) {
            this.artist = artist;
        }

        private Bitmap circleDimAround(Bitmap original, Bitmap mask, Context context) {
            Bitmap bitmap = Bitmap.createBitmap(original.getWidth(), original.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);

            // Draw the original bitmap
//            canvas.drawBitmap(BlurBuilder.blur(context, original), 0, 0, null);
            canvas.drawBitmap(original, 0, 0, null);
//
//            // DST_IN = Whatever was there, keep the part that overlaps with what I'm drawing now
//            Paint maskPaint = new Paint();
//            maskPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
//            canvas.drawBitmap(mask, 0, 0, maskPaint);
//
//            // DST_OVER = Whatever was there, put it over what I'm drawing now
//            Paint overPaint = new Paint();
//            overPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OVER));
//            canvas.drawBitmap(original, 0, 0, overPaint);

            return bitmap;
        }

        public Bitmap createRect(int x, int y, int width, int height) {
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.BLUE);

            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            canvas.drawRect(new Rect(x, y, width, height), paint);
            return bitmap;
        }


    }
}