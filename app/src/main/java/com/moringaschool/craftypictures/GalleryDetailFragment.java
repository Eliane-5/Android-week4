package com.moringaschool.craftypictures;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.moringaschool.craftypictures.models.Business;
import com.moringaschool.craftypictures.models.Category;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GalleryDetailFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.galleryImageView) ImageView mImageLabel;
    @BindView(R.id.galleryNameTextView) TextView mNameLabel;
    @BindView(R.id.categoryTextView) TextView mCategoriesLabel;
    @BindView(R.id.websiteTextView) TextView mWebsiteLabel;
    @BindView(R.id.phoneTextView) TextView mPhoneLabel;
    @BindView(R.id.addressTextView) TextView mAddressLabel;

    private Business mGallery;

    public GalleryDetailFragment() {
        // Required empty public constructor
    }

    public static GalleryDetailFragment newInstance(Business gallery) {
        GalleryDetailFragment galleryDetailFragment = new GalleryDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("gallery", Parcels.wrap(gallery));
        galleryDetailFragment.setArguments(args);
        return galleryDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGallery = Parcels.unwrap(getArguments().getParcelable("gallery"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.get().load(mGallery.getImageUrl()).into(mImageLabel);

        List<String> categories = new ArrayList<>();
        for (Category category: mGallery.getCategories()) {
            categories.add(category.getTitle());
        }

        mNameLabel.setText(mGallery.getName());
        mCategoriesLabel.setText(android.text.TextUtils.join(", ", categories));
        mPhoneLabel.setText(mGallery.getPhone());
        mAddressLabel.setText(mGallery.getLocation().toString());
        mWebsiteLabel.setOnClickListener(this);
        mPhoneLabel.setOnClickListener(this);
        mAddressLabel.setOnClickListener(this);
        return view;
    }
    @Override
    public void onClick(View v) {
        if (v == mWebsiteLabel) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(mGallery.getUrl()));
            startActivity(webIntent);
        }
        if (v == mPhoneLabel) {
            Intent phoneIntent = new Intent(Intent.ACTION_DIAL,
                    Uri.parse("tel:" + mGallery.getPhone()));
            startActivity(phoneIntent);
        }
        if (v == mAddressLabel) {
            Intent mapIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("geo:" + mGallery.getCoordinates().getLatitude()
                            + "," + mGallery.getCoordinates().getLongitude()
                            + "?q=(" + mGallery.getName() + ")"));
            startActivity(mapIntent);
        }
    }
}
