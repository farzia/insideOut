package com.example.user.insideout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.io.LineNumberReader;

/**
 * Created by user on 17-May-17.
 */
public class CreateNewActivity extends MainMenuActivity {
    private ImageButton backButton;
    private ListView createOption;
    Context context;


    // input string for project name
    private String projectName= "My Project";
    private Integer projectCount= 0;


    public static String [] categoryName={"Take Photos","Import" };
    public static Integer [] categoryId={R.drawable.camera, R.drawable.image };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_layout);

        //toolbar
        /*Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);*/

        // List of the create new activity
        context = this;
        createOption= (ListView)findViewById(R.id.listViewCreateNew);

        createOption.setAdapter(new CustomListAdapter(this, categoryName, categoryId ));

        createOption.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position== 0){
                    showNameDialog();

                    // for taking continuous images
                    //Intent camera = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);

                }

                if(position==1){
                    //import photos function
                }
            }
        });

      }

    // get pop-up xml view
      protected void showNameDialog(){

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

                          if(userInput.getText().length()<=0){
                              projectName= projectName + ++projectCount;
                          }else{
                              projectName= userInput.getText().toString();
                          }
                          //call the camera intent
                          Intent ccameraIntent = new Intent(getBaseContext(), TakePhotoActivity.class);
                          ccameraIntent.putExtra("PROJECT_NAME",projectName);
                          startActivity(ccameraIntent);

                      }
                  })
                  .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialog, int which) {
                          dialog.cancel();;
                      }
                  });

          // create alert dialog
          AlertDialog alertDialog = alertDialogBuilder.create();
          // show it
          alertDialog.show();

      }


    }
