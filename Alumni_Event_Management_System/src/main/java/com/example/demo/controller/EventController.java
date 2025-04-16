package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Exception.AlumniNotFoundException;
import com.example.demo.Exception.EventNotFoundException;
import com.example.demo.model.Alumni;
import com.example.demo.model.Event;
import com.example.demo.service.EventService;

@RestController
@CrossOrigin(origins = "*")
public class EventController {
	@Autowired
	EventService eventService;

	@PostMapping("/createEvent")
	public String createEvent(@RequestBody Event event) {
		boolean b = eventService.isAddNewEvent(event);
		System.out.println("Description: " + event.getDescription()); // Debug line
		if (b) {
			return "Event Added";
		}
		return "Event not Added";

	}

	@GetMapping("/viewAllEvents")
	public List<Event> getAllEvent() {
		List<Event> list = eventService.getAllEvent();
		if (list.size() != 0) {
			return list;
		} else {
			throw new EventNotFoundException("There is no data in database");

		}

	}

	@GetMapping("/searchEventById/{Eid}")
	public Event searchEventById(@PathVariable("Eid") Integer id) {
		Event event = eventService.getEventById(id);
		if (event != null) {
			return event;
		} else {
			throw new EventNotFoundException("Event not found using ID: " + id);
		}
	}
	@GetMapping("/searchEventByName/{name}")
	public Event searchEventByName(@PathVariable("name") String name) {
	    Event event = eventService.getEventByName(name);
	    if (event != null) {
	        return event;
	    } else {
	        throw new EventNotFoundException("Event not found with name: " + name);
	    }
	}


	@PutMapping("/updateEvent/{id}")
	public String updateEvent(@PathVariable int id, @RequestBody Event event) {
		event.setEid(id); 
		boolean updated = eventService.isUpdate(event);
		if (updated) {
			return " Event updated with ID: " + event.getEid();
		} else {
			throw new EventNotFoundException(" Event not found with ID: " + event.getEid());
		}
	}
	
	@DeleteMapping("/deleteEvet/{id}")
	public String deleteEvent(@PathVariable int id) {
		boolean b=eventService.deleteEventById(id);
		if(b) {
			return "Event Delete Successfully";
		}
		else {
		return "Event Not found or Alerdy deleted";
		
	}

}
}
