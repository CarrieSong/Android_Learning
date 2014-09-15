package com.example.basicprogressbar;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        requestWindowFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_main);
        
        //setProgressBarIndeterminateVisibility(true);
        setProgressBarVisibility(true);      
        setProgress(60*100);
        setSecondaryProgress(80*100);
        
        ProgressBar pb;                                                       
        pb = (ProgressBar) findViewById(R.id.ProgressBar04);
        pb.incrementProgressBy(10);                                                 
        //pb.incrementProgressBy(-5);                                                
        pb.incrementSecondaryProgressBy(30);                                 
        //pb.incrementSecondaryProgressBy(-5);                                

        /***********seek bar***************/
        final SeekBar sb = (SeekBar) findViewById(R.id.SeekBar01);
        final TextView tv1 = (TextView) findViewById(R.id.TextView01);

        sb.setMax(100);
		sb.setProgress(30);
		tv1.setText("Current progress: " + sb.getProgress());

		OnSeekBarChangeListener osbcl = new OnSeekBarChangeListener() {

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				tv1.setText("Current progress: " + sb.getProgress());
				//Toast.makeText(getApplicationContext(), "onProgressChanged",
				//		Toast.LENGTH_SHORT).show();
			}

			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				//Toast.makeText(getApplicationContext(), "onStartTrackingTouch",
				//		Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				//Toast.makeText(getApplicationContext(), "onStopTrackingTouch",
				//		Toast.LENGTH_SHORT).show();
			}

		};

		sb.setOnSeekBarChangeListener(osbcl);
		
		/*********************Rating Bar********************/
		
		final RatingBar rb1 = (RatingBar)findViewById(R.id.RatingBar01);
		final RatingBar rb2 = (RatingBar)findViewById(R.id.RatingBar02);
		final RatingBar rb3 = (RatingBar)findViewById(R.id.RatingBar03);
		
		OnRatingBarChangeListener orbcl= new OnRatingBarChangeListener(){
			
			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating, 
					boolean fromUser) { 
				switch(ratingBar.getId()){
				case R.id.RatingBar01:
					rb2.setRating(rb1.getRating());
					rb3.setRating(rb1.getRating()*2);
					break;
				case R.id.RatingBar02:
					rb1.setRating(rb2.getRating());
					rb3.setRating(rb2.getRating()*2);
					break;
				case R.id.RatingBar03:
					rb1.setRating(rb3.getRating()/2);
					rb2.setRating(rb3.getRating()/2);
					break;
				}
			}
		};
		
		rb1.setOnRatingBarChangeListener(orbcl);
		rb2.setOnRatingBarChangeListener(orbcl);
		rb3.setOnRatingBarChangeListener(orbcl);

    }
    
}
