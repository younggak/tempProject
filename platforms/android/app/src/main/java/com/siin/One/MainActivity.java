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
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import org.apache.cordova.*;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends CordovaActivity
{
    String shared = "file";
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);


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

        if(!Python.isStarted())
            Python.start(new AndroidPlatform(this));

        Python py = Python.getInstance();

        PyObject pyo = py.getModule("init");
        pyo.callAttr("main");

//        SharedPreferences sharedPreferences = getSharedPreferences(shared,0);
//        int listSize = sharedPreferences.getInt("size",0);
//        if(listSize>0){
//            int i = 0;
//            String numberList = sharedPreferences.getString("number","");
//            String textList = sharedPreferences.getString("text","");
//            String[] nl = numberList.split("#");
//            String[] tl = textList.split("#");
//
//            while(i<listSize){
//                SMSBook sb = new SMSBook();
//                sb.setNumber(nl[i]);
//                sb.setText(tl[i]);
//                DataCenter.getInstance().getSmsBookList().add(sb);
//                i++;
//            }
//        }
//        String numberList = sharedPreferences.getString("number","");
//        String textList = sharedPreferences.getString("text","");





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
                        pb.setTel(phoneNum[i]);
                        phoneType[i] = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
                        i++;
                    }
                }
                DataCenter.getInstance().getPhoneBookList().add(pb);
                //numbook.add(line);
                line ="";
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

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//
//        SharedPreferences sharedPreferences = getSharedPreferences(shared,0);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//
//        String number = "";
//        String text = "";
//        ArrayList<SMSBook> list = new ArrayList<SMSBook>();
//        list = DataCenter.getInstance().getSmsBookList();
//        int i = 0;
//        int max = list.size();
//        while(i<max){
//            number = number + list.get(i).getNumber() + "#";
//            text = text + list.get(i).getText() + "#";
//            i++;
//        }
//        editor.putString("number", number);
//        editor.putString("text", text);
//        editor.putInt("size", list.size());
//        editor.commit();
//    }
}
