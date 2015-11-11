package com.board;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AdDate {
	
	private int id;
	private Date date;
	private String name, headline, rubric, text;
	
	public AdDate(){
		setName("Уважаемый");
	}
	
	public AdDate(int id, String name, String headline, String rubric, String text, Date date){
		setId(id);
		setName(name);
		setHeadline(headline);
		setRubric(rubric);
		setText(text);
		setDate(date);
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
	
	public void setDate(Date date){
		this.date = date;
	}
	
	public Date getDate(){
		return date;
	}
}
