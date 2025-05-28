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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Exception.EventAttendanceNotFoundException;
import com.example.demo.model.EventAttendance;
import com.example.demo.service.EventAttendanceService;

@RestController
@RequestMapping("/api") // security
@CrossOrigin(origins = "*")
public class EventAttendanceController {

	@Autowired
	private EventAttendanceService EventService;

	@PostMapping("/createAttendance")
	public String createAttendance(@RequestBody EventAttendance Eattendance) {
		System.out.println(Eattendance.getAlumniid() + "" + Eattendance.getDeptid() + " " + Eattendance.getEventid());
		boolean b = EventService.isAddAttendance(Eattendance);
		return b ? "eventattendance  added " : "attendance not aaded";

	}

	@GetMapping("/ViweAttendance")
	public List<EventAttendance> getAllAttendance() {
		List<EventAttendance> list = EventService.getAllAttendance();
		if (list.size() != 0) {
			return list;

		} else {
			throw new EventAttendanceNotFoundException("ther is no data n database");
		}

	}

	@PutMapping("/updateAtten")
	public String isUpdateAttendance(@RequestBody EventAttendance attendance) {
		boolean b = EventService.isUpdateAttendance(attendance);
		if (b) {
			return "Attendance record updated with id" + attendance;

		} else {
			throw new EventAttendanceNotFoundException("attendance not found using id" + attendance.getAlumniid());

		}

	}

	@DeleteMapping("/deleteAttadnce/{id}")
	public String isDeleteAttendanceById(@PathVariable int id) {
		boolean b = EventService.isDeleteAttendanceById(id);
		if (b) {
			return "Attendance delete successfully" + id;

		} else {
			throw new EventAttendanceNotFoundException("Attendance Not Found or alredy deleted" + id);
		}

	}

	@GetMapping("/getallAttendance")
	public List<Map<String, Object>> getAttendance() {
		return EventService.getAttendance();

	}

	// [================================

//  @GetMapping("/alumni")
//  public List<EventAttendance> getFilteredAlumni(@RequestParam("deptId") int deptId, @RequestParam("eventId") int eventId,  @RequestParam("passoutYear") int passoutYear) {
//   
//	  System.err.println(deptId+" "+eventId+"  "+passoutYear);
//      return null;
//  }
//  

	@GetMapping("/alumniAttendance")
	public List<EventAttendance> getAlumniAttendance(@RequestParam int deptId, @RequestParam int passoutYear,
			@RequestParam int eventId) {
		return EventService.getAlumniAttendanceByDeptYearEvent(deptId, passoutYear, eventId);
	}

	@PostMapping("/updateAttendance")
	public String updateAttendance(@RequestBody List<EventAttendance> ea) {

		System.out.println("Received for update: " + ea);
		boolean b = EventService.updateAttendance(ea);
		if (b) {

			System.err.println("============Attendance updated successfully");
			return "Attendance updated successfully";

		} else {
			System.err.println("=========Attendance not updated ");

			return "Attendance  not updated ";
		}

	}

}