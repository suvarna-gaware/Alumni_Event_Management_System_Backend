package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.EventAttendance;
import com.example.demo.service.EventAttendanceService;

@RestController
public class EventAttendanceController {
	
	@Autowired
	private EventAttendanceService EventService;
	
  @PostMapping("/createAttendance")
  public String createAttendance(@RequestBody EventAttendance Eattendance) {
	  System.out.println("hello");
	boolean b=EventService.isAddAttendance(Eattendance);
	  return b? "eventattendance  added ":"attendance not aaded";
	  
  }
	
}
