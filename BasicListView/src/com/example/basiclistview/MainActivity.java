package com.example.basiclistview;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ListView lv= (ListView)findViewById(R.id.ListView01);
        
        String[] data ={
        		"Android Lesson 1",
        		"Android Lesson 2",
        		"Android Lesson 3",
        		"Android Lesson 4",
        		"Android Lesson 5",
        		"Android Lesson 6",
        		"Android Lesson 7",
        		"Android Lesson 8",
        		"Android Lesson 9"
        };
        
        lv.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data));
        
        lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getApplicationContext(),
						"The item you clicked is: "+((TextView) view).getText(),
						Toast.LENGTH_SHORT).show();
			}    			
        });
        
        lv.setOnItemLongClickListener(new OnItemLongClickListener(){

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getApplicationContext(),
						"The item you long clicked is: "+((TextView) view).getText(),
						Toast.LENGTH_SHORT).show();
				return false;
			}
        });
        
    }
    
}
