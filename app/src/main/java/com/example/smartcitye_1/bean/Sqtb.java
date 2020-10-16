package com.example.smartcitye_1.bean;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.List;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/16 at 9:07
 */
public class Sqtb  implements Serializable {
    private String title,cotent,time,people;

    public Sqtb(String title, String cotent, String time, String people) {
        this.title = title;
        this.cotent = cotent;
        this.time = time;
        this.people = people;
    }
    private List<Bitmap> bitmaps;

    public List<Bitmap> getBitmaps() {
        return bitmaps;
    }

    public void setBitmaps(List<Bitmap> bitmaps) {
        this.bitmaps = bitmaps;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCotent() {
        return cotent;
    }

    public void setCotent(String cotent) {
        this.cotent = cotent;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }
}
