package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.EventAttendance;
import com.example.demo.repository.EventAttendanceRepository;
@Service("EventService")
public class EventAttendanceService {

	@Autowired EventAttendanceRepository AttendanceRepo;
	
	public boolean isAddAttendance(EventAttendance eattendance) {
		
		return AttendanceRepo.isAddAttendance(eattendance) ;
	}

}
