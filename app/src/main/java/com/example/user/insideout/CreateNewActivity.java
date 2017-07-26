package com.example.user.insideout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by user on 17-May-17.
 */
public class CreateNewActivity extends MainMenuActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;

    private String mCurrentPhotoPath;
    private String newPicFile = "";

    private ImageButton backButton;
    private ListView createOption;
    Context context;


    // input string for project name
    private String projectName = "My_Project";
    private Integer projectCount = 0;


    public static String[] categoryName = {"Take Photos", "Import"};
    public static Integer[] categoryId = {R.drawable.camera, R.drawable.image};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_layout);

        //toolbar
        /*Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);*/

        // List of the create new activity
        context = this;
        createOption = (ListView) findViewById(R.id.listViewCreateNew);

        createOption.setAdapter(new CustomListAdapter(this, categoryName, categoryId));

        createOption.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    showNameDialog();

                    // for taking continuous images
                    //Intent camera = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);

                }

                if (position == 1) {
                    //import photos function
                    Intent photoPickIntent = new Intent(getBaseContext(), PhotoPickerActivity.class);
                    startActivity(photoPickIntent);
                }
            }
        });

    }

    // get pop-up xml view
    protected void showNameDialog() {

        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.popup_projectname_layout, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(promptsView); // setting the View to  Alert Dialog

        final EditText userInput = (EditText) promptsView.findViewById(R.id.editTextProjectName);

        //Dialog box button and text
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (userInput.getText().length() <= 0) {
                            projectName = "My_Project ";
                            projectName = projectName + ++projectCount;
                        } else {
                            projectName = userInput.getText().toString();
                        }


                        takePhotos();


                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        ;
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();

    }

    //called after entering project name
    public void takePhotos() {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        Intent takePictureIntent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                Log.e("err: ", ex.toString());
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = Uri.fromFile(photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }

        //display the image to another activity


    }

    //creating image file
    private File createImageFile() throws IOException {
        // Create an image file name

        String imageFileName = Util.validFileName(projectName + Util.generateGUID() + ".jpg");
//        String imageFileName = Util.validFileName(Util.generateGUID());
        newPicFile = imageFileName;
//        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = null;
        try {
            image = File.createTempFile(
                    imageFileName,  /* prefix */
                    ".jpg",         /* suffix */
                    storageDir      /* directory */
            );
        } catch (IOException e) {
            Log.e("Err image : ", e.toString());
        }

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //condition for captured images
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

            try {

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.RGB_565;
                options.inSampleSize = 4; //number the higher the smaller resolution
                Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, options);
                if (!new File(getFilesDir() + "/image").exists()) {
                    new File(getFilesDir() + "/image").mkdir();
                }
                File file = new File(this.getFilesDir() + "/image/" + newPicFile);

                FileOutputStream /*//call the camera intent
                          Intent ccameraIntent = new Intent(getBaseContext(), TakenPhotoActivity.class);
                          ccameraIntent.putExtra("PROJECT_NAME",projectName);
                          startActivity(ccameraIntent);*/out = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);

                out.flush();
                out.close();

                File fDel = new File(mCurrentPhotoPath); // Delete from the device storage (i.e : SD Card)
                fDel.delete();

                //call the camera intent
                viewPhoto();

            } catch (Exception exx) {
                exx.printStackTrace();
            }
        }

        else {
//            Toast.makeText(this, "directory not found ", Toast.LENGTH_LONG).show();
        }
    }

    //after taking picture and saving it calling the activity to view the image jaust taken
    private void viewPhoto() {
        Intent viewImageIntent = new Intent(getBaseContext(), TakenPhotoActivity.class);
        viewImageIntent.putExtra("PROJECT_NAME", projectName);
        viewImageIntent.putExtra("IMAGE_FILE", newPicFile);
        startActivity(viewImageIntent);
    }

    // Initiating Menu XML file (menu.xml)
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

 //menu item click action
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        switch (item.getItemId())
        {
            case R.id.action_showProject_list:
                // Single menu item is selected do something
                // Ex: launching new activity/screen or show alert message
                Intent actionMuProjectIntent = new Intent(CreateNewActivity.this,
                        MyProjectsActivity.class);
                startActivity(actionMuProjectIntent);
                return true;

            case R.id.action_help:
                Intent actionHelpIntent = new Intent(CreateNewActivity.this,
                        HelpActivity.class);
                startActivity(actionHelpIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
