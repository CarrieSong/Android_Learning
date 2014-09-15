package com.example.logdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LogDemo extends Activity {

	private static final String ACTIVITY_TAG="LogDemo";
	private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
	            Log.v(LogDemo.ACTIVITY_TAG, "This is Verbose.");
	            Log.d(LogDemo.ACTIVITY_TAG, "This is Debug.");
	            Log.i(LogDemo.ACTIVITY_TAG, "This is Information");
	            Log.w(LogDemo.ACTIVITY_TAG, "This is Warnning.");
	            Log.e(LogDemo.ACTIVITY_TAG, "This is Error.");
				
			}
                 
                });
    }
}
