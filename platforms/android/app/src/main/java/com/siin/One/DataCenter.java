package com.siin.One;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.format.DateFormat;
import android.widget.Toast;

import com.siin.One.HashMapDetail.HashMapDetail_PhoneBook;
import com.siin.One.HashMapDetail.HashMapDetail_SMS;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DataCenter {
  ArrayList<SMSBook> smsBookList = new ArrayList<SMSBook>();
  HashMap<String, HashMapDetail_PhoneBook> phoneBookHashMap = new HashMap<>();
  HashMap<String, ArrayList<HashMapDetail_SMS> > smsHashMap = new HashMap<>();
  HashMap<String, ArrayList<HashMapDetail_SMS>> bookedSmsHashMap = new HashMap<>();
  HashMap<String, ArrayList<HashMapDetail_SMS>> notBookedSmsHashMap = new HashMap<>();
  HashMap<String, ArrayList<HashMapDetail_SMS>> URLSmsHashMap = new HashMap<>();
  HashMap<String, ArrayList<HashMapDetail_SMS>> webSmsHashMap = new HashMap<>();
  HashMap<String, ArrayList<HashMapDetail_SMS>> zeroSevenSmsHashMap = new HashMap<>();
  HashMap<String, ArrayList<HashMapDetail_SMS>> adSmsHashMap = new HashMap<>();
  HashMap<String, ArrayList<HashMapDetail_SMS>> overseaSmsHashMap = new HashMap<>();
  float totalScore = 0;
  float tempSingleScore = 0;
  private int totalSMSNumber=0;
  private float singleFullScore = 100;

  public static DataCenter getInstance() {
    return instance;
  }
  private static DataCenter instance = new DataCenter();
  private DataCenter() {
  }
  public float getTotalScore(){
    return totalScore;
  }
  public HashMap<String, ArrayList<HashMapDetail_SMS>> getURLSmsHashMap() {
    return URLSmsHashMap;
  }
  public HashMap<String, ArrayList<HashMapDetail_SMS>> getWebSmsHashMap() {
    return webSmsHashMap;
  }
  public HashMap<String, ArrayList<HashMapDetail_SMS>> getZeroSevenSmsHashMap() {
    return zeroSevenSmsHashMap;
  }
  public HashMap<String, ArrayList<HashMapDetail_SMS>> getAdSmsHashMap() {
    return adSmsHashMap;
  }
  public HashMap<String, ArrayList<HashMapDetail_SMS>> getOverseaSmsHashMap() {
    return overseaSmsHashMap;
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
  public ArrayList<SMSBook> getSmsBookList() {
    return smsBookList;
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
  public void countSMSDataBase(Context context){
    totalScore = 0;
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
      totalSMSNumber++;
    }
  }
  public void refreshSMSDataBase(Context context){
    smsHashMap.clear();
    totalSMSNumber = 0;
    bookedSmsHashMap.clear();
    notBookedSmsHashMap.clear();
    URLSmsHashMap.clear();
    webSmsHashMap.clear();
    zeroSevenSmsHashMap.clear();
    adSmsHashMap.clear();
    overseaSmsHashMap.clear();

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
    Calendar cal = Calendar.getInstance();

    cal.add(Calendar.DATE,0);
    String today = sdf.format(cal.getTime());

    cal.add(Calendar.DATE, -7);
    String past = sdf.format(cal.getTime());

//    Toast.makeText(context.getApplicationContext(), today, Toast.LENGTH_SHORT).show();
//    Toast.makeText(context.getApplicationContext(), past, Toast.LENGTH_SHORT).show();


    countSMSDataBase(MainActivity.getContextOfApplication());

    Uri allMessage = Uri.parse("content://sms");
    ContentResolver cr = context.getContentResolver();
    Cursor c = cr.query(allMessage,
            new String[]{"_id", "thread_id", "address", "person", "date", "body"},
            null, null,
            "date DESC");
    while (c.moveToNext()) {
      //긴급 문자는 걸러주고
      if(c.getString(2).equals("Information")){
        continue;
      }
      //만약 연락처에 있는 번호에서 온 문자라면,
      if(phoneBookHashMap.containsKey(c.getString(2))){
        //일단 문자 디테일 오브젝트 하나 만들고.
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
        Date tempDay = new Date(timestamp);
        String formattedDate = new SimpleDateFormat("yyyy.MM.dd").format(tempDay);

        hmds.setTimestamp(String.valueOf(formattedDate));

        String body = c.getString(5);
        hmds.setBody(body);

        //####먼저 전체 문자 해쉬맵에 넣어준다####
          //만약 있는 번호에서 온 문자라면
        if(smsHashMap.containsKey(c.getString(2)) ){
          smsHashMap.get(c.getString(2)).add(hmds);
        }
          //새로운 번호에서 온 문자라면
        else {
          ArrayList<HashMapDetail_SMS> list = new ArrayList<>();
          list.add(hmds);
          smsHashMap.put(c.getString(2),list);
        }


        //####그 다음 연락처에 있는 문자 해쉬맵에 넣어준다.####
        //만약 있는 번호에서 온 문자라면
        if(bookedSmsHashMap.containsKey(c.getString(2)) ){
          bookedSmsHashMap.get(c.getString(2)).add(hmds);
        }
        //새로운 번호에서 온 문자라면
        else {
          ArrayList<HashMapDetail_SMS> list = new ArrayList<>();
          list.add(hmds);
          bookedSmsHashMap.put(c.getString(2),list);
        }

        //점수는 무조건 만점 더해준다.
        totalScore = totalScore + singleFullScore;
      }

      //만약 연락처에 없는 번호에서 온 문자라면, (전체 문자에 더해주고, 연락처에 없는 문자에 더해주며, 각종분류에 더해준다)
      else{
        tempSingleScore = singleFullScore;

        //일단 문자 디테일 오브젝트 하나 만들고.
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
        Date tempDay = new Date(timestamp);
        String formattedDate = new SimpleDateFormat("yyyy.MM.dd").format(tempDay);
        hmds.setTimestamp(String.valueOf(formattedDate));

        String body = c.getString(5);
        hmds.setBody(body);

        //####그 다음 전체 문자에 추가해준다.####
        //만약 있는 번호에서 온 문자라면
        if(smsHashMap.containsKey(c.getString(2)) ){
          smsHashMap.get(c.getString(2)).add(hmds);
        }
        //새로운 번호에서 온 문자라면
        else {
          ArrayList<HashMapDetail_SMS> list = new ArrayList<>();
          list.add(hmds);
          smsHashMap.put(c.getString(2),list);
        }

        //####그 다음 연락처에 없는 문자 해쉬맵에 추가해준다.####
        //만약 있는 번호에서 온 문자라면
        if(notBookedSmsHashMap.containsKey(c.getString(2)) ){
          notBookedSmsHashMap.get(c.getString(2)).add(hmds);
        }
        //새로운 번호에서 온 문자라면
        else {
          ArrayList<HashMapDetail_SMS> list = new ArrayList<>();
          list.add(hmds);
          notBookedSmsHashMap.put(c.getString(2),list);
        }

        //####그 다음 각종 특수 문자 계열에 넣어준다.####
        //Url
        if(hmds.getBody().contains("www") || hmds.getBody().contains("http") ){
          tempSingleScore = tempSingleScore * 5/10;
          if(URLSmsHashMap.containsKey(c.getString(2))){
            URLSmsHashMap.get(c.getString(2)).add(hmds);
          }else{
            ArrayList<HashMapDetail_SMS> list = new ArrayList<>();
            list.add(hmds);
            URLSmsHashMap.put(c.getString(2),list);
          }
        }
        //070 번호 문자
        if(hmds.getAddress().contains("070")){
          if(zeroSevenSmsHashMap.containsKey(c.getString(2))){
            zeroSevenSmsHashMap.get(c.getString(2)).add(hmds);
          }else{
            ArrayList<HashMapDetail_SMS> list = new ArrayList<>();
            list.add(hmds);
            zeroSevenSmsHashMap.put(c.getString(2),list);
          }
        }
        //[Web 발신]
        if(hmds.getBody().contains("[Web발신]")){
          tempSingleScore = tempSingleScore * 9/10;
          if(webSmsHashMap.containsKey(c.getString(2))){
            webSmsHashMap.get(c.getString(2)).add(hmds);
          }else {
            ArrayList<HashMapDetail_SMS> list = new ArrayList<>();
            list.add(hmds);
            webSmsHashMap.put(c.getString(2), list);
          }
        }
        //(광고)
        if(hmds.getBody().contains("(광고)")){
          tempSingleScore = 0;
          if(adSmsHashMap.containsKey(c.getString(2))){
            adSmsHashMap.get(c.getString(2)).add(hmds);
          }else {
            ArrayList<HashMapDetail_SMS> list = new ArrayList<>();
            list.add(hmds);
            adSmsHashMap.put(c.getString(2), list);
          }
        }
        //[국외발신]
        if(hmds.getBody().contains("[국외발신]")){
          tempSingleScore = tempSingleScore*5/10;
          if(overseaSmsHashMap.containsKey(c.getString(2))){
            overseaSmsHashMap.get(c.getString(2)).add(hmds);
          }else {
            ArrayList<HashMapDetail_SMS> list = new ArrayList<>();
            list.add(hmds);
            overseaSmsHashMap.put(c.getString(2), list);
          }
        }
      }
      totalSMSNumber++;
      totalScore = totalScore+tempSingleScore;

    }
    totalScore = totalScore/totalSMSNumber;
  }

}
