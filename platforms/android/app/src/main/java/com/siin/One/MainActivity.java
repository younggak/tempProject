/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package com.siin.One;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import org.apache.cordova.*;

import java.util.ArrayList;

public class MainActivity extends CordovaActivity
{
    String shared = "file";
    public static Context contextOfApplication;
    public static Context getContextOfApplication(){

        return contextOfApplication;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        contextOfApplication = getApplicationContext();

        String[] permissions = {Manifest.permission.RECEIVE_SMS};
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS);
        if (permissionCheck == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, permissions, 1);
        }

        String[] permissionsRead = {Manifest.permission.READ_CONTACTS};
        int permissionCheckRead = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);
        if (permissionCheckRead == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, permissionsRead, 1);
        }


        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI , null ,null, null, null);

        int count = 0;

        if(cur.getCount()>0){
            String line = "";
            while(cur.moveToNext()){
                PhoneBook pb = new PhoneBook();
                int id = cur.getInt(cur.getColumnIndex(ContactsContract.Contacts._ID));
//                line = String.format("%4d",id);
                pb.setId(String.format("%4d",id));

                String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
//                line += " " + name;
                pb.setName(name);

                if(("1").equals(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)))) {
                    Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?", new String[]{String.valueOf(id)}, null);
                    int i = 0;
                    int pCount = pCur.getCount();
                    String[] phoneNum = new String[pCount];
                    String[] phoneType = new String[pCount];

                    while (pCur.moveToNext()) {
                        phoneNum[i] = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//                        line += " " + phoneNum[i];
                        pb.setTel(phoneNum[i].replace("-",""));
                        phoneType[i] = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
                        i++;
                    }
                }
                DataCenter.getInstance().getPhoneBookList().add(pb);
                //numbook.add(line);
                line ="";
            }
        }

        int listSize = PreferenceManager.getInt(this,"size");
        if(listSize>0){

            String numberList = PreferenceManager.getString(this,"number");
            String textList = PreferenceManager.getString(this,"text");
            String[] nl = numberList.split("#");
            String[] tl = textList.split("#");

            int i = 0;
            int pblistSize = DataCenter.getInstance().getPhoneBookList().size();
            while(i<listSize){
                SMSBook sb = new SMSBook();
                String alterNumber = nl[i];
                int k = 0;
                while(k<pblistSize){
                    PhoneBook pb = DataCenter.getInstance().getPhoneBookList().get(k);
                    if(!(pb.getTel() == null)){
                        if(pb.getTel().equals(nl[i])){
                            alterNumber = pb.getName();
                            System.out.println(pb.getTel());
                        }
                    }
                    k++;
                }
                k = 0;

                sb.setNumber(alterNumber);
                sb.setText(tl[i]);
                DataCenter.getInstance().getSmsBookList().add(sb);
                i++;
            }
        }







        // enable Cordova apps to be started in the background
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.getBoolean("cdvStartInBackground", false)) {
            moveTaskToBack(true);
        }

        // Set by <content src="index.html" /> in config.xml
        loadUrl(launchUrl);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        String number = "";
        String text = "";
        ArrayList<SMSBook> list = new ArrayList<SMSBook>();
        list = DataCenter.getInstance().getSmsBookList();
        int i = 0;
        int max = list.size();
        while(i<max){
            number = number + list.get(i).getNumber() + "#";
            text = text + list.get(i).getText() + "#";
            i++;
        }

        PreferenceManager.setString(this,"number", number);
        PreferenceManager.setString(this,"text", text);
        PreferenceManager.setInt(this,"size", list.size());
        list.clear();
    }

//    private long time= 0;
//
//    @Override
//
//    public void onBackPressed(){
//
//        if(System.currentTimeMillis() - time >= 2000){
//            time=System.currentTimeMillis();
//            Toast.makeText(getApplicationContext(),"한번더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
//        } else if(System.currentTimeMillis() - time < 2000 ){
//            finish();
//        }
//    }
}
