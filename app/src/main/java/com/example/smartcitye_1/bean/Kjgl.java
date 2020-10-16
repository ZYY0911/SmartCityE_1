package com.example.smartcitye_1.bean;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/16 at 8:47
 */
public class Kjgl  {
    private String name,time,people,num;

    public Kjgl(String name, String time, String people, String num) {
        this.name = name;
        this.time = time;
        this.people = people;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
