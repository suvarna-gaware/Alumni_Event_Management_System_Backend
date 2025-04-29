package com.example.demo.controller;

import java.util.*; 
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
import com.example.demo.Exception.DepartmentNotFoundException;
import com.example.demo.model.Alumni;
import com.example.demo.model.Department;
import com.example.demo.service.DepartmentService;

@RestController
@CrossOrigin(origins="*")
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

    	@GetMapping("/getDepartments")	
    	public List<Department> getAllDepartments() {
    	    List<Department> list = DeptService.getAllDepartments();
    	    list.forEach((l)->System.out.println(l));
    	    if (list.size() != 0) {
    	        return list;
    	    } else {
    	        throw new DepartmentNotFoundException("There are no departments in the database");
    	    }
    	}




    	@GetMapping("/searchDepartmentByName/{deptname}")
    	public List<Department> searchDepartmentByName(@PathVariable("deptname") String deptname) {
    	    List<Department> departments = DeptService.searchDepartmentsByName(deptname);
    	    
    	    if (departments != null && !departments.isEmpty()) {
    	        return departments;
    	    } else {
    	        throw new DepartmentNotFoundException("Department not found with name: " + deptname);
    	    }
    	}

	 @DeleteMapping("/deleteDepartment/{id}")
	    public String deleteDepartment(@PathVariable int id) {
	        boolean isDeleted = DeptService.deleteDepartmentById(id);
	        if (isDeleted) {
	            return "Department deleted successfully.";
	        } else {
	            return "Department not found or already deleted.";
	        }
	    }
	
	 @PutMapping("/updateDepartment")
	    public String updateDepartment(@RequestBody Department department) {
	        System.out.println("Updating department: " + department);

	        boolean updated = DeptService.isUpdate(department);

	        if (updated) {
	            return "Department updated successfully with ID: " + department.getDeptid();
	        } else {
	            throw new DepartmentNotFoundException("Department not found with ID: " + department.getDeptid());
	        }
	    }

}


