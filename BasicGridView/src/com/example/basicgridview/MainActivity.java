package com.example.basicgridview;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        final ImageView iv= (ImageView)findViewById(R.id.ImageView01);
        Gallery g = (Gallery) findViewById(R.id.Gallery01);
        
        g.setAdapter(new ImageAdapter(this));
        
        g.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "" + position,
						Toast.LENGTH_SHORT).show();
				iv.setImageResource(((ImageView)view).getId());
			}
        	
        });       
    }
    
    public class ImageAdapter extends BaseAdapter {
    	
    	int mGalleryItemBackground;
    	
    	private Context mContext;
    	
    	private Integer[] mImageIds = { 
    			R.drawable.wallpaper0, R.drawable.wallpaper1, 
    			R.drawable.wallpaper2, R.drawable.wallpaper3, 
    			R.drawable.wallpaper4, R.drawable.wallpaper5, 
    			R.drawable.wallpaper6, R.drawable.wallpaper7, 
    			R.drawable.wallpaper8, R.drawable.wallpaper9
    	};
    	
    	public ImageAdapter(Context c){
    		mContext = c;
    		
    		TypedArray a = obtainStyledAttributes(R.styleable.HelloGallery);
    		mGalleryItemBackground = a.getResourceId(
    				R.styleable.HelloGallery_android_galleryItemBackground, 0);
    		a.recycle();	
    	}
    	
    	@Override
    	public int getCount() {
    		return mImageIds.length;
    	}
    	
    	@Override
    	public Object getItem(int position) {
    		return position;
    	}
    	
    	@Override
    	public long getItemId(int position) {
    		return position;
    	}
    	
    	@Override
    	public View getView(int position, View convertView, ViewGroup parent) {
    		ImageView iv = new ImageView(mContext);
    		iv.setImageResource(mImageIds[position]);
    		iv.setId(mImageIds[position]);
    		iv.setLayoutParams(new Gallery.LayoutParams(120, 160));
    		iv.setScaleType(ImageView.ScaleType.FIT_XY);
    		iv.setBackgroundResource(mGalleryItemBackground);
    		return iv;
    	}
    	
    }
    
}
