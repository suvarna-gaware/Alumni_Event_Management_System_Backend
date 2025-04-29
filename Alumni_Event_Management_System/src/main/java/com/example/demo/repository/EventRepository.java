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

import com.example.demo.model.Event;

@Repository("eventRepo")
public class EventRepository {
	List<Event> list;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public boolean isAddNewEvent(Event event) {
		int val = jdbcTemplate.update(
				"INSERT INTO event (deptid, eventname, eventdate, eventtime, location) VALUES (?, ?, ?, ?, ?)",
				new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setInt(1, event.getDeptid());
						ps.setString(2, event.getEventname());
						ps.setString(3, event.getEventdate());
						ps.setString(4, event.getEventtime());
						ps.setString(5, event.getLocation());
					}
				});
		return val > 0;
	}

	
	public List<Event> getAllEvent() {
		list = jdbcTemplate.query("SELECT * FROM event", new RowMapper<Event>() {
			@Override
			public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
				Event e = new Event();
				e.setEventid(rs.getInt("eventid"));
				e.setDeptid(rs.getInt("deptid"));
				e.setEventname(rs.getString("eventname"));
				e.setEventdate(rs.getString("eventdate"));
				e.setEventtime(rs.getString("eventtime"));
				e.setLocation(rs.getString("location"));
				return e;
			}
		});
		return list;
	}

	
	public Event getEventById(Integer id) {
		List<Event> list = jdbcTemplate.query("SELECT * FROM event WHERE eventid = ?", new Object[] { id },
				new RowMapper<Event>() {
					@Override
					public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
						Event e = new Event();
						e.setEventid(rs.getInt("eventid"));
						e.setDeptid(rs.getInt("deptid"));
						e.setEventname(rs.getString("eventname"));
						e.setEventdate(rs.getString("eventdate"));
						e.setEventtime(rs.getString("eventtime"));
						e.setLocation(rs.getString("location"));
						return e;
					}
				});
		return list.size() > 0 ? list.get(0) : null;
	}

	// Update an event
	public boolean isUpdate(Event event) {
		String sql = "UPDATE event SET deptid=?, eventname=?, eventdate=?, eventtime=?, location=? WHERE eventid=?";
		int rows = jdbcTemplate.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, event.getDeptid());
				ps.setString(2, event.getEventname());
				ps.setString(3, event.getEventdate());
				ps.setString(4, event.getEventtime());
				ps.setString(5, event.getLocation());
				ps.setInt(6, event.getEventid());
			}
		});
		return rows > 0;
	}

	
	public boolean deleteEventById(int id) {
		int val = jdbcTemplate.update("DELETE FROM event WHERE eventid = ?", new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, id); 
			}
		});
		return val > 0;
	}

	public List<Event> getEventByName(String eventname) {
		 System.out.println("Searching Alumni by Name in repo ===> " + eventname);

		
		List<Event> list = jdbcTemplate.query("SELECT * FROM event WHERE TRIM(eventname) LIKE ?",
				new Object[] { "%" + eventname.trim() + "%" }, new RowMapper<Event>() {
					@Override
					public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
						Event e = new Event();
						e.setEventid(rs.getInt("eventid"));
						e.setDeptid(rs.getInt("deptid"));
						e.setEventname(rs.getString("eventname"));
						e.setEventdate(rs.getString("eventdate")); 
						e.setEventtime(rs.getString("eventtime")); 
						e.setLocation(rs.getString("location"));
						return e;
					}
				});
		return list;
	}

	public List<Map<String, Object>> getEvents(){
		List<Map<String, Object>> list = jdbcTemplate.queryForList("select e.eventid, e.eventname, e.eventdate, e.eventtime, e.location, d.deptname from event e join department d on e.deptid = d.did");
    	return list;
    }
}
