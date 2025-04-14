package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Exception.AlumniNotFoundException;
import com.example.demo.Exception.ErrorMassage;
import com.example.demo.model.Alumni;
import com.example.demo.service.AlumniService;
@RestController
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
	@GetMapping("/searchAlumniById/{Aid}")
	public Alumni SearchAlumniById(@PathVariable("Aid") Integer id) {
		Alumni al=alumService.getAlumniById(id);
		if(al!=null) {
			return al;
			
		}
		else {
			throw new AlumniNotFoundException("Employee not found using"+id);
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
	}


