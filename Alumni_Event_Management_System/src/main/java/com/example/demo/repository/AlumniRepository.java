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

@Repository("alumniRepo")
public class AlumniRepository {
	List<Alumni> list;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public boolean isAddNewAlumni(Alumni alumni) {
		int val = jdbcTemplate.update("insert into alumni values('0',?,?,?,?,?,?,?)", new PreparedStatementSetter() {

			
			@Override
			public void setValues(PreparedStatement ps) {
				System.out.println(alumni);
				try {
					ps.setInt(1, alumni.getDid());
					ps.setString(2, alumni.getName());
					ps.setString(3, alumni.getGender());
					ps.setInt(4, alumni.getYear());
					ps.setString(5, alumni.getAddress()); 
					ps.setString(6, alumni.getEmail());
					ps.setString(7, alumni.getContact());
					

				}catch(Exception e) {
					System.out.println(e);
				}

			}
		});

		return val > 0 ? true : false;
	}
	
public List<Alumni>getAllAlumni(){
	list=jdbcTemplate.query("select*from Alumni",new RowMapper<Alumni>() {

		@Override
		public Alumni mapRow(ResultSet rs, int rowNum) throws SQLException {
			Alumni al=new Alumni();
			al.setAlumniid(rs.getInt(1));
			al.setDid(rs.getInt(2));
			al.setName(rs.getString(3));
			al.setGender(rs.getString(4));
			al.setYear(rs.getInt(5));
			al.setAddress(rs.getString(6));
			al.setEmail(rs.getString(7));			
			al.setContact(rs.getString(8));
			return al;
		}
		
	}
			);
	return list;
	
}
public Alumni getAlumniById(Integer id) {
	
	list=jdbcTemplate.query("select*from Alumni where Alumni_id=?", new Object[] {id},new RowMapper<Alumni>() {

		@Override
		public Alumni mapRow(ResultSet rs, int rowNum) throws SQLException {
		Alumni al=new Alumni();
		al.setAlumniid(rs.getInt(1));
		al.setDid(rs.getInt(2));
		al.setName(rs.getString(3));
		al.setGender(rs.getString(4));
		al.setYear(rs.getInt(5));
		al.setAddress(rs.getString(6));
		al.setEmail(rs.getString(7));
		al.setContact(rs.getString(8));
			return al;
		}
		
	});
	return list.size()>0?list.get(0):null;
	
	
}

public boolean isUpdate(Alumni alumni) {
	int value=jdbcTemplate.update("update Alumni set Did=?, alumni_name=?,gender=?, passout_year=?,address=?,alumni_email=?,contact=? where Alumni_id=?",new PreparedStatementSetter() {
		
		@Override
		public void setValues(PreparedStatement ps) throws SQLException {
			ps.setInt(1, alumni.getDid());          
            ps.setString(2, alumni.getName());      
            ps.setString(3, alumni.getGender());    
            ps.setInt(4, alumni.getYear());         
            ps.setString(5, alumni.getAddress());   
            ps.setString(6, alumni.getEmail());     
            ps.setString(7, alumni.getContact());   
            ps.setInt(8, alumni.getAlumniid()); 
		}
	});
	return value>0?true:false;
	
}
	}

