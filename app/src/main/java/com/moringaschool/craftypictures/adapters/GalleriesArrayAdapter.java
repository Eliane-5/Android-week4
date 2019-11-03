package com.moringaschool.craftypictures.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.craftypictures.GalleryDetailActivity;
import com.moringaschool.craftypictures.R;
import com.moringaschool.craftypictures.models.Business;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GalleriesArrayAdapter extends RecyclerView.Adapter<GalleriesArrayAdapter.GalleryViewHolder>  {

    private Context mContext;
    private List<Business> mGalleries;


    public GalleriesArrayAdapter(Context context, List<Business> galleries) {
        mContext = context;
        mGalleries = galleries;
    }

    @Override
    public GalleryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_list_item, parent, false);
        GalleryViewHolder viewHolder = new GalleryViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(GalleryViewHolder holder, int position) {
        holder.bindGallery(mGalleries.get(position));
    }

    @Override
    public int getItemCount() {
        return mGalleries.size();
    }

    public class GalleryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.galleryImageView) ImageView mGalleryImageView;
        @BindView(R.id.galleryNameTextView) TextView mNameTextView;
        @BindView(R.id.categoryTextView) TextView mCategoryTextView;
        private Context mContext;

        public GalleryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, GalleryDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("galleries", Parcels.wrap(mGalleries));
            mContext.startActivity(intent);
        }

        public void bindGallery(Business gallery) {
            Picasso.get().load(gallery.getImageUrl()).into(mGalleryImageView);
            mNameTextView.setText(gallery.getName());
            mCategoryTextView.setText(gallery.getCategories().get(0).getTitle());
        }
    }
}