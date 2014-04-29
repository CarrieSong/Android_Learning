package com.example.foodorder;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SubActivity extends Activity {

	@Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.sub_activity);  
        bundle = this.getIntent().getExtras();
        index = bundle.getInt("INDEX");
        findViews();
        showResults();
        setListeners();
        setColors();
    }
	
	private TextView textview;
	private ImageView imageview;
	private Button b_small;
	private Button b_medium;
	private Button b_big;
	private Button b_cancel;
	private Button b_order;
	private TextView view_s;
	private TextView view_m;
	private TextView view_b;
	
	private void findViews() {
		textview = (TextView) findViewById(R.id.textview);
		imageview = (ImageView) findViewById(R.id.imagview);
		b_small = (Button) findViewById(R.id.small_btn);
		b_medium = (Button) findViewById(R.id.medium_btn);
		b_big = (Button) findViewById(R.id.big_btn);
		view_s = (TextView) findViewById(R.id.prize_s);
		view_m = (TextView) findViewById(R.id.prize_m);
		view_b = (TextView) findViewById(R.id.prize_b);
		b_cancel = (Button) findViewById(R.id.cancel_btn);
		b_order = (Button) findViewById(R.id.order_btn);
		
	}
	
	private void setListeners() {
        b_small.setOnClickListener(small);
        b_medium.setOnClickListener(medium);
        b_big.setOnClickListener(big);
        b_cancel.setOnClickListener(cancel);
        b_order.setOnClickListener(order);
    }
	
	private void setColors() 
	{
		b_small.setBackgroundColor(Color.CYAN);
		b_medium.setBackgroundColor(Color.CYAN);
		b_big.setBackgroundColor(Color.CYAN);
		b_cancel.setBackgroundColor(Color.YELLOW);
		b_order.setBackgroundColor(Color.YELLOW);
	}
	
	String size;
	
	private Button.OnClickListener small = new Button.OnClickListener()
    {
        public void onClick(View v)
        {
        	size = view_s.getText().toString();
        	b_small.setBackgroundColor(Color.YELLOW);
        	b_medium.setBackgroundColor(Color.CYAN);
    		b_big.setBackgroundColor(Color.CYAN);
        }
    };
	
    private Button.OnClickListener medium = new Button.OnClickListener()
    {
        public void onClick(View v)
        {
        	size = view_m.getText().toString();
        	b_medium.setBackgroundColor(Color.YELLOW);
        	b_small.setBackgroundColor(Color.CYAN);
        	b_big.setBackgroundColor(Color.CYAN);
        }
    };
    
    private Button.OnClickListener big = new Button.OnClickListener()
    {
        public void onClick(View v)
        {
        	size = view_b.getText().toString();
        	b_big.setBackgroundColor(Color.YELLOW);
        	b_small.setBackgroundColor(Color.CYAN);
    		b_medium.setBackgroundColor(Color.CYAN);
        }
    };
    
    Bundle bundle;
    int index;
    
    private Button.OnClickListener cancel = new Button.OnClickListener()
    {	
        public void onClick(View v)
        {
        	MenuFragment.menuItems[index+1] = "";
    		MenuFragment.adapter.notifyDataSetChanged();
    		finish();
        }
    };
    
    private Button.OnClickListener order = new Button.OnClickListener()
    {
        public void onClick(View v)
        {
        	MenuFragment.menuItems[index+1] = size;
        	MenuFragment.adapter.notifyDataSetChanged();
        	finish();
        }
    };
    
    private void showResults()
	{
    	
		if(index == 0) {
			textview.setText("A double layer of sear-sizzled 100% pure beef mingled with special sauce on a sesame seed bun and topped with melty American cheese, crisp lettuce, minced onions and tangy pickles.");
			imageview.setImageResource(R.drawable.bigmac);
		}
		else if(index == 2) {
			textview.setText("A juicy 100% beef patty simply seasoned with a pinch of salt and pepper, melty American cheese, tangy pickles, minced onions, ketchup and mustard.");
			imageview.setImageResource(R.drawable.cheeseburger);
		}
		else if(index == 4) {
			textview.setText("Feast on this: A juicy strip of tender chicken breast filet covered with creamy ranch, crisp iceberg lettuce, jack and cheddar cheeses all wrapped in a soft flour tortilla.");
			imageview.setImageResource(R.drawable.wrap);
		}
		else if(index == 6) {
			textview.setText("Golden on the outside, soft and fluffy on the inside. Made with quality potatoes and cooked in our Canola oil blend for zero grams of trans fat per serving. Now that's an epic bite.");
			imageview.setImageResource(R.drawable.fries);
		}
		else if(index == 8) {
			textview.setText("Made fresh daily with a variety of premium mixed greens, up to two cups of veggies like our tasty grape tomatoes and shaved carrots and as always, served with your choice of Newman's Own Dressing.");
			imageview.setImageResource(R.drawable.salad);
		}
		else if(index == 10) {
			textview.setText("A cold and refreshing complement to all of our menu items.");
			imageview.setImageResource(R.drawable.coke);
		}
		else {
			textview.setText("A briskly refreshing blend of orange pekoe and pekoe cut black tea, sweetened to perfection.");
			imageview.setImageResource(R.drawable.tea);
		}
		
		
		
	}
    

}
