package com.example.demo.model;

import java.time.LocalDate;

import lombok.Data;
@Data
public class Event {
	private int Eid;
	private int Orgid;
	private String ename;
	private LocalDate dates;
	private String Description;
	private String venue;
	
	

}
