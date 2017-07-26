package com.example.user.insideout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by user on 27-Jul-17.
 */

public class PhotoPickerActivity extends AppCompatActivity {

    private static final int RESULT_LOAD_IMG= 2;

    ImageView photoTaken;
    TextView description;
    Button processButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Image from Gallery");
        setContentView(R.layout.photo_layout);

        photoTaken = (ImageView)findViewById(R.id.imageViewPhotoTaken);

        description= (TextView)findViewById(R.id.textViewDescription);

        processButton= (Button)findViewById(R.id.buttonProcess) ;

        getPhotoFromGallery();// start action


    }

    private void getPhotoFromGallery() {

        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        //condition for gallery images

        if(requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK){

            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                photoTaken.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }

    }






}
