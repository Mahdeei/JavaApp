package com.example.mysmsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView list ;
    String tutorials[]= {};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        list = (ListView) findViewById(R.id.list_viewID);
        ArrayList<Model_SMS> msgList = getSMS();

        MyCustomAdapter adapter = new MyCustomAdapter(getApplicationContext() , msgList);


//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, msgList);
        list.setAdapter(adapter);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.simple_list_item_1,msgList);






    }

    public ArrayList<Model_SMS> getSMS(){
        Model_SMS objSms ;
        ArrayList<Model_SMS> lstSms = new ArrayList<Model_SMS>();
//        List<String> sms = new ArrayList<String>();
        Uri uriSMSURI = Uri.parse("content://sms/inbox");
        Cursor cur = getContentResolver().query(uriSMSURI, null, null, null, null);

        while (cur.moveToNext()) {

            objSms = new Model_SMS();

            objSms.set_id(cur.getString(cur.getColumnIndexOrThrow("_id")));
            objSms.set_address(cur.getString(cur
                    .getColumnIndexOrThrow("address")));
            objSms.set_msg(cur.getString(cur.getColumnIndexOrThrow("body")));
            objSms.set_readState(cur.getString(cur.getColumnIndex("read")));
            objSms.set_time(cur.getString(cur.getColumnIndexOrThrow("date")));
            if (cur.getString(cur.getColumnIndexOrThrow("type")).contains("1")) {
                objSms.set_folderName("inbox");
            } else {
                objSms.set_folderName("sent");
            }

            lstSms.add(objSms);

        }
        cur.close();
        return lstSms;

    }

//    public void Read_SMS(View view){
//
//        Cursor cursor = getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);
//        String msgData = "";
//        StringBuilder msg = new StringBuilder();;
//        if (cursor.moveToFirst()) { // must check the result to prevent exception
//            do {
//
//                for(int idx=0;idx<cursor.getColumnCount();idx++)
//                {
//                    tutorials[idx] = cursor.getString(idx);
//                    msg.append(cursor.getString(idx));
//
////                    msgData += " " + cursor.getColumnName(idx) + ":" ;
//                }
//                // use msgData
//            } while (cursor.moveToNext());
//        } else {
//            // empty box, no SMS
//        }
//
////        listView.deferNotifyDataSetChanged();
//
//
////        int count =0;
////
////        if(cursor.moveToFirst()){
////            while (cursor.moveToNext()){
////                count +=1;
////                Toast.makeText(getApplicationContext(),cursor.getString(1),Toast.LENGTH_LONG);
////                textView.setText(String.valueOf(count));
////            }
//////            textView.setText("Finish");
////
////        }else{
////            textView.setText("Finish");
////        }
//        cursor.close();
//
//    }

}