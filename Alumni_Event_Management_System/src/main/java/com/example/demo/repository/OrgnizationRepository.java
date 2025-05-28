package com.example.demo.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Orgnization;
@Repository("orgRepo")
public class OrgnizationRepository {
	
	List<Orgnization> list;
	
	@Autowired 
	private JdbcTemplate jdbcTemplate;

	public boolean isAddNewOrg(Orgnization org) {
		System.out.println("repo:"+org);
		int val = jdbcTemplate.update("INSERT INTO organization (Did, org_name, org_email, org_contact) VALUES (?, ?, ?, ?)",new PreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setInt(1, org.getDeptid());
						ps.setString(2, org.getOrgname());
						ps.setString(3, org.getOrgemail());
						ps.setString(4,org.getOrgcontact());
					}
			
		});
		return val>0?true:false;
	}

	public List<Orgnization> getAllorg() {
		list=jdbcTemplate.query("select*from organization", new RowMapper<Orgnization>(){

			@Override
			public Orgnization mapRow(ResultSet rs, int rowNum) throws SQLException {
				Orgnization org=new Orgnization();
				org.setOrgid(rs.getInt(1));
				org.setDeptid(rs.getInt(2));
				org.setOrgname(rs.getString(3));
				org.setOrgemail(rs.getString(4));
				org.setOrgcontact(rs.getString(5));
				
				return org;
			}
			
		});
		
		return list;
	}

	public List<Orgnization> getOrgByName(String name) {
		List<Orgnization> list=jdbcTemplate.query("select*from organization where trim( org_name) like ?",new Object[] {"%"+name.trim()+"%"},new RowMapper<Orgnization>() {

			@Override
			public Orgnization mapRow(ResultSet rs, int rowNum) throws SQLException {
				Orgnization org=new Orgnization();
				org.setOrgid(rs.getInt(1));
				org.setDeptid(rs.getInt(2));
				org.setOrgname(rs.getString(3));
				org.setOrgemail(rs.getString(4));
				org.setOrgcontact(rs.getString(5));
				return org;
			}
			
		});
		return list;
	}




	public boolean isUpdateorg(Orgnization org) {
		
		String sql = "UPDATE organization SET Did=?, org_name=?, org_email=?, org_contact=? WHERE org_id=?";
		int row = jdbcTemplate.update(sql, new PreparedStatementSetter() {
		    @Override
		    public void setValues(PreparedStatement ps) throws SQLException {
		        ps.setInt(1, org.getDeptid());
		        ps.setString(2, org.getOrgname());
		        ps.setString(3, org.getOrgemail());
		        ps.setString(4, org.getOrgcontact());
		        ps.setInt(5, org.getOrgid());
		    }
		});
		return row > 0?true:false;
	}

	public boolean isDeleteOrg(int id) {
		int val=jdbcTemplate.update("delete from  organization where org_id=?",new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, id);
				
			}
			
		});
		
		
		return val>0;
	}

	public Orgnization isOrgnizationLogin(Orgnization orgnizer) {
		
		String sql="select*from organization  where org_email=? and org_contact=?";
		
		try {
			return jdbcTemplate.queryForObject(sql, new Object[] {orgnizer.getOrgemail(),orgnizer.getOrgcontact()},new RowMapper<Orgnization>() {

				@Override
				public Orgnization mapRow(ResultSet rs, int rowNum) throws SQLException {
				
					Orgnization org=new Orgnization();
					
					org.setOrgid(rs.getInt(1));
					org.setDeptid(rs.getInt(2));
					org.setOrgname(rs.getString(3));
					org.setOrgemail(rs.getString(4));
					org.setOrgcontact(rs.getString(5));
					
					return org;
				}
				
			});
		}
	  catch(EmptyResultDataAccessException e) {
		  return null;
	  }
		
		
	}

	 public int getUpcomingEventCount(int orgId) {
	        String sql = """
	            SELECT COUNT(*) 
	            FROM event e
	            JOIN organization o ON e.deptid = o.Did
	            WHERE o.org_id = ? AND e.eventdate >= ?
	        """;
	        return jdbcTemplate.queryForObject(sql, Integer.class, orgId, LocalDate.now());
	    }
	 
	 public int getAlumniAttendanceCount(int orgId) {
	        String sql = """
	            SELECT COUNT(*)
	            FROM eventattendance ea
	            JOIN event e ON ea.Eid = e.eventid
	            JOIN organization o ON e.deptid = o.Did
	            WHERE o.org_id = ?
	        """;
	        return jdbcTemplate.queryForObject(sql, Integer.class, orgId);
	    }

}

