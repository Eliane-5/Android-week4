package com.moringaschool.craftypictures;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LocationActivity extends AppCompatActivity {
    public static final String TAG = LocationActivity.class.getSimpleName();
    @BindView(R.id.locationEditText) EditText mLocationEditText;
    @BindView(R.id.submitLocationButton) Button mSubmitLocationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        mSubmitLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location = mLocationEditText.getText().toString();
                Log.d(TAG, location);
                Intent intent = new Intent(LocationActivity.this, SearchGalleryActivity.class);
                intent.putExtra("location",location);
                startActivity(intent);
            }
        });
    }
}
