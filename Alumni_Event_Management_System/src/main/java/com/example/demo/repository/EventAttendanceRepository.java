package com.example.demo.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.model.EventAttendance;

@Repository
public class EventAttendanceRepository {
	
	List<EventAttendance> list;

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public boolean isAddAttendance(EventAttendance eattendance) {
		
		
		System.out.println("===========> "+ eattendance);
		
		
		
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

	public List<EventAttendance> getAllAttendance() {
		list=jdbcTemplate.query("select * from eventattendance", new RowMapper<EventAttendance>() {

			@Override
			public EventAttendance mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				EventAttendance al=new EventAttendance();
				al.setEventid(rs.getInt(1));
				al.setAlumniid(rs.getInt(2));
				al.setDeptid(rs.getInt(3));
				al.setStatus(rs.getString(4));
				return al;
			}
			
		});
		
		return list;
	}

	public boolean isUpdateAttendance(EventAttendance attendance) {
	    int val = jdbcTemplate.update(
	        "UPDATE eventattendance SET Status = ? WHERE Alumni_id = ?",
	        new PreparedStatementSetter() {
	            @Override
	            public void setValues(PreparedStatement ps) throws SQLException {
	               
	                ps.setString(1, attendance.getStatus()); 
	                ps.setInt(2, attendance.getAlumniid());
	            }
	        }
	    );
	    return val > 0;
	}

	public boolean isDeleteAttendanceById(int id) {
		int val=jdbcTemplate.update("delete from eventattendance where  Eid = ?",new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1,id);
				
			}
			
		});
		return val>0;
		
		
	}

	public List<Map<String, Object>> getAttendance() {
		List<Map<String,Object>>list=jdbcTemplate.queryForList("select ea.Eid,e.eventname,a.alumni_name,d.deptname,ea.status from eventAttendance ea join alumni a on ea.Alumni_id=a.Alumni_id join department d on ea.Did=d.Did join  event e ON ea.Eid = e.eventid");
		
		return list;
	}

}
