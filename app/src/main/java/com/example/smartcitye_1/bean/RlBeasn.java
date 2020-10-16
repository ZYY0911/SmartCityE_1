package com.example.smartcitye_1.bean;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/15 at 19:08
 */
public class RlBeasn {
    private int index,day,month,bg;

    public RlBeasn(int index, int day, int month, int bg) {
        this.index = index;
        this.day = day;
        this.month = month;
        this.bg = bg;
    }

    public RlBeasn() {
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getBg() {
        return bg;
    }

    public void setBg(int bg) {
        this.bg = bg;
    }
}
