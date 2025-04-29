package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.example.demo.service.AlumniService;
@RestController
@CrossOrigin(origins="*")
//@CrossOrigin(origins="http://localhost:5174") 
public class AlumniController {
	@Autowired
	AlumniService alumService;
	
	@PostMapping("/createAlumni")
	public String createAlumni(@RequestBody Alumni alumni) {
		 boolean b=alumService.isAddNewAlumni(alumni);
		 if(b) {
			 return "Alumni Added";
		 }
		 else {
			 return "Alumni not Added";
		 }
		
		
	}
	@GetMapping("/viewAllAlumni")
	public List<Alumni>getAllAlumni(){
		List<Alumni>list=alumService.getAllAlumni();
		if(list.size()!=0) {
			return list;
		}
		else {
			throw new AlumniNotFoundException("There is no data in database");
			
		}	
		
	}
	@GetMapping("/searchAlumniByName/{name}")
	public List<Alumni> searchAlumniByName(@PathVariable("name") String name) {
	    System.out.println("Searching Alumni by Name ===> " + name);

	    List<Alumni> alumniList = alumService.getAlumniByName(name);

	    if (alumniList != null) {
	        return alumniList;
	    } else {
	       throw new AlumniNotFoundException("Alumni not found"+name);
	    }
	}
	 

	@PutMapping("/updateAlumni")
	public String isupdateAlumni(@RequestBody  Alumni alumni) {
		System.out.println(alumni);
		boolean b=alumService.isUpdate(alumni);
		if(b) {
			return "Alumni Record update with id"+alumni;
		}
		else {
			throw new AlumniNotFoundException("Alumni not Found using id"+alumni.getAlumniid());
			
		}
		
		
		
	}
	
	@DeleteMapping("/deleteAlumni/{id}")
	public String deleteAlumni(@PathVariable int id) {
		boolean b=alumService.deleteAlumniById(id);
		
			if(b) {
				return "Alumni Delete Successfully"+id;
			}
			else {
				throw new AlumniNotFoundException("Alumni not found or alerdy deleted"+id);
			}
		
	
		
	}
	
	
	}


