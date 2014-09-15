package com.example.basictimepicker;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends Activity {

	private TextView timeDisplay;
	private Button pickTime;
	private int mHour;
    private int mMinute;
    
    //dialog id
    static final int TIME_DIALOG_ID = 0;
    
    private OnTimeSetListener tsl = new TimePickerDialog.OnTimeSetListener() {   			
		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			mHour = hourOfDay;
			mMinute = minute;
			timeDisplay.setText(mHour + ":" + mMinute);
		}
	};
    
	@Override
    protected Dialog onCreateDialog(int id){
    	
    	switch(id){
    	case TIME_DIALOG_ID:
    		return new TimePickerDialog(this, tsl, mHour, mMinute, false);
    	}
    	return null;
    }
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        
        timeDisplay = (TextView)findViewById(R.id.timeDisplay);
        pickTime = (Button)findViewById(R.id.pickTime);
        
        //Initialize the displaying text
        timeDisplay.setText(mHour + ":" + mMinute);
        
        pickTime.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showDialog(TIME_DIALOG_ID);
			}
		});
        
    }
    
}
