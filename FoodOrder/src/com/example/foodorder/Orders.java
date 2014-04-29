package com.example.foodorder;

public class Orders {

	private int id ;  
	private String items;  
	private String order_date;  
	public int getId() {  
	    return id;  
	}  
	public void setId(int id) {  
	    this.id = id;  
	}  
	public String getItems() {  
	    return items;  
	} 
	public void setItems(String _items) {
		this.items = _items;
	}
	public String getOrderDate() {  
	    return order_date;  
	}  
	public void setOrderDate(String orderdate) {  
	    this.order_date = orderdate;  
	}  
	
	public String toString() {
		return "id: "+getId()+"\nItems: "+getItems()+"order date: "+getOrderDate()+"\n";
	}

}
