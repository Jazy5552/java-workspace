package com.school.events;

public class JEvent {
	
	String eventName = null;
	String category = null;
	String time = null;
	String date = null;
	String description = null; //Used as other
	String price = null;
	
	public void setName(String n){
		eventName = n;
	}
	
	public void setCategory(String n){
		category = n;
	}
	
	public void setTime(String n){
		time = n;
	}
	
	public void setDate(String n){
		date = n;
	}
	
	public void setPrice(String n){
		price = n;
	}
	
	public void setDescription(String n){
		description  = n;
	}
	
	public String getName(){
		return eventName;
	}
	
	public String getCategory(){
		return category;
	}
	
	public String getTime(){
		return time;
	}
	
	public String getDate(){
		return date;
	}
	
	public String getPrice(){
		return price;
	}
	
	public String getDescription(){
		return description;
	}

}
