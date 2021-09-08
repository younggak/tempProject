package com.siin.One;

import java.util.ArrayList;

public class DataCenter {
  ArrayList<PhoneBook> phoneBookList = new ArrayList<PhoneBook>();
  ArrayList<SMSBook> smsBookList = new ArrayList<SMSBook>();


  private static DataCenter instance = new DataCenter();
  //private construct
  private DataCenter() {
  }

  public static DataCenter getInstance() {
    return instance;
  }

  public ArrayList<PhoneBook> getPhoneBookList() {
    return phoneBookList;
  }

  public void setPhoneBookList(ArrayList<PhoneBook> list) {
    this.phoneBookList = list;
  }

  public ArrayList<SMSBook> getSmsBookList() {
    return smsBookList;
  }

  public void setSmsBookList(ArrayList<SMSBook> smsBookList) {
    this.smsBookList = smsBookList;
  }
}
