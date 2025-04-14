package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Alumni;
import com.example.demo.repository.AlumniRepository;

@Service("alumniService")
public class AlumniService {
	@Autowired
	private AlumniRepository alumniRepo;
	
	public boolean isAddNewAlumni(Alumni alumni) {
		return alumniRepo.isAddNewAlumni(alumni) ;
		
	}
	public List<Alumni>getAllAlumni(){
		return alumniRepo.getAllAlumni();
	}
	public Alumni getAlumniById(Integer id) {
		return alumniRepo.getAlumniById(id);
		
	}
	public boolean isUpdate(Alumni alumni) {
		return alumniRepo.isUpdate(alumni);
		
	}
	
	}


