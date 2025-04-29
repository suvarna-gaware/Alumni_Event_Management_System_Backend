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
    public List<Department> getAllDepartments() {
        List<Department> list = jdbcTemplate.query("SELECT * FROM Department", new RowMapper<Department>() {

            @Override
            public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
                Department dept = new Department();
                dept.setDeptid(rs.getInt(1));         
                dept.setDeptname(rs.getString(2));
                return dept;
            }

        });
        return list;
    }

    public List<Department> searchDepartmentsByName(String deptname) {
        
    	System.out.println("name : "+deptname);
        String sql = "SELECT * FROM Department WHERE TRIM(deptname) like ?";
        String mod = "%";
        String like = mod + deptname.trim() + mod;
        return jdbcTemplate.query(
            sql,
            new Object[] {like },  
            (rs, rowNum) -> {
                Department dept = new Department();
                dept.setDeptid(rs.getInt(1));
                dept.setDeptname(rs.getString(2));
                return dept;
            }
        );
    }


    
    public boolean deleteDepartmentById(int id) {
		int value = jdbcTemplate.update("DELETE FROM department WHERE Did = ?", new PreparedStatementSetter() {
	        @Override
	        public void setValues(PreparedStatement ps) throws SQLException {
	            ps.setInt(1, id);
	        }
	    });
	    return value > 0;
	
		
		
	}
	


    public boolean isUpdate(Department department) {
        String sql = "UPDATE department SET deptname = ? WHERE Did = ?";

        int rows = jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, department.getDeptname());
                ps.setInt(2, department.getDeptid());
            }
        });

        return rows > 0?true:false;
    }

}