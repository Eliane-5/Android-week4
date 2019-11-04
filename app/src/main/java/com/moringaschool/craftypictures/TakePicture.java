package com.moringaschool.craftypictures;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.os.Environment.getExternalStoragePublicDirectory;

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
            pictureFile = createPictureFile();
            if (pictureFile != null){
                String pathToFile = pictureFile.getAbsolutePath();
                Uri pictureUri = FileProvider.getUriForFile(TakePicture.this,"fgjkdjk",pictureFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, pictureUri);
                startActivityForResult(intent,1);
            }
        }
    }

    private File createPictureFile() {
        String name = new SimpleDateFormat("yyyyMMdd_MMmmss").format(new Date());
        File storageDir = getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = null;
        try {
            image = File.createTempFile(name, ".jpg", storageDir);
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("my Log", "Exception"+ e.toString());
        }
        return image;
    }
}
