package com.example.nghia.lab03.jsonModels;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Nghia on 11/27/2016.
 */

public class Salon {
    @SerializedName("Fanpage")
    private String fanPage;
    @SerializedName("Name")
    private String name;
    @SerializedName("FanpageId")
    private String fanpageId;
    @SerializedName("ManagerName")
    private String managerName;
    @SerializedName("Phone")
    private String phone;
    @SerializedName("Images")
    private List<Image> images;
    @SerializedName("Id")
    private int Id;

    public Salon(String fanPage, String name, String fanpageId, String managerName, String phone, List<Image> images, int id) {
        this.fanPage = fanPage;
        this.name = name;
        this.fanpageId = fanpageId;
        this.managerName = managerName;
        this.phone = phone;
        this.images = images;
        Id = id;
    }

    public String getFanPage() {
        return fanPage;
    }

    public void setFanPage(String fanPage) {
        this.fanPage = fanPage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFanpageId() {
        return fanpageId;
    }

    public void setFanpageId(String fanpageId) {
        this.fanpageId = fanpageId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    @Override
    public String toString() {
        return "\nSalon{" +
                "fanPage='" + fanPage + '\'' +
                ", name='" + name + '\'' +
                ", fanpageId='" + fanpageId + '\'' +
                ", managerName='" + managerName + '\'' +
                ", phone='" + phone + '\'' +
                ", images=" + images +
                ", Id=" + Id +
                '}';
    }
}
