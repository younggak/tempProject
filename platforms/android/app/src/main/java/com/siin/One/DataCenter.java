package com.siin.One;

import java.util.ArrayList;

public class DataCenter {
  String number;
  String text;
  ArrayList<String> list = new ArrayList<String>();


  private static DataCenter instance = new DataCenter();
  //private construct
  private DataCenter() {
    number="dirty";
    text="shit";
  }

  public ArrayList<String> getList() {
    return list;
  }

  public void setList(ArrayList<String> list) {
    this.list = list;
  }

  public static DataCenter getInstance() {
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
