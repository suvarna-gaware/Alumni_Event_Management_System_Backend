package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Exception.EventNotFoundException;
import com.example.demo.model.Event;
import com.example.demo.model.EventDTO;
import com.example.demo.service.EventService;

@RestController
@CrossOrigin(origins = "*")
public class EventController {
	@Autowired
	EventService eventService;

	@PostMapping("/createEvent")
	public String createEvent(@RequestBody Event event) {
	    System.out.println("Event Data is " + event);
	    boolean b = eventService.isAddNewEvent(event);
	    System.out.println("Description: " + event.getLocation());
	    return b ? "Event Added" : "Event not Added";
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
	public List<Event> searchEventByName(@PathVariable("name") String name) {
		 System.out.println("Searching Alumni by Name in repo ===> " +name);

	    List<Event> event = eventService.getEventByName(name);
	    if (event != null) {
	        return event;
	    } else {
	        throw new EventNotFoundException("Event not found with name: " + name);
	    }
	}


	@PutMapping("/updateEvent/{id}")
	public String updateEvent(@PathVariable int id, @RequestBody Event event) {
	    event.setEventid(id); 
	    boolean updated = eventService.isUpdate(event);
	    System.out.println("controller" + event);
	    if (updated) {
	        return "Event updated successfully";
	    } else {
	        throw new EventNotFoundException("Event not found with ID: " + id); // FIXED LINE
	    }
	}

	
	@DeleteMapping("/deleteEvent/{id}")
	public String deleteEvent(@PathVariable int id) {
		boolean b=eventService.deleteEventById(id);
		if(b) {
			return "Event Delete Successfully:"+id;
		}
		else {
		throw new EventNotFoundException("Event Not found or Alerdy deleted"+id);
		
	}

}
@GetMapping("/getevents")
public List<Map<String, Object>> getevents(){
	return eventService.getEvents(); 
	
}

/*
@GetMapping("/events/alumni/{alumniId}")
public List<Map<String, Object>> getEventsByAlumniId(@PathVariable Integer alumniId) {
	
	System.out.println("=========================================");
	List<Map<String, Object>> list=eventService.getEventsByAlumniId(alumniId);
	System.err.println("------------> "+list);
    return list;
}

*/

//--------------------------------------------------------------------

@GetMapping("/events/alumni/{alumniId}")
public List<Map<String, Object>> getEventsByAlumniId(@PathVariable Integer alumniId) {
    System.out.println("====== Backend Hit: /events/alumni/" + alumniId + " ======");
    List<Map<String, Object>> list = eventService.getEventsByAlumniId(alumniId);
    System.err.println("Events returned: " + list);
    return list;
}
//--------------------------------------------------------------------
//@GetMapping("/alumni/{alumniId}")
//public List<Map<String, Object>> getEventsByAlumniId(@PathVariable int alumniId) {
//    return eventService.getEventsByAlumniId(alumniId);
//}

@GetMapping("/by-department/{deptName}")
public List<Event> getEventsByDepartmentName(@PathVariable String deptName) {
    System.out.println("Received department: " + deptName);
    return eventService.getEventsByDepartmentName(deptName);
}


@PostMapping("/create")
public ResponseEntity<String> createEventDept(@RequestBody EventDTO eventDTO) {
    eventService.createEventDetp(eventDTO);
    return ResponseEntity.ok("Event created successfully");
}


@GetMapping("/department/{deptid}")
public List<EventDTO> viewEventsByDept(@PathVariable int deptid) {
    return eventService.getEventsByDept(deptid);
}




}
