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

import com.example.demo.model.Orgnization;
@Repository("orgRepo")
public class OrgnizationRepository {
	
	List<Orgnization> list;
	
	@Autowired 
	private JdbcTemplate jdbcTemplate;

	public boolean isAddNewOrg(Orgnization org) {
		int val = jdbcTemplate.update("insert into organization values ('0',?, ?, ?, ?)",new PreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setInt(1, org.getDid());
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
				org.setDid(rs.getInt(2));
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
				org.setDid(rs.getInt(2));
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
		        ps.setInt(1, org.getDid());
		        ps.setString(2, org.getOrgname());
		        ps.setString(3, org.getOrgemail());
		        ps.setString(4, org.getOrgcontact());
		        ps.setInt(5, org.getOrgid());
		    }
		});
		return row > 0?true:false;
	}

	

}
