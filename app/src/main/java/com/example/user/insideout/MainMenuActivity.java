package com.example.user.insideout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;

/**
 * Created by user on 17-May-17.
 */
public class MainMenuActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_layout);

        GridView gridview = (GridView) findViewById(R.id.gridViewMainMenu);
        gridview.setAdapter(new GridViewAdapter(this, 1));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                //item position click will open new pages
                switch (position){
                    case 0:// Create New
                        Intent iCreate = new Intent(MainMenuActivity.this, CreateNewActivity.class);
                        startActivity(iCreate);
                        break;

                    case 1:// My Projects
                        Intent iMyProjects = new Intent(MainMenuActivity.this, MyProjectsActivity.class);
                        startActivity(iMyProjects);
                        break;
                    case 2:
                        //
                    case 3:
                        //

                }


            }
        });

    }



}
