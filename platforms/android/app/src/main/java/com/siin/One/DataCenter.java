package com.siin.One;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import com.siin.One.HashMapDetail.HashMapDetail_PhoneBook;
import com.siin.One.HashMapDetail.HashMapDetail_SMS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataCenter {
  ArrayList<SMSBook> smsBookList = new ArrayList<SMSBook>();

  HashMap<String, HashMapDetail_PhoneBook> phoneBookHashMap = new HashMap<>();
  HashMap<String, ArrayList<HashMapDetail_SMS> > smsHashMap = new HashMap<>();

  HashMap<String, ArrayList<HashMapDetail_SMS>> bookedSmsHashMap = new HashMap<>();
  HashMap<String, ArrayList<HashMapDetail_SMS>> notBookedSmsHashMap = new HashMap<>();

  private int totalSMSNumber;
  private int SMSInPhoneBookNumber;
  private int SMSNotInPhoneBookNumber;

  public static DataCenter getInstance() {
    return instance;
  }

  private static DataCenter instance = new DataCenter();
  //private construct
  private DataCenter() {
  }

  public HashMap<String, ArrayList<HashMapDetail_SMS>> getSmsHashMap() {
    return smsHashMap;
  }

  public HashMap<String, HashMapDetail_PhoneBook> getPhoneBookHashMap() {
    return phoneBookHashMap;
  }

  public HashMap<String, ArrayList<HashMapDetail_SMS>> getBookedSmsHashMap() {
    return bookedSmsHashMap;
  }

  public HashMap<String, ArrayList<HashMapDetail_SMS>> getNotBookedSmsHashMap() {
    return notBookedSmsHashMap;
  }

  public int getTotalSMSNumber() {
    return totalSMSNumber;
  }

  public int getSMSInPhoneBookNumber() {
    return SMSInPhoneBookNumber;
  }

  public int getSMSNotInPhoneBookNumber() {
    return SMSNotInPhoneBookNumber;
  }

  public ArrayList<SMSBook> getSmsBookList() {
    return smsBookList;
  }

  public void setSmsBookList(ArrayList<SMSBook> smsBookList) {
    this.smsBookList = smsBookList;
  }

  public void refreshSMSDataBase(Context context){
    smsHashMap.clear();
    totalSMSNumber = 0;
    Uri allMessage = Uri.parse("content://sms");
    ContentResolver cr = context.getContentResolver();
    Cursor c = cr.query(allMessage,
            new String[]{"_id", "thread_id", "address", "person", "date", "body"},
            null, null,
            "date DESC");

    while (c.moveToNext()) {
      if(c.getString(2).equals("Information")){
        continue;
      }
      else if(smsHashMap.containsKey(c.getString(2)) ){
        HashMapDetail_SMS hmds = new HashMapDetail_SMS();

        long messageId = c.getLong(0);
        hmds.setMessageId(String.valueOf(messageId));

        long threadId = c.getLong(1);
        hmds.setThreadId(String.valueOf(threadId));

        String address = c.getString(2);
        hmds.setAddress(address);

        long contactId = c.getLong(3);
        hmds.setContactId(String.valueOf(contactId));

        String contactId_string = String.valueOf(contactId);
        hmds.setContactId_string(contactId_string);

        long timestamp = c.getLong(4);
        hmds.setTimestamp(String.valueOf(timestamp));

        String body = c.getString(5);
        hmds.setBody(body);

        smsHashMap.get(c.getString(2)).add(hmds);

      }else{
        HashMapDetail_SMS hmds = new HashMapDetail_SMS();
        ArrayList<HashMapDetail_SMS> list = new ArrayList<>();

        long messageId = c.getLong(0);
        hmds.setMessageId(String.valueOf(messageId));

        long threadId = c.getLong(1);
        hmds.setThreadId(String.valueOf(threadId));

        String address = c.getString(2);
        hmds.setAddress(address);

        long contactId = c.getLong(3);
        hmds.setContactId(String.valueOf(contactId));

        String contactId_string = String.valueOf(contactId);
        hmds.setContactId_string(contactId_string);

        long timestamp = c.getLong(4);
        hmds.setTimestamp(String.valueOf(timestamp));

        String body = c.getString(5);
        hmds.setBody(body);

        list.add(hmds);
        smsHashMap.put(c.getString(2),list);
      }
      totalSMSNumber ++;
    }
  }

  public void refreshPhoneBookDataBase(Context context){
    phoneBookHashMap.clear();
    ContentResolver cr = context.getContentResolver();
    Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI , null ,null, null, null);

    if(cur.getCount()>0){
      while(cur.moveToNext()){
        int i = 0;
        HashMapDetail_PhoneBook pb = new HashMapDetail_PhoneBook();
        int id = cur.getInt(cur.getColumnIndex(ContactsContract.Contacts._ID));
//                line = String.format("%4d",id);
        pb.setId(String.format("%4d",id));

        String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
//                line += " " + name;
        pb.setName(name);

        if(("1").equals(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)))) {
          Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?", new String[]{String.valueOf(id)}, null);

          int pCount = pCur.getCount();
          String[] phoneNum = new String[pCount];
          String[] phoneType = new String[pCount];

          while (pCur.moveToNext()) {
            phoneNum[i] = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//                        line += " " + phoneNum[i];
            pb.setTel(phoneNum[i].replace("-", ""));
            phoneType[i] = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
            pb.setPhoneType(phoneType[i]);
            i++;
          }
        }
        phoneBookHashMap.put(pb.getTel(), pb);
      }
    }
  }

  public void refreshBookedSmsHashMap(){
    SMSNotInPhoneBookNumber = 0;
    SMSInPhoneBookNumber = 0;
    totalSMSNumber = 0;
    bookedSmsHashMap.clear();
    notBookedSmsHashMap.clear();
    for(Map.Entry<String, ArrayList<HashMapDetail_SMS>> entrySet : smsHashMap.entrySet()){
      if(phoneBookHashMap.containsKey(entrySet.getKey())){
        bookedSmsHashMap.put(entrySet.getKey(), entrySet.getValue());
        SMSInPhoneBookNumber ++;
      }else{
        notBookedSmsHashMap.put(entrySet.getKey(), entrySet.getValue());
        SMSNotInPhoneBookNumber ++;
      }
      totalSMSNumber ++;
    }
  }
}
