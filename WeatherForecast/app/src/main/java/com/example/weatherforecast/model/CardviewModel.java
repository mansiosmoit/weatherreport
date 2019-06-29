package com.example.weatherforecast.model;

import java.io.Serializable;

public  class CardviewModel implements Serializable {
    String time;
    String wind;
    String pressure;
    String humidity;
    //    String pac;
    String tid;
    String Id;
//    String tenderarea;
//    String keyword_name;

    public String getWind() {
        return wind;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPressure() {
        return pressure;
    }

    public String getHumidity() {
        return humidity;
    }


//    public boolean isSelected;
//    String Id;
//    String View_date;
//    String View_on;

//    public String getView_date() {
//        return View_date;
//    }
//
//    public void setView_date(String view_date) {
//        View_date = view_date;
//    }
//
//    public String getView_on() {
//        return View_on;
//    }
//
//    public void setView_on(String view_on) {
//        View_on = view_on;
//    }
//


    public CardviewModel() {

    }

//
//    public String getWork() {
//
//        return work;
//    }
//
//    public String getTenderarea() {
//        return tenderarea;
//    }
//
//    public void setTenderarea(String tenderarea) {
//        this.tenderarea = tenderarea;
//    }
//
//    public void setWork(String work) {
//
//        this.work = work;
//    }
//
//    public String getKeyword_name() {
//        return keyword_name;
//    }
//
//    public void setKeyword_name(String keyword_name) {
//        this.keyword_name = keyword_name;
//    }
//
//    public String getStatename() {
//        return statename;
//    }
//
//    public void setStatename(String statename) {
//        this.statename = statename;
//    }
//
//    public String getDate() {
//
//        return date;
//    }
//
//    public void setDate(String date) {
//
//        this.date = date;
//    }
//
//    public String getPac() {
//
//        return pac;
//    }
//
//    public void setPac(String pac) {
//
//        this.pac = pac;
//    }
//
//    public String getId() {
//        return Id;
//    }


    //    public String getTid() {
//        return tid;
//    }
//
//    public void setTid(String tid) {
//
//        this.tid = tid;
//    }
    @Override
    public String toString() {
        return wind;
    }
//
//
//    public void setSelected(boolean selection){
//        this.isSelected = selection;
//    }
//
//    public boolean isSelected(){
//        return isSelected;
//    }
//
//
//    public boolean getSelected() {
//        return isSelected;
//    }
}