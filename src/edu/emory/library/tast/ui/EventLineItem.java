package edu.emory.library.tast.ui;

public class EventLineItem
{
	
	private int year;
	private String text;
	
	public EventLineItem(int year, String text)
	{
		this.year = year;
		this.text = text;
	}

	public String getText()
	{
		return text;
	}
	
	public void setText(String text)
	{
		this.text = text;
	}
	
	public int getYear()
	{
		return year;
	}
	
	public void setYear(int year)
	{
		this.year = year;
	}

}
