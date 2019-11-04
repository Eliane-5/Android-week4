package com.moringaschool.craftypictures;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TakePicture extends AppCompatActivity {
    @BindView(R.id.pictureButton) Button mPictureButton;
    @BindView(R.id.capturedImage) ImageView mCapturedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_picture);
        ButterKnife.bind(this);

    }
}
