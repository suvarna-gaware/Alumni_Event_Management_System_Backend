package com.example.demo.service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Alumni;
import com.example.demo.model.Event;
import com.example.demo.repository.AlumniRepository;
import com.example.demo.repository.EventRepository;


@Service("eventService")
public class EventService {
	@Autowired
	private EventRepository eventRepo;
	
	public boolean isAddNewEvent(Event event) {
		return eventRepo.isAddNewEvent(event) ;
		
	}
	public List<Event>getAllEvent(){
		return eventRepo.getAllEvent();
	}
	public Event getEventById(Integer id) {
		return eventRepo.getEventById(id);

}
	
	 public boolean isUpdate(Event event) {
	        return eventRepo.isUpdate(event);
	    }
	
	
	public boolean deleteEventById(int id) {
		
		return eventRepo.deleteEventById(id);
	}
	public List<Event> getEventByName(String name) {
		 System.out.println("Searching Alumni by Name in repo ===> " +name);

		
		return eventRepo.getEventByName(name);
	}
	
	public List<Map<String, Object>> getEvents(){
		return eventRepo.getEvents();
	}
}