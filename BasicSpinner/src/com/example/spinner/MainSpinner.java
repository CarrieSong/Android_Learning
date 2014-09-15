package com.example.spinner;

import com.example.spinner.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainSpinner extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tiny_dial);
        
        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.colors, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        
        spinner.setAdapter(adapter);
        
        OnItemSelectedListener oisl = new OnItemSelectedListener() {
        	
        	@Override
        	public void onItemSelected(AdapterView<?> parent, View view,
        			int position, long id) {
        		Toast.makeText(MainSpinner.this, "The color selected: " + 
        				parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
        	}
        	
        	@Override
        	public void onNothingSelected(AdapterView<?> parent) {
        		
        	}
        };
        
        spinner.setOnItemSelectedListener(oisl);
    }
}
