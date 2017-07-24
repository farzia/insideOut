package com.example.user.insideout;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by user on 10-Jul-17.
 */

public class GridViewAdapter extends BaseAdapter {

    private Context context;
    private Integer activityId = 0;

    //constructer
    public GridViewAdapter(Context context, int activityId){

        this.context= context;
        this.activityId= activityId;
    }
    @Override
    public int getCount() {
        if(activityId ==1){
            return mainMenuImageIcon.length;
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
      return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        //ImageView imageView;
        View gridView;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null) {
            // if it's not recycled, initialize some attributes
            /*imageView = new ImageView(context);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(100,100));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);*/

            gridView = new View(context);
            gridView = inflater.inflate(R.layout.custom_gridview_layout,null);
            ImageView iconImage = (ImageView)gridView.findViewById(R.id.imageViewGrid);
            TextView iconText= (TextView)gridView.findViewById(R.id.textViewGrid);

            iconImage.setImageResource(mainMenuImageIcon[position]);
            iconText.setText(mainMenuIconText[position]);


        } else {
            //imageView = (ImageView) view;
            gridView= (View)view;
        }

        //imageView.setImageResource(mainMenuImageIcon[position]);
        //return imageView;
        return gridView;
    }

    // global variables for this class
    //array of images, varies from activity to activity

    // icon images for main menu
    private Integer[] mainMenuImageIcon= { R.drawable.create,
            R.drawable.project, R.drawable.cloud,
            R.drawable.settings, R.drawable.help
    };
    // titles for main menu
    private String [] mainMenuIconText= {"Create New",
            "My Projects", "My Cloud"  ,"Settings","Help"
    };

}
