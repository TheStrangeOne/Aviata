package com.example.aslan.aviata;

/**
 * Created by Yerda on 2-Apr-17.
 */
public class Data {
    String fs, name, city, countryCode, countryName, localTime;
    public Data(String nFs, String nName, String nCity, String nCountryCode, String nCountryName, String nLocalTime){
        fs=nFs;
        name=nName;
        city=nCity;
        countryCode=nCountryCode;
        countryName=nCountryName;
        localTime=nLocalTime;
    }
    public void setFs(String value){
        fs=value;
    }
    public void setName(String value){
        name=value;
    }
    public void setCity(String value){
        city=value;
    }
    public void setCountryCode(String value){
        countryCode=value;
    }
    public void setCountryName(String value){
        countryName=value;
    }
    public void setLocalTime(String value){
        localTime=value;
    }

    public String getFs(){
        return fs;
    }
    public String getName(){
        return name;
    }
    public String getCity(){
        return city;
    }
    public String getCountryCode(){
        return countryCode;
    }
    public String getCountryName(){
        return countryName;
    }
    public String getLocalTime(){
        return localTime;
    }
}
