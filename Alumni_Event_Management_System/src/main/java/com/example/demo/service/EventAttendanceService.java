package com.example.demo.service;

import java.util.List;
import java.util.Map;

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

	public List<EventAttendance> getAllAttendance() {
		
		return AttendanceRepo.getAllAttendance();
	}

	public boolean isUpdateAttendance(EventAttendance attendance) {

		return AttendanceRepo.isUpdateAttendance(attendance);
	}

	public boolean isDeleteAttendanceById(int id) {
		
		return AttendanceRepo.isDeleteAttendanceById(id);
	}

	public List<Map<String, Object>> getAttendance() {
		
		return AttendanceRepo.getAttendance();
	}

}
