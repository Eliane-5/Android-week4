package com.moringaschool.craftypictures;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TakePicture extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.pictureButton) Button mPictureButton;
    @BindView(R.id.capturedImage) ImageView mCapturedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_picture);
        ButterKnife.bind(this);

        mPictureButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == mPictureButton){
            takePictureAction();
        }
    }

    private void takePictureAction() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager())!= null){
            File pictureFile = null;
            try {
                pictureFile = createPictureFile();
            }catch (Exception){

            }
        }
    }

    private File createPictureFile() {

        return null;
    }
}
