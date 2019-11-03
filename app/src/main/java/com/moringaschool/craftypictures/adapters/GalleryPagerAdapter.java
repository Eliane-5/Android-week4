package com.moringaschool.craftypictures.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.moringaschool.craftypictures.GalleryDetailFragment;
import com.moringaschool.craftypictures.models.Business;

import java.util.List;

public class GalleryPagerAdapter extends FragmentPagerAdapter {
    private List<Business> mGalleries;

    public GalleryPagerAdapter(FragmentManager fm, int behavior, List<Business> galleries) {
        super(fm, behavior);
        mGalleries = galleries;
    }

    @Override
    public Fragment getItem(int position) {
        return GalleryDetailFragment.newInstance(mGalleries.get(position));
    }

    @Override
    public int getCount() {
        return mGalleries.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mGalleries.get(position).getName();
    }
}
