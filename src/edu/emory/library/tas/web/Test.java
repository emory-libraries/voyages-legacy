package edu.emory.library.tas.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Test
{
	
	public static void main(String[] args)
	{
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(df.format(new Date()));
		
	}
	
}
