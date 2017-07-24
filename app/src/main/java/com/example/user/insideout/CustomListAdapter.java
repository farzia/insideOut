package com.example.user.insideout;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by user on 17-May-17.
 */
public class CustomListAdapter extends BaseAdapter {

    Context context;
    String [] itemName;
    Integer [] itemId;

    public CustomListAdapter(Activity context, String[] itemName, Integer[] itemId){

        this.context = context;
        this.itemName= itemName;
        this.itemId= itemId;

    }
    @Override
    public int getCount() { return itemName.length; }

    @Override
    public Object getItem(int position) { return position; }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        ViewHolder holder =new ViewHolder();

        View rowView;

        rowView = inflater.inflate(R.layout.custom_list_layout,null);
        holder.holderTextView = (TextView)rowView.findViewById(R.id.customTextView);
        holder.holderImageView= (ImageView)rowView.findViewById(R.id.customImageView);

        holder.holderTextView.setText(itemName[position]);
        holder.holderImageView.setImageResource(itemId[position]);


        return rowView;
    }
}
