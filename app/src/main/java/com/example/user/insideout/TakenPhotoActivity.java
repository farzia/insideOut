package com.example.user.insideout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.user.insideout.ImageAdapter.getImageOrientation;

/**
 * Created by user on 22-Jul-17.
 */

public class TakenPhotoActivity extends MainMenuActivity {

    static final int REQUEST_TAKE_PHOTO = 1;
    String projectName="";

    ImageView photoTaken;
    TextView description;
    Button processButton;

    String imageFile= "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //getting intent extra value project and file name
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        projectName = (String)b.get("PROJECT_NAME");
        imageFile = (String)b.get("IMAGE_FILE");

        setTitle(projectName);// Activity title change according to project name
        setContentView(R.layout.photo_layout);

        photoTaken = (ImageView)findViewById(R.id.imageViewPhotoTaken);

        description= (TextView)findViewById(R.id.textViewDescription);

        processButton= (Button)findViewById(R.id.buttonProcess) ;

        loadImage();

        //takePhoto();
    }

    // loding the image into imageView
    private void loadImage(){
        File f = new File(getFilesDir()+"/image/" + imageFile);
        Matrix matrix = new Matrix(); //android graphics
        matrix.postRotate(getImageOrientation(getFilesDir() + "/image/" + imageFile));
        //matrix.postRotate(getImageOrientation(hmItem.get("image_filename").toString()));

        BitmapFactory.Options options = new BitmapFactory.Options();
        //options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inSampleSize = 4; //number the higher the smaller resolution

        Bitmap bm = BitmapFactory.decodeFile(f.getAbsolutePath(), options);
        Bitmap rotatedBitmap = Bitmap.createBitmap(bm, 0, 0,
                bm.getWidth(),
                bm.getHeight(), matrix, true);

        photoTaken.setImageBitmap(rotatedBitmap);
    }

   /* public void takePhoto(){
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = projectName+ "_"+timeStamp + ".jpg";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        imageFile = storageDir.getAbsolutePath() + "/" + imageFileName;
        File file = new File(imageFile);
        Uri outputFileUri = Uri.fromFile(file);

        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        //enabling whether there is a camera to handle the intent
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            startActivityForResult(cameraIntent, REQUEST_TAKE_PHOTO);
        }

    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String imageFileName = projectName + "_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName,".jpg", storageDir);
        // Save a file: path for use with ACTION_VIEW intents
        imageFile = image.getAbsolutePath();
        return image;
    }

    //camera result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        // super.onActivityResult(requestCode, resultCode, data);

        // if a photo has been taken
        if (resultCode == RESULT_OK && requestCode== REQUEST_TAKE_PHOTO ) {

            File imgFile = new  File(imageFile);
            if(imgFile.exists()){
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                photoTaken.setImageBitmap(myBitmap);

            }



            // code to previewthe image
            // result from camera
            //Bitmap photo = (Bitmap) data.getExtras().get("data");
            //setting the photo in imageView
            //photoTaken.setImageBitmap(photo);


            // storing the image to SD card
            //storeCameraPhotoInSDCard(photo,timeStamp,imageName);

            // getting the image file and displaying to the imageView

            // Bitmap nBitmap = getImageFileFromSDCard(imageName);
            // photoTaken.setImageBitmap(nBitmap);

        }

    }*/

}
