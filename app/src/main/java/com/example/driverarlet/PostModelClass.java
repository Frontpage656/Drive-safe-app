package com.example.driverarlet;

import android.widget.TextView;

public class PostModelClass {
    String author,content_summary,location,time,content_thumbnail;

    public PostModelClass(String author, String content_summary, String location, String time, String content_thumbnail) {
        this.author = author;
        this.content_summary = content_summary;
        this.location = location;
        this.time = time;
        this.content_thumbnail = content_thumbnail;
    }

    public PostModelClass() {
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent_summary() {
        return content_summary;
    }

    public void setContent_summary(String content_summary) {
        this.content_summary = content_summary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent_thumbnail() {
        return content_thumbnail;
    }

    public void setContent_thumbnail(String content_thumbnail) {
        this.content_thumbnail = content_thumbnail;
    }
}
