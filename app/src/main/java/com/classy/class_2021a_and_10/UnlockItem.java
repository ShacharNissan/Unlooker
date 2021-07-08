package com.classy.class_2021a_and_10;

public class UnlockItem {
    private int image;
    private String date;

    public UnlockItem(int image, String date){
        this.image = image;
        this.date = date;
    }

    public UnlockItem(String date){
        this.date = date;
    }

    public int getImage() {
        return image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
