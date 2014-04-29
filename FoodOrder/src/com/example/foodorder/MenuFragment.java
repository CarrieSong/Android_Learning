package com.example.foodorder;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MenuFragment extends Fragment {
	
	private ListView menuList;  //menu is a list

	public static ArrayAdapter<String> adapter;
	
	public static String[] menuItems = { "Big Mac","", "Cheeseburger","", "Ranch Snack Wrap(Grilled)","",
			"Fries","","Side Salad","", "Coca-Cola","","Sweet Tea",""};

	public void onAttach(Activity activity) {  
        super.onAttach(activity);  
        adapter = new ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, menuItems); 

    }
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {  
        View view = inflater.inflate(R.layout.menu_fragment, container, false);  
        menuList = (ListView) view.findViewById(R.id.menu_list);  
        menuList.setAdapter(adapter);  
        menuList.setOnItemClickListener(content_item_listener);  
        return view;  
    }
	
	OnItemClickListener content_item_listener = new OnItemClickListener()     
	{
		@Override 
	    public void onItemClick(AdapterView<?> arg0, View view, int index, long arg3) {              
	            if(index % 2 == 0)
	            {
	            	Intent intent = null;  
	            	intent = new Intent(getActivity(), SubActivity.class); 
		            Bundle bundle = new Bundle();
	            	bundle.putInt("INDEX", index);	            	
	            	intent.putExtras(bundle);
	            	startActivity(intent);  
	            }
	            else
	            	;
	        } 		
		
	};	
	
	public static void reset()
	{
		for(int i = 1; i <= 13; i += 2)
			menuItems[i] = "";
		adapter.notifyDataSetChanged();
	}
	
	
}


