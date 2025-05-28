
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
import com.example.demo.model.EventDTO;

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
		System.out.println("repo"+event);
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

	public List<Map<String, Object>> getEvents() {
		List<Map<String, Object>> list = jdbcTemplate.queryForList(
				"select e.eventid, e.eventname, e.eventdate, e.eventtime, e.location, d.deptname from event e join department d on e.deptid = d.did");
		return list;
	}

	public List<Map<String, Object>> getEventsByAlumniId(int alumniId) {
		String sql = "SELECT e.eventid, e.eventname, e.location, e.eventdate, e.eventtime, e.deptid, d.deptname "
				+ "FROM event e " + "JOIN department d ON e.deptid = d.Did " + "JOIN alumni a ON a.Did = d.Did "
				+ "WHERE a.Alumni_id = ?";

		return jdbcTemplate.queryForList(sql, alumniId);
	}
	
	 public List<Event> getEventsByDepartmentName(String deptName) {
	        String sql = """
	            SELECT e.eventid, e.deptid, e.eventname, e.eventdate, e.eventtime, 
	                   e.location, d.deptname
	            FROM event e
	            JOIN department d ON e.deptid = d.Did
	            WHERE d.deptname = ?
	        """;

	        return jdbcTemplate.query(sql, new Object[]{deptName}, (rs, rowNum) -> {
	            Event event = new Event();
	            event.setEventid(rs.getInt("eventid"));
	            event.setDeptid(rs.getInt("deptid"));
	            event.setEventname(rs.getString("eventname"));
	            event.setEventdate(rs.getString("eventdate"));
	            event.setEventtime(rs.getString("eventtime"));
	            event.setLocation(rs.getString("location"));
	            event.setDeptname(rs.getString("deptname"));
	            return event;
	        });
	    }
	
	 
	 public void insertEvent(EventDTO dto) {
	        String sql = "INSERT INTO event (eventname, location, eventdate, eventtime, deptid) " +
	                     "VALUES (?, ?, ?, ?, ?)";
	        System.out.print(dto);	 
	        jdbcTemplate.update(sql,
	            dto.getEventname(),
	            dto.getLocation(),
	            dto.getEventdate(),
	            dto.getEventtime(),
	            dto.getDeptid()
	          
	        );
	    }
	 
	 
	 public List<EventDTO> getEventsByDeptId(int deptid) {
		    String sql = "SELECT eventid, eventname, location, eventdate, eventtime, deptid FROM event WHERE deptid = ?";

		    return jdbcTemplate.query(sql, new Object[]{deptid}, (rs, rowNum) -> {
		        EventDTO dto = new EventDTO();
		        dto.setEventid(rs.getInt("eventid"));        // you forgot this earlier
		        dto.setEventname(rs.getString("eventname"));
		        dto.setLocation(rs.getString("location"));
		        dto.setEventdate(rs.getString("eventdate"));
		        dto.setEventtime(rs.getString("eventtime"));
		        dto.setDeptid(rs.getInt("deptid"));
		        return dto;
		    });
		}



	
	 
	
}
