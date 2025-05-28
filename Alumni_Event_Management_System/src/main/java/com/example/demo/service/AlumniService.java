package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Alumni;
import com.example.demo.model.AlumniDTO;
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
//	public Alumni getAlumniById(Integer id) {
//		return alumniRepo.getAlumniById(id);
//		
//	}
	public boolean isUpdate(Alumni alumni) {
		return alumniRepo.isUpdate(alumni);
		
	}
	public List<Alumni> getAlumniByName(String name) {
	    System.out.println("Searching Alumni by Name in service ===> " + name);
	    return alumniRepo.getAlumniByName(name);  
	}

	public boolean deleteAlumniById(int id) {
		
		return alumniRepo.deleteAlumniById(id);
	}
	public List getAlumniByIdd(Integer aid) {
		System.out.println("Service:"+aid);
		
		return alumniRepo.getAlumniByIdd(aid);
	}
	public Alumni iseAlumniLogin(Alumni alumni) {
		// TODO Auto-generated method stub
		return alumniRepo.iseAlumniLogin(alumni);
	}
	
	
	

    public List<AlumniDTO> getAlumniByDepartment(int deptid) {
        return alumniRepo.getAlumniByDepartment(deptid);
    }
    
	}


