package com.example.demo.repository;

import com.example.demo.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet; 
import java.sql.SQLException;

@Repository
public class AdminRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    static String username="admin";
    static String password="admin123";

  	public boolean login(String username, String password) {
		if(this.username.equals(username)&& this.password.equals(password))
  			return true;
  		else
  				return false;
	}
}
