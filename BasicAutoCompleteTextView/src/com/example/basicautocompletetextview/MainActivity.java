package com.example.basicautocompletetextview;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        AutoCompleteTextView atv = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        
        String[] province = getResources().getStringArray(R.array.province);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, province);
        
        atv.setAdapter(adapter);
    }
    
}
