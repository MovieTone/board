package com.board;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Ad {
	
	private int id;
	private long time;
	private String name, headline, rubric, text;
	
	public Ad(){
		setName("Уважаемый");
	}
	
	public Ad(String name, String headline, String rubric, String text, long time){
		setName(name);
		setHeadline(headline);
		setRubric(rubric);
		setText(text);
		setTime(time);
	}
	
	public Ad(String name, String headline, String rubric, String text){
		setName(name);
		setHeadline(headline);
		setRubric(rubric);
		setText(text);
		setTime(System.currentTimeMillis() / 1000L);
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}
	
	public void setHeadline(String headline){
		this.headline = headline;
	}
	
	public String getHeadline(){
		return headline;
	}
	
	public void setRubric(String rubric){
		this.rubric = rubric;
	}
	
	public String getRubric(){
		return rubric;
	}
	
	public void setText(String text){
		this.text = text;
	}
	
	public String getText(){
		return text;
	}
	
	public void setTime(long time){
		this.time = time;
	}
	
	public long getTime(){
		return time;
	}
}
