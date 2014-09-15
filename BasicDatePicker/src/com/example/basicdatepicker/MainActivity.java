package com.example.basicdatepicker;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView dateDisplay;
	private Button pickDate;
	private int mYear;
    private int mMonth;
    private int mDay;
    
    //dialog id
    static final int DATE_DIALOG_ID = 0;
    
    private OnDateSetListener dsl = new DatePickerDialog.OnDateSetListener(){
    	
    	@Override
    	public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
    		mYear = year;
    		mMonth = monthOfYear;
    		mDay = dayOfMonth;
    		dateDisplay.setText(mYear+"-"+(mMonth+1)+"-"+mDay);
    	}
    };
    
    @Override
    protected Dialog onCreateDialog(int id){
    	
    	switch(id){
    	case DATE_DIALOG_ID:
    		return new DatePickerDialog(this, dsl, mYear, mMonth, mDay);
    	}
    	return null;
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        
        dateDisplay= (TextView)findViewById(R.id.dateDisplay);
        pickDate = (Button)findViewById(R.id.pickDate);
        
        //Initialize the displaying text
        dateDisplay.setText(mYear+"-"+(mMonth+1)+"-"+mDay);
        
        pickDate.setOnClickListener(new View.OnClickListener() {
        	@SuppressWarnings("deprecation")
			public void onClick(View v) {
        		showDialog(DATE_DIALOG_ID);
        	}
        });
        
    }
    
}
