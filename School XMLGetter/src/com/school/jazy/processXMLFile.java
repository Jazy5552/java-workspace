package com.school.jazy;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.app.Activity;

import com.school.events.JEvent;
import com.school.events.School;

public class processXMLFile {

	String ssh = ""; //ssh = sexyStringHolder ;D
	School school = null;
	File xmlFile;
	Activity aa;
	String tmp = "";
	
	processXMLFile (File file,Activity aa){
		xmlFile = file;
		this.aa = aa;
	}
	
	public String toString(){
		return ssh;
	}
	
	public School getSchoolEvents(){
		return school;
	}
	
	public void parse(){
		tmp = "";
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
		 
			DefaultHandler handler = new DefaultHandler() {
				JEvent event = null;
				boolean pevent = false;
				boolean pname = false;
				boolean pcategory = false;
				boolean ptime = false;
				boolean pdate = false;
				boolean pprice = false;
				boolean pdescription = false;
				boolean pschool = false;
				public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {
					System.out.println("Start Element:" + qName);
					if (qName.equalsIgnoreCase("SCHOOL")){
						pschool = true;
					}
					if (qName.equalsIgnoreCase("EVENT")) {
						//Starting new event
						pevent = true;
					}
					if (qName.equalsIgnoreCase("CATEGORY")) {
						pcategory = true;
					}
					if (qName.equalsIgnoreCase("NAME")) {
						pname = true;
					}
					if (qName.equalsIgnoreCase("TIME")) {
						ptime = true;
					}
					if (qName.equalsIgnoreCase("DATE")) {
						pdate = true;
					}
					if (qName.equalsIgnoreCase("PRICE")) {
						pprice = true;
					}
					if (qName.equalsIgnoreCase("DESCRIPTION")) {
						pdescription = true;
					}
					
				}
		 
				public void endElement(String uri, String localName,String qName) throws SAXException {
					if (qName.equalsIgnoreCase("EVENT")) {//CLose event
						school.addEvent(event);
						tmp += "\n";
					}
					
				}
		 
				public void characters(char ch[], int start, int length) throws SAXException {
					String text = new String(ch,start,length);
					if (pschool) {
						school = new School(text);
						pschool = false;
					}
					if (pevent) { //Start new event
						event = new JEvent();
						tmp += "Event:\n";
						pevent = false;
					}
					if (pname) {
						event.setName(text);
						tmp += text + "\n";
						pname = false;
					}
					if (pcategory) {
						event.setCategory(text);
						tmp += "["+text+"]" + "\n";
						pcategory = false;
					}
					if (ptime) {
						event.setTime(text);
						tmp += "at "+ text + "\n";
						ptime = false;
					}
					if (pdate) {
						event.setDate(text);
						tmp += text + "\n";
						pdate = false;
					}
					if (pprice) {
						event.setPrice(text);
						tmp += "Price is " + text + "\n";
						pprice = false;
					}
					if (pdescription) {
						event.setDescription(text);
						tmp += "-" + text + "\n";
						pdescription = false;
					}
					
				}
		     };//End of Handler
		     
		     saxParser.parse(xmlFile, handler);
		} catch (Exception e) {
			e.printStackTrace();
			tmp = "Error:" + e.getMessage();
		}
		ssh = tmp;
	}
}
