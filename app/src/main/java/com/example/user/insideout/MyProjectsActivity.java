package com.example.user.insideout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by user on 25-Jul-17.
 */

public class MyProjectsActivity extends AppCompatActivity{

    private RecyclerView recycledImageList;
    private GridLayoutManager mLayoutManager;
    private ImageAdapter mAdapter;
    private ArrayList<String> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_projects_layout);
        recycledImageList = (RecyclerView) findViewById(R.id.taken_pictures);

        try {
            mAdapter = new ImageAdapter(this, getImages());
            recycledImageList.setAdapter(mAdapter);
            recycledImageList.setLayoutManager(new GridLayoutManager(this, 2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getImages() throws Exception {

        ArrayList<String> imgFiles = new ArrayList<String>();
        if (!new File(getFilesDir() + "/image").exists()) {
            new File(getFilesDir() + "/image").mkdir();
        }
        File file[] = new File(getFilesDir() + "/image").listFiles();
        for (int i = 0; i < file.length; i++) {
            //Log.d("Files", "FileName:" + file[i].getName());
            if (file[i].getName().indexOf(".jpg") != -1) {
                Log.d("TAG", "image_filename: " + "do/" + file[i].getName());
                imgFiles.add("image/" + file[i].getName());
            }
        }
        return imgFiles;
    }

}
