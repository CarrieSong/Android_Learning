package com.example.foodorder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FoodOrder extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_food_order);
		db = new DBAdapter(this);
		findViews();
		button_checkout.setText(R.string.checkout_btn);
		setListeners();
		setColors(); //set background colors for buttons
	}

	DBAdapter db;
	private Button button_reset;
	private Button button_checkout;
	
	private void findViews()
	{
		button_reset = (Button) findViewById(R.id.reset_btn);
		button_checkout = (Button) findViewById(R.id.checkout_btn);
	}
	
	private void setListeners() {
        button_reset.setOnClickListener(reset);
        button_checkout.setOnClickListener(checkout);
    }
	
	private void setColors()
	{
		button_reset.setBackgroundColor(Color.YELLOW);
		button_checkout.setBackgroundColor(Color.YELLOW);
	}
	
	private Button.OnClickListener reset = new Button.OnClickListener()
    {
		public void onClick(View v)
		{
        	MenuFragment.reset();
		}
    };
    
    private Button.OnClickListener checkout = new Button.OnClickListener()
    {
        public void onClick(View v)
        {
        	if(button_checkout.getText().toString().equals("Check Out"))
        	{
        		Toast.makeText(FoodOrder.this, "The order has been sent.", Toast.LENGTH_LONG).show();
            	button_checkout.setText(R.string.save_btn);
        	}
        	else
        	{
        		//save current order
        		String items = "";
        		String date;
        		for(int i = 1; i < 14; i += 2)
        		{
        			if(!(MenuFragment.menuItems[i].equals("")))
        			{
        				items += MenuFragment.menuItems[i-1]+": "+MenuFragment.menuItems[i]+"\n";
        			} 
        		}
        		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS") ;     
    			date = df.format(new java.util.Date());
    			db.insertOrder(items, date);      		
        		Toast.makeText(FoodOrder.this, "The order has been saved.", Toast.LENGTH_LONG).show();
        		button_checkout.setText(R.string.checkout_btn);
        	}
        }
    };
    
    protected static final int MENU_ABOUT = Menu.FIRST;
    protected static final int MENU_EXPORT = Menu.FIRST+1;
    protected static final int MENU_DELETE = Menu.FIRST+2;
    protected static final int MENU_CONTACT = Menu.FIRST+3;
    protected static final int MENU_QUIT = Menu.FIRST+4;
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, MENU_ABOUT, 0, "About");
        menu.add(0, MENU_EXPORT, 0, "Export");
        menu.add(0, MENU_DELETE, 0, "Delete order history");
        menu.add(0, MENU_CONTACT, 0, "Search from Contact");
        menu.add(0, MENU_QUIT, 0, "Quit");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
       super.onOptionsItemSelected(item);
       switch(item.getItemId()){
            case MENU_ABOUT:
            	Intent mIntent = new Intent();  
                mIntent.setClass(this, MyPreference.class);  
                startActivity(mIntent);    
                break;
            case MENU_EXPORT:
            	//export order data
            	
            	List<Orders> list = db.findAll();
            	if(list != null){
            		if(isExternalStorageWritable())
            		{
            			// save order history date to a new file in sdcard
            			//the file name can be hard coded or allow user to enter.
            			File file = new File(Environment.getExternalStorageDirectory(), "OrderHistory.txt");
            			Iterator<Orders> ite = list.iterator();
            			FileWriter fw;
						try {
							fw = new FileWriter (file, false);
							while(ite.hasNext())
	            			{
	            				String str = ite.next().toString();
	            				fw.write(str);
	            			}
	            			fw.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}		
						Toast.makeText(FoodOrder.this, "Export successfully.", Toast.LENGTH_LONG).show();
            		}
            		else {
            			Toast.makeText(FoodOrder.this, "External Storage is not writable right now.", Toast.LENGTH_LONG).show();
            		}	
            	}else {
            		Toast.makeText(FoodOrder.this, "No order history available.", Toast.LENGTH_LONG).show();
            	}
            	break;
            case MENU_DELETE:
            	db.deleteOrders();
            	break;
            case MENU_CONTACT:
            	Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI); 	 
                startActivityForResult(intent, 1);
                break;
           case MENU_QUIT:
                finish();
               break;
         }
         return true;
    }
    
    @Override 
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
      
      // TODO Auto-generated method stub 
        super.onActivityResult(requestCode, resultCode, data); 
        switch(requestCode) 
        { 
     
            case (1) : 
            if (resultCode == Activity.RESULT_OK) 
            { 
     
            Uri contactData = data.getData(); 
     
            Cursor c = managedQuery(contactData, null, null, null, null); 
     
            c.moveToFirst();  
             
            } 
            break; 
        }        
    }
    
    private String getContactPhone(Cursor cursor) 
    { 
     
        int phoneColumn = cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);   
        int phoneNum = cursor.getInt(phoneColumn);  
        String phoneResult=""; 
        //System.out.print(phoneNum); 
        if (phoneNum > 0) 
        { 
        	// get ID of contact
            int idColumn = cursor.getColumnIndex(ContactsContract.Contacts._ID); 
            String contactId = cursor.getString(idColumn); 
                //get cursor of contact's phone number 
                Cursor phones = getContentResolver().query( 
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, 
                null, 
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID+ " = " + contactId,  
                null, null); 
                //int phoneCount = phones.getCount(); 
                //allPhoneNum = new ArrayList(phoneCount); 
                if (phones.moveToFirst()) 
                { 
                	// search all phone numbers
                    for (;!phones.isAfterLast();phones.moveToNext()) 
                    {                                             
                        int index = phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER); 
                        int typeindex = phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE); 
                        int phone_type = phones.getInt(typeindex); 
                        String phoneNumber = phones.getString(index); 
                        switch(phone_type) 
                        { 
                            case 2: 
                                phoneResult=phoneNumber; 
                            break; 
                        } 
                           //allPhoneNum.add(phoneNumber); 
                    } 
                    if (!phones.isClosed()) 
                    { 
                           phones.close(); 
                    } 
                } 
        } 
        return phoneResult; 
    } 

    
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }




}
