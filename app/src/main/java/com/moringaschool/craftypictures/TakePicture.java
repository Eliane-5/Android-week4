package com.moringaschool.craftypictures;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
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
    static final int REQUEST_IMAGE_CAPTURE = 1;
    String pathToFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_picture);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        if (Build.VERSION.SDK_INT >= 23){
            requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
        }
        mPictureButton.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_OK){
            if (requestCode == 1){
                Bitmap bitmap = BitmapFactory.decodeFile(pathToFile);
                mCapturedImage.setImageBitmap(bitmap);
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                mCapturedImage.setImageBitmap(imageBitmap);
            }
        }
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bitmap bitmap = BitmapFactory.decodeFile(pathToFile);
            mCapturedImage.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == mPictureButton){
            takePictureAction();
        }
    }

    private void takePictureAction() {
        Intent takeSnap = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takeSnap.resolveActivity(getPackageManager())!= null){
            File pictureFile = null;
            pictureFile = createPictureFile();
            if (pictureFile != null){
                pathToFile = pictureFile.getAbsolutePath();
                Uri pictureUri = FileProvider.getUriForFile(TakePicture.this,"com.moringaschool.craftypictures.FileProvider", pictureFile);
                takeSnap.putExtra(MediaStore.EXTRA_OUTPUT, pictureUri);
                startActivityForResult(takeSnap,1);
            }
        }
    }

    private File createPictureFile() {
        String name = new SimpleDateFormat("yyyyMMdd_MMmmss").format(new Date());
        File storageDir = getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File picture = null;
        try {
            picture = File.createTempFile(name, ".png", storageDir);
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("my Log", "Exception"+ e.toString());
        }
        return picture;
    }
}
