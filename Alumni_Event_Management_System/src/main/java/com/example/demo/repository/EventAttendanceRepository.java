package com.example.demo.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.example.demo.model.EventAttendance;

@Repository
public class EventAttendanceRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public boolean isAddAttendance(EventAttendance eattendance) {
		int val=jdbcTemplate.update("insert into eventattendance values(?,?,?,?)",new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, eattendance.getEventid());
				ps.setInt(2,eattendance.getAlumniid());
				ps.setInt(3,eattendance.getDeptid());
				ps.setString(4, eattendance.getStatus());
			}
			
		});
		return val>0;
	}

}
