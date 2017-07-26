package com.example.user.insideout;

/**
 * Created by user on 25-Jul-17.
 */


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by admin on 11/21/2016.
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder>
{

    private ArrayList<String> images;
    private OnItemClick itemClick;
    public Context context;
    public ImageAdapter(Context context, ArrayList<String> allImages)
    {
        this.context=context;
        images = allImages;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_image_list_layout, null);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        String imageFileName =images.get(position);
        File f = new File(context.getFilesDir()+"/" + imageFileName);
        Matrix matrix = new Matrix(); //android graphcis
        matrix.postRotate(getImageOrientation(context.getFilesDir() + "/" + imageFileName));
        //matrix.postRotate(getImageOrientation(hmItem.get("image_filename").toString()));

        BitmapFactory.Options options = new BitmapFactory.Options();
        //options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inSampleSize = 4; //number the higher the smaller resolution

        Bitmap bm = BitmapFactory.decodeFile(f.getAbsolutePath(), options);
        Bitmap rotatedBitmap = Bitmap.createBitmap(bm, 0, 0,
                bm.getWidth(),
                bm.getHeight(), matrix, true);

        holder.image.setImageBitmap(rotatedBitmap);
//        String imageUrl=getImage(category.imageUrl);

        /*Picasso.with(context).load(imageUrl)
                .error(R.drawable.noimage)
                .placeholder(R.drawable.noimage)
                .into(holder.image);
        holder.text.setText(Html.fromHtml(category.name));*/
    }

    //auto set image orientation
    public static int getImageOrientation(String imagePath){
        int rotate = 0;
        try {

            File imageFile = new File(imagePath);
            ExifInterface exif = new ExifInterface(
                    imageFile.getAbsolutePath());
            int orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);

            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotate = 270;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotate = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotate = 90;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return rotate;
    }

    @Override
    public int getItemCount() {
        return images.size();
    }
   /* private  String getImage(String image){
        if(image==null || image.isEmpty())
            return ApplicationController.API_BASE_URL+"/Content/img/noimage.png";
        else
            return image;
    }*/





    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public ImageView image;
        public TextView text;
        public ViewHolder(View itemView)
        {
            super(itemView);
            itemView.setClickable(true);
            itemView.setOnClickListener((View.OnClickListener) this);
            image= (ImageView) itemView.findViewById(R.id.category_image);


        }
        @SuppressWarnings("deprecation")
        @Override
        public void onClick(View v) {
            if (itemClick != null) {
//                itemClick.onItemClicked(categories.get(getPosition()).id);
            }
        }
    }
    public interface OnItemClick {
        public void onItemClicked(int position);
    }
    public OnItemClick getItemClick() {
        return itemClick;
    }

    public void setItemClick(OnItemClick itemClick) {
        this.itemClick = itemClick;
    }

}
