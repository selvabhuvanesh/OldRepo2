package com.qladder.pinefruit;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;

public class SessionScheduleActivity<TimePickerFragment> extends AppCompatActivity {

    Button fromTimebtn;
    Button toTimebtn;
    Button savebtn;
    Button publishbtn;
    CalendarView sdate;
    String selectedDate;
    static int fromHour;
    static int toHour;
    static int frommin;
    static int tomin;
    String sessionInfoconfirmMessage;
    String providerID;
    String mserviceName;
    String mproviderName;
    String sessionStatus;
    String mfromtime;
    String mtotime;
    String mdate;
    String sessionID;
    protected Intent confirmIntent;
    FirebaseDatabase database ;
    DatabaseReference myRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        fromTimebtn = (Button) findViewById(R.id.fromtime);
        toTimebtn = (Button) findViewById(R.id.totime);
        savebtn = (Button) findViewById(R.id.save);
        publishbtn = (Button) findViewById(R.id.publish);
        sdate = (CalendarView) findViewById(R.id.date);
        confirmIntent = new Intent(SessionScheduleActivity.this, ProviderRegitserConfirmActivity.class);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Session");

        savebtn.setEnabled(false);
        publishbtn.setEnabled(false);

        //Setting current date in the CALEDAR to avoid past date selection
        Long currentDate = Calendar.getInstance().getTime().getTime();
        sdate.setMinDate(currentDate);

        //Code while selecting From time
        fromTimebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Dialog fromDialog = new Dialog(ScheduleActivity.this);
                //showTimePickerDialog(view);
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        SessionScheduleActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                                fromHour = i;
                                frommin = i1;
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(0, 0, 0, fromHour, frommin);
                                fromTimebtn.setText(DateFormat.format("hh:mm aa", calendar));
                            }
                        }, 12, 0, false
                );
                timePickerDialog.updateTime(fromHour, frommin);
                timePickerDialog.show();

            }
        });


        //Code while selecting to Time should go here
        toTimebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showTimePickerDialog(view);
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        SessionScheduleActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                                toHour = i;
                                tomin = i1;
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(0, 0, 0, toHour, tomin);
                                toTimebtn.setText(DateFormat.format("hh:mm aa", calendar));
                            }
                        }, 12, 0, false
                );
                timePickerDialog.updateTime(toHour, tomin);
                timePickerDialog.show();

                savebtn.setEnabled(true);
                publishbtn.setEnabled(true);
            }
        });


        //Set the date picked from the calendar


        //method for onclick of Save button
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isSaveSuccessful())
                Toast.makeText(SessionScheduleActivity.this, "Saved Successfully", Toast.LENGTH_LONG).show();
            }
        });

        //method for onclick of publish or share  button. This method changes the status to "Booking" and pass info to next intent
        publishbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isSaveSuccessful())
                {
                AlertDialog confirmDialog;
                confirmDialog = new AlertDialog.Builder(SessionScheduleActivity.this)
                        .setTitle("Session Review")
                        .setMessage(sessionInfoconfirmMessage)
                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                sessionStatus = "Booking";
                                SessionInfo sessionInfo = new SessionInfo(
                                        sessionID,
                                        mfromtime,
                                        mtotime,
                                        mdate,
                                        sessionStatus);
                                myRef.child(sessionID).setValue(sessionInfo);


                                confirmIntent.putExtra("serviceName", getIntent().getStringExtra("serviceName"));
                                confirmIntent.putExtra("providerName", getIntent().getStringExtra("providerName"));
                                confirmIntent.putExtra("fromTime",mfromtime);
                                confirmIntent.putExtra("toTime",mtotime );
                                confirmIntent.putExtra("date",mdate );
                                startActivity(confirmIntent);
                            }
                        })
                        .setNegativeButton("Not Sure", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();



                /*confirmIntent.putExtra("Latitude ", mainIntent.getStringExtra("Latitude"));
                confirmIntent.putExtra("Longitude ", mainIntent.getStringExtra("Longitude"));
                confirmIntent.putExtra("AddressLine ", mainIntent.getStringExtra("AddressLine"));
                confirmIntent.putExtra("Location ", mainIntent.getStringExtra("Location"));*/
            }}

        });





        sdate.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {

                Date mselected = new Date(i,i1,i2);
                selectedDate = mselected+"";

            }


        });


    }

    private boolean isSaveSuccessful() {
        mfromtime = fromTimebtn.getText().toString();
        mtotime = toTimebtn.getText().toString();
        mdate = selectedDate;
        sessionStatus = "Saved";

        if(!(mfromtime ==null || mtotime == null || mdate==null)) {
            sessionInfoconfirmMessage = "Service : "+mserviceName
                    +"\nProvider Name : " + mproviderName
                    +"\nSession Date : " + mdate
                    +"\nStartTime : "+ mfromtime+"\nEnd Time : " + mtotime;
            if(sessionID==null)
            {
                sessionID = myRef.push().getKey();
                SessionInfo sessionInfo = new SessionInfo(
                        sessionID,
                        mfromtime,
                        mtotime,
                        mdate,
                        sessionStatus);
                myRef.child(sessionID).setValue(sessionInfo);
            }
            else {
                SessionInfo sessionInfo = new SessionInfo(
                        sessionID,
                        mfromtime,
                        mtotime,
                        mdate,
                        sessionStatus);
                myRef.child(sessionID).setValue(sessionInfo);
            }
            return true;
        }
        else
        {
            Toast.makeText(this,"From Time \nTo Time and \nDate are required",Toast.LENGTH_LONG).show();
            return false;
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this,"OnResumed called from SessionSchedule Activity",Toast.LENGTH_LONG).show();

    }

}