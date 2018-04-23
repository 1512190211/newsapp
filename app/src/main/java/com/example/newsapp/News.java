package com.example.newsapp;

public class News {
    private String title;
    private String source;
    private String firstImg;
    private String url;

    public News(String title,String source,String firstImg,String url){
        this.title=title;
        this.source=source;
        this.firstImg=firstImg;
        this.url=url;
    }
    public News(){
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getFirstImg() {
        return firstImg;
    }

    public void setFirstImg(String firstImg) {
        this.firstImg = firstImg;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
