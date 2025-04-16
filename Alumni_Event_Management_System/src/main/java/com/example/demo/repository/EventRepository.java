package com.example.demo.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Alumni;
import com.example.demo.model.Event;

@Repository("eventRepo")
public class EventRepository {
	List<Event> list;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public boolean isAddNewEvent(Event event) {
		int val = jdbcTemplate.update(
				"INSERT INTO event (org_id, ename, Dates, Description, venue) VALUES (?, ?, ?, ?, ?)",
				new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setInt(1, event.getOrgid());
						ps.setString(2, event.getEname());
						ps.setDate(3, java.sql.Date.valueOf(event.getDates()));
						ps.setString(4, event.getDescription());
						ps.setString(5, event.getVenue());
					}
				}
			);
			return val > 0;


}
	
	public List<Event> getAllEvent() {
		list=jdbcTemplate.query("select*from event",new RowMapper<Event>() {

			@Override
			public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
				Event e = new Event();
				e.setEid(rs.getInt(1));                      
				e.setOrgid(rs.getInt(2));                
				e.setEname(rs.getString(3));
   				e.setDates(rs.getDate(4).toLocalDate()); 
				e.setDescription(rs.getString(5));  
				e.setVenue(rs.getString(6));        

				return e;
			}});
		
		return list;
	}
	public Event getEventById(Integer id) {
	    List<Event> list = jdbcTemplate.query(
	        "SELECT * FROM event WHERE Eid = ?",
	        new Object[] { id },
	        new RowMapper<Event>() {
	            @Override
	            public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
	                Event e = new Event();
	                e.setEid(rs.getInt(1));
	                e.setOrgid(rs.getInt(2));
	                e.setEname(rs.getString(3));                
	                e.setDates(rs.getDate(4).toLocalDate());    
	                e.setDescription(rs.getString(5));
	                e.setVenue(rs.getString(6));
	                return e;
	            }
	        }
	    );

	    return list.size() > 0 ? list.get(0) : null;
	}


	 public boolean isUpdate(Event event) {
	        String sql = "UPDATE event SET org_id=?, ename=?, Dates=?, Description=?, venue=? WHERE Eid=?";
	        int rows = jdbcTemplate.update(sql, new PreparedStatementSetter() {
	            @Override
	            public void setValues(PreparedStatement ps) throws SQLException {
	                ps.setInt(1, event.getOrgid());
	                ps.setString(2, event.getEname());
	                ps.setDate(3, java.sql.Date.valueOf(event.getDates()));
	                ps.setString(4, event.getDescription());
	                ps.setString(5, event.getVenue());
	                ps.setInt(6, event.getEid());
	            }
	        });
	        return rows > 0;
	    }

	 public boolean deleteEventById(int id) {
		    int val = jdbcTemplate.update("DELETE FROM event WHERE Eid= ?", new PreparedStatementSetter() {
		        @Override
		        public void setValues(PreparedStatement ps) throws SQLException {
		            ps.setInt(1, id);
		        }
		    });
		    return val > 0;
		}

	 public Event getEventByName(String ename) {
		    List<Event> list = jdbcTemplate.query("SELECT * FROM event WHERE TRIM(ename) like ?", 
		        new Object[] { "%" + ename.trim()+"%" },
		        new RowMapper<Event>() {
		            @Override
		            public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
		                Event e = new Event();
		                e.setEid(rs.getInt(1));
		                e.setOrgid(rs.getInt(2));
		                e.setEname(rs.getString(3));
		                e.setDates(rs.getDate(4).toLocalDate());
		                e.setDescription(rs.getString(5));
		                e.setVenue(rs.getString(6));
		                return e;
		            }
		        }
		    );

		    return list.isEmpty() ? null : list.get(0); 
		}


}
