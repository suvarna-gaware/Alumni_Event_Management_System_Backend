package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Alumni;
import com.example.demo.model.Department;
import com.example.demo.repository.DepartmentRepositorry;

@Service("DeptService")
public class DepartmentService {
@Autowired
private DepartmentRepositorry DeptRepo;

public boolean isAddDepartment(Department department) {
	return DeptRepo.isAddDepartment(department);
}
public List<Department> getAllDepartments() {
	// TODO Auto-generated method stub
	return DeptRepo.getAllDepartments();
}

public boolean deleteDepartmentById(int id) {
	
	return DeptRepo.deleteDepartmentById(id);
}


public boolean isUpdate(Department department) {
    return DeptRepo.isUpdate(department);
}
public Department getDepartmentById(Integer did) {
	
	return DeptRepo.getDepartmentById(did);

}

}
