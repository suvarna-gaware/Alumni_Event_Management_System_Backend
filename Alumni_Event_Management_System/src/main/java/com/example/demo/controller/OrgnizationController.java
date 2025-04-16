package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Exception.OrgnizationNotFoundException;
import com.example.demo.model.Orgnization;
import com.example.demo.service.OrgnizationService;

@RestController
public class OrgnizationController {
	@Autowired
	OrgnizationService orgService;
	
	@PostMapping("/createOrg")
	public String createorg(@RequestBody Orgnization org) {
		System.out.println("org details");
		System.out.println(org);
		boolean b=orgService.isAddNewOrg(org);
		
		if(b) {
			return "Orgnization Added";
		}
		return "Orgnization not Added";
		
	}
	
	@GetMapping("/viweAllOrg")
	
	public List<Orgnization> getAllOrg(){
		List<Orgnization> list=orgService.getAllOrg();
		if(list.size()!=0) {
			return list;
		}
		else {
			throw new OrgnizationNotFoundException("not founs");
		}
		
	}
	
	@GetMapping("/searchOrgByname/{name}")
	public Orgnization getOrgByName(@PathVariable ("name") String name) {
		
		Orgnization org=orgService.getOgrByName(name);
		if(org!=null) {
			return org;
		}
		 throw new OrgnizationNotFoundException("orgnization not found"+name);
		
	}
	@PutMapping("/updateOrg")
	
	public String isUpdateOrg(@RequestBody Orgnization org) {
		System.out.println("update:"+org);
		
		boolean b=orgService.isUpdateorg(org);
		if(b) {
			return "Orgnization update with id"+org.getOrgid();
		}
		throw new OrgnizationNotFoundException("orgnizatin not found"+org.getOrgid());
		
	}
	
	

}
