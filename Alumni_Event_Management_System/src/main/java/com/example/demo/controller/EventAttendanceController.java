package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Exception.EventAttendanceNotFoundException;
import com.example.demo.model.EventAttendance;
import com.example.demo.service.EventAttendanceService;

@RestController
@CrossOrigin(origins="*")
public class EventAttendanceController {
	
	@Autowired
	private EventAttendanceService EventService;
	
  @PostMapping("/createAttendance")
  public String createAttendance(@RequestBody EventAttendance Eattendance) {
	  System.out.println(Eattendance.getAlumniid()+""+Eattendance.getDeptid()+" "+Eattendance.getEventid());
	boolean b=EventService.isAddAttendance(Eattendance);
	  return b? "eventattendance  added ":"attendance not aaded";
	  
  }
  
  @GetMapping("/ViweAttendance")
  public List<EventAttendance> getAllAttendance(){
	  List<EventAttendance> list=EventService.getAllAttendance();
	  if(list.size()!=0) {
		  return list;
		  
		  
	  }
	  else {
		  throw new EventAttendanceNotFoundException("ther is no data n database");
	  }
	
  }
  
  @PutMapping("/updateAttendance")
  public String isUpdateAttendance(@RequestBody EventAttendance attendance) {
	 boolean b=EventService.isUpdateAttendance(attendance);
	 if(b) {
		 return "Attendance record updated with id"+attendance;
		 
	 }
	 else {
		 throw new EventAttendanceNotFoundException("attendance not found using id"+attendance.getAlumniid());
		 
	 }
	  
	  
  }
  
  @DeleteMapping("/deleteAttadnce/{id}")
  public String isDeleteAttendanceById(@PathVariable int id) {
	  boolean b=EventService.isDeleteAttendanceById(id);
	  if(b) {
		  return "Attendance delete successfully"+id;
		  
		  
	  }
	  else {
		  throw new EventAttendanceNotFoundException("Attendance Not Found or alredy deleted"+id);
	  }
	
	  
	
  }
  
  @GetMapping("/getallAttendance")
  public List<Map<String,Object>> getAttendance(){
	return EventService.getAttendance();
	  
  }
  
  
	
}
