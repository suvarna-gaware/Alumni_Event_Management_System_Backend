package com.example.demo.controller;

import java.util.*; 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Exception.AlumniNotFoundException;
import com.example.demo.Exception.DepartmentNotFoundException;
import com.example.demo.model.Alumni;
import com.example.demo.model.Department;
import com.example.demo.service.DepartmentService;

@RestController
public class DepartmentController {

    @Autowired 
    private DepartmentService DeptService;

    	@PostMapping("/createDepartment")
    	public String createDepartment(@RequestBody Department department) {
        System.out.println("inside createdepartment controller");
        System.out.println(department);
        boolean b = DeptService.isAddDepartment(department);
        return b ? "Department Added" : "Department not Added";
    }

    @GetMapping("/getDeparment") 
    public Department getDepartment() {
        return new Department(1, "CSE");
    }



	@GetMapping("/searchDepartmentiById/{Did}")
	public Department SearchDepartmenById(@PathVariable("Did") Integer Did) {
		
		
		Department dept= DeptService.getDepartmentById(Did);
		
		if (dept != null) {
			return dept;
		} else {
			throw new DepartmentNotFoundException("Department not found using ID: " + Did);
		}
	}
	@PutMapping("/updateDepartment")
	public String isupdateDepartment(@RequestBody  Department department) {
		System.out.println(department);
		boolean b=DeptService.isUpdate(department);
		if(b) {
			return "Department Record update with id"+department;
		}
		else {
			throw new DepartmentNotFoundException("Department not Found using id"+department.getDeptname());
			
		}
		
		
	}

}


