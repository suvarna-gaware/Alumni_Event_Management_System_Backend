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
public Department getDepartmentById(Integer id) {
	return DeptRepo.getDepartmentById(id);

}
public boolean isUpdate(Department department) {
	return DeptRepo.isUpdate(department);
}
}
