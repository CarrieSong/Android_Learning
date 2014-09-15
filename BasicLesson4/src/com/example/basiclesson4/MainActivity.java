package com.example.basiclesson4;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	String tag ="[MainActivityLife]";
	
	/** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(tag, "onCreate");
    }

    @Override
    public void onStart(){
    	super.onStart();
    	Log.i(tag, "onStart");
    }

    @Override
    public void onPause(){
    	super.onPause();
    	Log.i(tag, "onPause");
    }
    
    @Override
    public void onResume(){
    	super.onResume();
    	Log.i(tag, "onResume");
    }
    
    @Override
    public void onStop(){
    	super.onStop();
    	Log.i(tag, "onStop");
    }
    
    @Override
    public void onDestroy(){
    	super.onDestroy();
    	Log.i(tag, "onDestroy");
    }
    
}
