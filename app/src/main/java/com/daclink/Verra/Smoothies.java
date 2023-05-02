package com.daclink.Verra;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.daclink.Verra.DB.AppDatabase;

@Entity(tableName =  AppDatabase.SMOOTHIES_TABLE)
public class Smoothies {

    @PrimaryKey(autoGenerate = true)
    private int smoothieID;

    private String name;
    private String desc;
    private String price;

    public Smoothies(String name, String desc, String price) {
        this.name = name;
        this.desc = desc;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Smoothies{" +
                "smoothieID=" + smoothieID +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", price=" + price +
                '}';
    }

    public int getSmoothieID() {
        return smoothieID;
    }

    public void setSmoothieID(int smoothieID) {
        this.smoothieID = smoothieID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
