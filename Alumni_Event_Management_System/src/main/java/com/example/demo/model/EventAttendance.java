package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class EventAttendance {
	private int eventid;
	 @JsonProperty("alumniid") 
	private int Alumniid;
	private int deptid;
	private String status;
	

}
