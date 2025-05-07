package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Alumni {
	private int alumniid;
	private int deptid;
	private String name;
	private String email;
	private String contact;
	private String address;
	private String gender;
	private int year;
   private String status;

}