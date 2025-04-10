package com.example.demo.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Alumni;
import com.example.demo.model.Department;

@Repository("DeptRepo")
public class DepartmentRepositorry {
	
    @Autowired
    JdbcTemplate jdbcTemplate;
    List<Department>list;
    public boolean isAddDepartment(Department department) {
        int value = jdbcTemplate.update("INSERT INTO department VALUES ('0',?)", new PreparedStatementSetter() {
           
            
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, department.getDeptname());
            }
        });

        return value > 0;
    }
    public List<Department>getDeptname(){
    	list=jdbcTemplate.query("select*from Department",new RowMapper<Department>() {
            @Override
            public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
                Department department = new Department();
                department.setDid(rs.getInt(1)); 
                department.setDeptname(rs.getString(2));
                return department;
            }
        });
        return list;
    }

    public Department getDepartmentById(Integer Did) {
        
        List<Department> list = jdbcTemplate.query(
        		"select * from Department where Did = ?",
            new Object[] { Did },
            new RowMapper<Department>() {
                @Override
                public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Department dept = new Department();
                    dept.setDid(rs.getInt(1));
                    dept.setDeptname(rs.getString(2));
                    return dept;
                }
            }
        );

        return list.isEmpty() ? null : list.get(0);
    }


    public boolean isUpdate(Department department) {
    	
        int value = jdbcTemplate.update("update department set deptname = ? where Did = ?;", new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                               ps.setString(1, department.getDeptname());   
                               ps.setInt(2, department.getDid()); 

            }
        });
        return value > 0 ? true : false;
    }

}