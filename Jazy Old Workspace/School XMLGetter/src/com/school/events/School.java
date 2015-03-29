package com.school.events;

import java.util.ArrayList;

public class School {
	
	final String SchoolName;
	ArrayList<JEvent> events = new ArrayList<JEvent>(0);
	ArrayList<String> categorys = new ArrayList<String>(0);
	
	public School(String name){
		SchoolName = name;
	}
	
	public void addEvent(JEvent ev){
		events.add(ev);
		//Add a category
		String ca = ev.getCategory();
		if (ca==null) return;
		for (int i=0;i<categorys.size();i++){
			if (categorys.get(i).equalsIgnoreCase(ca)){
				return;
			}
		}
		categorys.add(ca.toLowerCase());
	}
	
	public void removeEvent(JEvent ev){
		events.remove(ev);
	}
	
	public void removeEvent(String eventName){
		for (int i=0;i<events.size();i++){
			if (events.get(i).getName().equalsIgnoreCase(eventName)){
				events.remove(i);
			}
		}
	}
	
	public void removeAllEvents(){
		events = new ArrayList<JEvent>(0);
	}
	
	public JEvent getEventByName(String name){
		for (int i=0;i<events.size();i++){
			if (events.get(i).getName().equalsIgnoreCase(name)){
				return events.get(i);
			}
		}
		return null;
	}
	
	public ArrayList<JEvent> getEvents(){
		return events;
	}

}
