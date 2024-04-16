package com.example.lab2;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "phonesData")
public class Phone {
    @PrimaryKey(autoGenerate = true)
    //@ColumnInfo(name = "id")
    private long id;


    @ColumnInfo(name = "brand_column")
    private String brand;

    @ColumnInfo(name = "model_column")
    private String model;

    @ColumnInfo(name = "android_version_column")
    private String androidVersion;

    @ColumnInfo(name = "website_column")
    private String website;



    public Phone(String brand, String model, String androidVersion, String website){
        this.brand = brand;
        this.model = model;
        this.androidVersion = androidVersion;
        this.website = website;
    }
    public Phone( long id, String brand, String model, String androidVersion,String website){
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.androidVersion = androidVersion;
        this.website = website;
    }




    public void setId(long id){
        this.id = id;
    }

    public long getId(){
        return id;
    }

    public String getBrand(){
        return brand;
    }

    public void setBrand(){
        this.brand = brand;
    }

    public String getModel(){
        return model;
    }

    public void setModel(){
        this.model = model;
    }

    public String getAndroidVersion(){
        return androidVersion;
    }

    public void setAndroidVersion(){
        this.androidVersion = androidVersion;
    }

    public String getWebsite(){
        return website;
    }

    public void setWebsite(){
        this.website = website;
    }


    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setAndroidVersion(String androidVersion) {
        this.androidVersion = androidVersion;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Phone(){

    }


}
