package com.example.user.insideout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by user on 27-Jul-17.
 */

public class HelpActivity extends AppCompatActivity {

    TextView instruction1, instruction2, instruction3, instruction4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_layout);

        instruction1= (TextView)findViewById(R.id.textViewInstructionCreate1);
        instruction1.setText("Take Photo: takes pictures and processes it to view information about it/n");

        instruction2= (TextView)findViewById(R.id.textViewInstructionCreate2);
        instruction2.setText("Import: lets you select pictures from your phone gallery for processing");

        instruction3= (TextView)findViewById(R.id.textViewInstructionMyProjects);
        instruction3.setText("Displays all the projects that has been created");

        instruction4= (TextView)findViewById(R.id.textViewInstructionMyCloud);
        instruction4.setText("Provides the Google Drive Services. Need to have a google account for using it to save and fetch");

    }

}
