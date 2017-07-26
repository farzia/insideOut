package com.example.user.insideout;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;


/**
 * Created by Dell on 1/23/2016.
 */
public class Util {

    public static String generateGUID() {
        return UUID.randomUUID().toString();
    }

    public static String validFileName(String filename) {
        return filename.replaceAll("[^a-zA-Z0-9.-]", "_");
    }

    public static final String dateToday() {
        Calendar calendar = Calendar.getInstance();
//date format is:  "Date-Month-Year Hour:Minutes am/pm"
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy"); //Date and time
        String currentDate = sdf.format(calendar.getTime());

//Day of Name in full form like,"Saturday", or if you need the first three characters you have to put "EEE" in the date format and your result will be "Sat".
        SimpleDateFormat sdf_ = new SimpleDateFormat("EEEE");
        Date date = new Date();
        String dayName = sdf_.format(date);
        return "" + dayName + " " + currentDate + "";
    }

    //UI
    public static boolean setListViewHeightBasedOnItems(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter != null) {

            int numberOfItems = listAdapter.getCount();

            // Get total height of all items.
            int totalItemsHeight = 0;
            for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
                //View item = listAdapter.getView(itemPos, null, listView);
                //item.measure(0, 0);
                //totalItemsHeight += item.getMeasuredHeight();

                listView.measure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED),
                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

                totalItemsHeight += listView.getMeasuredHeight();
            }

            // Get total height of all item dividers.
            int totalDividersHeight = listView.getDividerHeight() *
                    (numberOfItems - 1);

            // Set list height.
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalItemsHeight + totalDividersHeight;// + 6; //6 as little margin
            listView.setLayoutParams(params);
            listView.requestLayout();

            return true;

        } else {
            return false;
        }
    }

    public static void processErrorLog(Context mContext, String error) {
        if (!new File(mContext.getFilesDir() + "/error").exists()) {
            new File(mContext.getFilesDir() + "/error").mkdir();
        }
        try {
            File file = new File(mContext.getFilesDir() + "/error/" + "exception.txt");
            if (file.exists()){
                FileOutputStream os = new FileOutputStream(file,true);
                OutputStreamWriter osw = new OutputStreamWriter(os);
                osw.append(error);
                osw.flush();
                osw.close();

            }else {
                file.createNewFile();
                FileOutputStream os = new FileOutputStream(file);
                os.write(error.getBytes());
                os.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
