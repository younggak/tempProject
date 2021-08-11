package com.siin.One;

public class DataCenter {
    String number;
    String text;


    private static com.siin.One.DataCenter instance = new com.siin.One.DataCenter();
    //private construct
    private DataCenter() {
        number="dirty";
        text="shit";
    }

    public static com.siin.One.DataCenter getInstance() {
        return instance;
    }

    public String getNumber() {
        return number;
    }

    public String getText() {
        return text;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setText(String text) {
        this.text = text;
    }
}
