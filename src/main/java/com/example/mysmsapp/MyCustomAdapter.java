package com.example.mysmsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class MyCustomAdapter extends BaseAdapter {

    private Context context ;
    private ArrayList<Model_SMS> smsArrayList;
    private static LayoutInflater inflater = null;

    public MyCustomAdapter(Context context, ArrayList<Model_SMS> smsArrayList) {
        this.context = context;
        this.smsArrayList = smsArrayList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return smsArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return smsArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View myView = convertView ;
        if (myView != null)
            return myView;

        myView = inflater.inflate(R.layout.list_sms,null);
        TextView address = myView.findViewById(R.id.addressID);
        TextView message = myView.findViewById(R.id.messageID);
        TextView time = myView.findViewById(R.id.timeID);
        Model_SMS model_sms = smsArrayList.get(position);
        address.setText(model_sms.get_address());
        message.setText(model_sms.get_msg());

        long currentDateTime= Long.parseLong(model_sms.get_time());
        Date currentDate = new Date(currentDateTime);
        time.setText(currentDate.toString());
        return myView ;

    }
}
