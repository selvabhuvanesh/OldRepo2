package com.qladder.pinefruit;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Date;
import java.util.Calendar;

public class ScheduleActivity<TimePickerFragment> extends AppCompatActivity {

    Button fromTimebtn;
    Button toTimebtn;
    Button savebtn;
    Button publishbtn;
    CalendarView sdate;
    Date selectedDate;
    static int fromHour;
    static int toHour;
    static int frommin;
    static int tomin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        fromTimebtn =(Button) findViewById(R.id.fromtime);
        toTimebtn = (Button) findViewById(R.id.totime);
        savebtn = (Button) findViewById(R.id.save);
        publishbtn = (Button) findViewById(R.id.publish);
        sdate = (CalendarView) findViewById(R.id.date);

        //Code while selecting From time to go hear


        fromTimebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //Dialog fromDialog = new Dialog(ScheduleActivity.this);




            }
        });


        //Code while selecting to Time should go here
        toTimebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showTimePickerDialog(view);
            }
        });


        //Set the date picked from the calendar


        //method for onclick of Save button
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            Toast.makeText(ScheduleActivity.this,"Saved Successfully",Toast.LENGTH_LONG).show();


            }
        });

        //method for onclick of publish button
        publishbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent mainIntent = getIntent();
                Intent confirmIntent = new Intent(ScheduleActivity.this,RegitserConfirm.class);
                confirmIntent.putExtra("service",mainIntent.getStringExtra("service"));
                confirmIntent.putExtra("facility",mainIntent.getStringExtra("facility"));
                confirmIntent.putExtra("provider",mainIntent.getStringExtra("provider"));
                confirmIntent.putExtra("fromtime",fromTimebtn.getText().toString());
                confirmIntent.putExtra("totime",toTimebtn.getText().toString());
                confirmIntent.putExtra("sdate",selectedDate);
                startActivity(confirmIntent);
                }

        });


        sdate.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                //Toast.makeText(ScheduleActivity.this,"Day is :"+i2,Toast.LENGTH_LONG).show();
                //Toast.makeText(ScheduleActivity.this,"Day is :"+sdate.getWeekDayTextAppearance(),Toast.LENGTH_LONG).show();
                //selectedDate = new Date(i,i1,i2);


            }


        });



    }



    @Override
    protected void onResume() {
        super.onResume();
        String facility;
        String provider;
        String service;
        Intent homeIntent = getIntent();
        service = homeIntent.getStringExtra("service");
        provider = homeIntent.getStringExtra("provider");
        facility = homeIntent.getStringExtra("facility");

        Log.d("Resume Called ***** ",service);
        Toast.makeText(this,"Service Name : "+ service+"\n Provider : "+provider+"\n Facility : "+facility,Toast.LENGTH_LONG).show();


    }





}


