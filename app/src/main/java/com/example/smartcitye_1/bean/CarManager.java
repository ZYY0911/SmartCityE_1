package com.example.smartcitye_1.bean;

import java.io.Serializable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/16 at 9:53
 */
public class CarManager implements Serializable {
    private String cp,cwh,tch,cz,tel,home,address;

    public CarManager(String cp, String cwh, String tch, String cz, String tel, String home, String address) {
        this.cp = cp;
        this.cwh = cwh;
        this.tch = tch;
        this.cz = cz;
        this.tel = tel;
        this.home = home;
        this.address = address;
    }

    public CarManager() {
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getCwh() {
        return cwh;
    }

    public void setCwh(String cwh) {
        this.cwh = cwh;
    }

    public String getTch() {
        return tch;
    }

    public void setTch(String tch) {
        this.tch = tch;
    }

    public String getCz() {
        return cz;
    }

    public void setCz(String cz) {
        this.cz = cz;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
