package com.example.smartcitye_1.bean;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.List;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/16 at 9:36
 */
public class Sytg implements Serializable {

    private String msg, title;
    private int image;

    public Sytg() {
    }

    public Sytg(String msg, String title, int image) {
        this.msg = msg;
        this.title = title;
        this.image = image;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
