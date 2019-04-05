package com.example.hp.newsapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class NewsAdapter extends ArrayAdapter<News> {
    public NewsAdapter(@NonNull Context context, ArrayList<News> news) {
        super(context, 0, news);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.news_list, parent, false);
        }
        final News currentInfo = getItem(position);

        TextView title = listItemView.findViewById(R.id.title_view);
        title.setText(currentInfo.getTitle());

        TextView date = listItemView.findViewById(R.id.date_view);
        TextView time = listItemView.findViewById(R.id.time_view);
        String published = currentInfo.getDate();
        String primaryTime;
        String primaryDate;
        if (published.contains("T")) {
            String[] parts = published.split("T");
            primaryDate = parts[0];
            primaryTime = parts[1];
        } else {
            primaryDate = "error";
            primaryTime = "error";
        }
        String newString = primaryTime.substring(0, 5);
        date.setText(primaryDate);
        time.setText(newString);

        ImageView img = (ImageView) listItemView.findViewById(R.id.img);
        Bitmap bitmap = currentInfo.getBitmap();
        img.setImageBitmap(bitmap);
        return listItemView;
    }
     /*   private class ImageRequestAsk extends AsyncTask<String,Void,Bitmap> {
           private ImageView imageViewPhoto;
           public ImageRequestAsk(ImageView imageViewPhoto) {
               this.imageViewPhoto = imageViewPhoto;
           }
            @Override
            protected Bitmap doInBackground(String... params) {
                try {
                    InputStream inputStream = new java.net.URL(params[0]).openStream();
                    return BitmapFactory.decodeStream(inputStream);
                }catch (Exception e) { return null;}
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                imageViewPhoto.setImageBitmap(bitmap);
            }
        }*/
}
