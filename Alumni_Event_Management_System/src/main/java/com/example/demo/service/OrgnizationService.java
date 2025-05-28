package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.OrgDashboardCountDTO;
import com.example.demo.model.Orgnization;
import com.example.demo.repository.OrgnizationRepository;
@Service("orgService")
public class OrgnizationService {
	@Autowired
	OrgnizationRepository orgRepo;

	public boolean isAddNewOrg(Orgnization org) {
		System.out.println("service:"+org);
		
		return orgRepo.isAddNewOrg(org) ;
	}

	public List<Orgnization> getAllOrg() {
		
		return orgRepo.getAllorg();
	}

	public List<Orgnization> getOgrByName(String name) {
		
		return orgRepo.getOrgByName(name);
	}

	public boolean isUpdateorg(Orgnization org) {
		
		return orgRepo.isUpdateorg(org);
	}

	public boolean isDeleteOrg(int id) {
		
		return orgRepo.isDeleteOrg(id);
	}

	public Orgnization isOrgnizationLogin(Orgnization orgnizer) {
		// TODO Auto-generated method stub
		return orgRepo.isOrgnizationLogin(orgnizer);
	}

	
	  public OrgDashboardCountDTO getDashboardCounts(int orgId) {
	        OrgDashboardCountDTO dto = new OrgDashboardCountDTO();
	        dto.setUpcomingEventCount(orgRepo.getUpcomingEventCount(orgId));
	        dto.setAlumniAttendanceCount(orgRepo.getAlumniAttendanceCount(orgId));
	        return dto;
	    }
}
