package com.qladder.pinefruit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

public class ScheduleActivity extends AppCompatActivity {

    Button fromTimebtn;
    Button toTimebtn;
    Button savebtn;
    Button publishbtn;
    CalendarView sdate;
    Date selectedDate;


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
                showTimePickerDialog(view);


            }
        });


        //Code while selecting to Time should go here
        toTimebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog(view);
            }
        });


        //Set the date picked from the calendar


        //method for onclick of Save button
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               /* Intent mainIntent = getIntent();
                Intent saveIntent = new Intent(ScheduleActivity.this,RegitserConfirm.class);
                saveIntent.putExtra("service",mainIntent.getStringExtra("service"));
                saveIntent.putExtra("facility",mainIntent.getStringExtra("facility"));
                saveIntent.putExtra("provider",mainIntent.getStringExtra("provider"));
                saveIntent.putExtra("fromtime",fromTimebtn.getText().toString());
                saveIntent.putExtra("totime",toTimebtn.getText().toString());
                saveIntent.putExtra("sdate",selectedDate);
                startActivity(saveIntent);*/
               ownerconfirmed("Saved Successfully");

            }
        });

        //method for onclick of publish button
        publishbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(true) {
                   //ownerconfirmed("Confirm to Proceed?");
                   Intent confirmIntent = new Intent(ScheduleActivity.this,RegitserConfirm.class);
                   startActivity(confirmIntent);
                }
                else{
                    // Provide the code on what needs to be done if user cancels confirmation
                }
                }
        });


        sdate.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                //Toast.makeText(ScheduleActivity.this,"Day is :"+i2,Toast.LENGTH_LONG).show();
                //Toast.makeText(ScheduleActivity.this,"Day is :"+sdate.getWeekDayTextAppearance(),Toast.LENGTH_LONG).show();
                selectedDate = new Date(i,i1,i2);


            }


        });



    }

    protected boolean ownerconfirmed(String msg){

        new AlertDialog.Builder(ScheduleActivity.this)
                .setTitle("Pine Confirm")
                .setMessage(msg)
                .setPositiveButton(android.R.string.ok,null)
                //.setNegativeButton(android.R.string.cancel,null)
                .show();
            return true;
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

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user


        }
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }


}


