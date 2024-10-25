package com.sm.finalproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
public class AutoComplete extends Activity {
    AutoCompleteTextView autocomplete;
    private Button pickDateBtn;
    private Button pickDateBtn1;
    private TextView selectedTimeTV;
    private Button pickTimeBtn;
    boolean isShow;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_complete);
        findViewById(R.id.imgBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        ControlBind();
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Paries,France");
        arrayList.add("PA,United States");
        arrayList.add("Parana,Brazil");
        arrayList.add("Padua,Italy");
        arrayList.add("Pasadena,CA,United States");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, arrayList);
        autocomplete.setThreshold(1);
        autocomplete.setAdapter(adapter);
        autocomplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Position clicked: " + (String) parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }
        });
        pickDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);
                DatePickerDialog futurDateDilog = new DatePickerDialog(AutoComplete.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String futurDate = dayOfMonth + "-" + month + "-" + year;
                        pickDateBtn.setText(futurDate);
                    }
                }, year, month, day);
                futurDateDilog.getDatePicker().setMaxDate(System.currentTimeMillis());
                futurDateDilog.show();
            }
        });
        pickDateBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);
                DatePickerDialog futurDateDilog = new DatePickerDialog(AutoComplete.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String futurDate = dayOfMonth + "-" + month + "-" + year;
                        pickDateBtn1.setText(futurDate);
                    }
                }, year, month, day);
                futurDateDilog.getDatePicker().setMaxDate(System.currentTimeMillis());
                futurDateDilog.show();
            }
        });
        pickTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(AutoComplete.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        selectedTimeTV.setText(hourOfDay + ":" + minute);
                    }
                }, hour, minute, false);
                timePickerDialog.show();
            }
        });
        findViewById(R.id.idBtnPickDate11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pickDateBtn.getText().toString().trim().length() == 0) {
                    Toast.makeText(getApplicationContext(), getString(R.string.Start), Toast.LENGTH_SHORT).show();
                } else if (pickDateBtn1.getText().toString().trim().length() == 0) {
                    Toast.makeText(getApplicationContext(), getString(R.string.End), Toast.LENGTH_SHORT).show();
                } else {
                    boolean b = checkDates(pickDateBtn.getText().toString(), pickDateBtn1.getText().toString());
                    if (b == false) {
                        Toast.makeText(getApplicationContext(), getString(R.string.Date_is_not_match), Toast.LENGTH_SHORT).show();
                    } else if (b == true) {
                        Toast.makeText(AutoComplete.this, R.string.Submit1, Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
    static SimpleDateFormat dfDate = new SimpleDateFormat("dd-MM-yyyy");
    public static boolean checkDates(String StartDate, String StrEndDate) {
        boolean b = false;
        try {
            if (dfDate.parse(StartDate).before(dfDate.parse(StrEndDate))) {
                b = true;//If start date is before end date
            } else if (dfDate.parse(StartDate).equals(dfDate.parse(StrEndDate))) {
                b = true;
            } else {
                b = false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return b;
    }
    @SuppressLint("WrongViewCast")
    private void ControlBind() {
        autocomplete = findViewById(R.id.autoCompleteTextView1);
        pickDateBtn = findViewById(R.id.idBtnPickDate);
        pickDateBtn1 = findViewById(R.id.idBtnPickDate1);
        pickTimeBtn = findViewById(R.id.idBtnPickTime);
        selectedTimeTV = findViewById(R.id.idTVSelectedTime);
    }
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(), Dashboard.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }
}