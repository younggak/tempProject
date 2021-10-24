package com.siin.One;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;

public class DataCenter {
  ArrayList<PhoneBook> phoneBookList = new ArrayList<PhoneBook>();
  ArrayList<SMSBook> smsBookList = new ArrayList<SMSBook>();
  ArrayList<SMSInPhoneBook> smsInPhoneBooksList = new ArrayList<SMSInPhoneBook>();
  private int totalSMSNumber;
  private int SMSNotInPhoneBookNumber;


  private static DataCenter instance = new DataCenter();
  //private construct
  private DataCenter() {
  }

  public ArrayList<SMSInPhoneBook> getSmsInPhoneBooksList() {
    return smsInPhoneBooksList;
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

  public void refreshSMSDataBase(Context context){
    Uri allMessage = Uri.parse("content://sms");
    ContentResolver cr = context.getContentResolver();
    Cursor c = cr.query(allMessage,
            new String[]{"_id", "thread_id", "address", "person", "date", "body"},
            null, null,
            "date DESC");

    while (c.moveToNext()) {
      SMSInPhoneBook msg = new SMSInPhoneBook();

      long messageId = c.getLong(0);
      msg.setMessageId(String.valueOf(messageId));

      long threadId = c.getLong(1);
      msg.setThreadId(String.valueOf(threadId));

      String address = c.getString(2);
      msg.setAddress(address);

      long contactId = c.getLong(3);
      msg.setContactId(String.valueOf(contactId));

      String contactId_string = String.valueOf(contactId);
      msg.setContactId_string(contactId_string);

      long timestamp = c.getLong(4);
      msg.setTimestamp(String.valueOf(timestamp));

      String body = c.getString(5);
      msg.setBody(body);

      smsInPhoneBooksList.add(msg);
    }
  }
}
