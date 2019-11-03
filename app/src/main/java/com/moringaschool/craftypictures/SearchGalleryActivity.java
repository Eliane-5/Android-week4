package com.moringaschool.craftypictures;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.moringaschool.craftypictures.adapters.GalleriesArrayAdapter;
import com.moringaschool.craftypictures.models.Business;
import com.moringaschool.craftypictures.models.YelpGalleriesSearchResponse;
import com.moringaschool.craftypictures.network.YelpApi;
import com.moringaschool.craftypictures.network.YelpClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchGalleryActivity extends AppCompatActivity {
    private static final String TAG = SearchGalleryActivity.class.getSimpleName();

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;
    private GalleriesArrayAdapter mAdapter;

    public List<Business> galleries;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_gallery);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");

        YelpApi client = YelpClient.getClient();

        Call<YelpGalleriesSearchResponse> call = client.getGalleries(location, "art gallery");

        call.enqueue(new Callback<YelpGalleriesSearchResponse>() {
            @Override
            public void onResponse(Call<YelpGalleriesSearchResponse> call, Response<YelpGalleriesSearchResponse> response) {
                hideProgressBar();

                if (response.isSuccessful()) {
                    galleries = response.body().getBusinesses();
                    mAdapter = new GalleriesArrayAdapter(SearchGalleryActivity.this, galleries);
                    mRecyclerView.setAdapter(mAdapter);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SearchGalleryActivity.this);
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setHasFixedSize(true);

                    showGalleries();
                } else {
                    showUnsuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<YelpGalleriesSearchResponse> call, Throwable t) {
                hideProgressBar();
                showFailureMessage();
            }

        });
    }

    private void showFailureMessage() {
        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        mErrorTextView.setText("Something went wrong. Please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showGalleries() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }
}
