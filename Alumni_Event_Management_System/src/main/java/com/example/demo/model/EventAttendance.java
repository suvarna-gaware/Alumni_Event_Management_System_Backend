package com.example.demo.model;


import lombok.Data;

@Data
public class EventAttendance {
	private int eventid;
	 //@JsonProperty("alumniid") 
	private int alumniid;
	private int deptid;
	private String status;
	
	//==============================
	private String name;
	private String deptname;
	   private String eventname;
	

}