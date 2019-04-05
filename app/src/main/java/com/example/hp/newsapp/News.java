package com.example.hp.newsapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.util.Date;

public class News {
    private String title;
    private String date;
    private String url;
    private String imgUrl;
    public  Bitmap newBitmap;

    public News(String mTitle,String mDate,String mUrl,String mImgUrl) {
        title = mTitle;
        date = mDate;
        url = mUrl;
        imgUrl = mImgUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public Bitmap getBitmap() {
        ImageRequestAsk task = new ImageRequestAsk();
        task.execute(imgUrl);
        Bitmap bitmap_b =task.getImage();
        return bitmap_b;
    }
    private class ImageRequestAsk extends AsyncTask<String,Void,Bitmap> {

        @Override
        protected Bitmap doInBackground(String... params) {
            try {
                InputStream inputStream = new java.net.URL(params[0]).openStream();
                return BitmapFactory.decodeStream(inputStream);
            }catch (Exception e) { return null;}
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            newBitmap = bitmap;
        }

        public Bitmap getImage() {return newBitmap;}

    }
}
