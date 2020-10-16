package com.example.smartcitye_1.bean;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/15 at 8:04
 */
public class ServiceInfo{

    /**
     * serviceid : 8
     * serviceName : 银联支付
     * icon : http://localhost:8080/mobileA/images/tubiao11.png
     * url : https://www.baidu.com/
     * serviceType : 智慧服务
     * desc : aaa
     */

    private int serviceid;
    private String serviceName;
    private String icon;
    private String url;
    private String serviceType;
    private String desc;

    public int getServiceid() {
        return serviceid;
    }

    public void setServiceid(int serviceid) {
        this.serviceid = serviceid;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
