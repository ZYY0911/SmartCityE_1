package com.example.smartcitye_1.bean;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/16 at 8:14
 */
public class Smart_home {
    private int image;
    private String msg;

    public Smart_home(int image, String msg) {
        this.image = image;
        this.msg = msg;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
