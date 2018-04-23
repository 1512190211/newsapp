package com.example.newsapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class NewsAdapter extends ArrayAdapter<News> {
    private int resourceId;
    public NewsAdapter(@NonNull Context context, int resource, @NonNull List<News> objects) {
        super(context, resource, objects);
        resourceId=resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        News news=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        MyImageView newsImage=(MyImageView)view.findViewById(R.id.image_view);
        TextView newsTitle=(TextView)view.findViewById(R.id.title_view);
        TextView newsSource=(TextView)view.findViewById(R.id.source_view);
        newsTitle.setText(news.getTitle());
        newsImage.setImageURL(news.getFirstImg());
        newsSource.setText(news.getSource());
        return view;
    }


    //根据图片的URL路径来获取网络图片
    public Bitmap getBitmap(String url) {
        Bitmap bm = null;
        try {
            URL iconUrl = new URL(url);
            URLConnection conn = iconUrl.openConnection();
            HttpURLConnection http = (HttpURLConnection) conn;
            int length = http.getContentLength();
            conn.connect();
            // 获得图像的字符流
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is, length);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();// 关闭流
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return bm;
    }
}
