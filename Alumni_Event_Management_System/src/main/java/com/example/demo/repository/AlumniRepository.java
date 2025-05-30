package com.example.demo.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Alumni;
import com.example.demo.model.AlumniDTO;

@Repository("alumniRepo")
public class AlumniRepository {
	List<Alumni> list;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public boolean isAddNewAlumni(Alumni alumni) {
		int val = jdbcTemplate.update("insert into alumni values('0',?,?,?,?,?,?,?,?)", new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) {
				System.out.println(alumni);
				try {
					ps.setInt(1, alumni.getDeptid());
					ps.setString(2, alumni.getName());
					ps.setString(3, alumni.getGender());
					ps.setInt(4, alumni.getYear());
					ps.setString(5, alumni.getAddress());
					ps.setString(6, alumni.getEmail());
					ps.setString(7, alumni.getContact());
					ps.setString(8,alumni.getStatus());
					

				} catch (Exception e) {
					System.out.println(e);
				}

			}
		});

		return val > 0 ? true : false;
	}

	public List<Alumni> getAllAlumni() {
		list = jdbcTemplate.query("select*from Alumni", new RowMapper<Alumni>() {

			@Override
			public Alumni mapRow(ResultSet rs, int rowNum) throws SQLException {
				Alumni al = new Alumni();
				al.setAlumniid(rs.getInt(1));
				al.setDeptid(rs.getInt(2));
				al.setName(rs.getString(3));
				al.setGender(rs.getString(4));
				al.setYear(rs.getInt(5));
				al.setAddress(rs.getString(6));
				al.setEmail(rs.getString(7));
				al.setContact(rs.getString(8));
				al.setStatus(rs.getString(9));
				
				return al;
			}

		});
		return list;

	}

//	public Alumni getAlumniById(Integer id) {
//
//		list = jdbcTemplate.query("select*from Alumni where Alumni_id=?", new Object[] { id }, new RowMapper<Alumni>() {
//
//			@Override
//			public Alumni mapRow(ResultSet rs, int rowNum) throws SQLException {
//				Alumni al = new Alumni();
//				al.setAlumniid(rs.getInt(1));
//				al.setDid(rs.getInt(2));
//				al.setName(rs.getString(3));
//				al.setGender(rs.getString(4));
//				al.setYear(rs.getInt(5));
//				al.setAddress(rs.getString(6));
//				al.setEmail(rs.getString(7));
//				al.setContact(rs.getString(8));
//				return al;
//			}
//
//		});
//		return list.size() > 0 ? list.get(0) : null;
//
//	}

	public boolean isUpdate(Alumni alumni) {
	    int value = jdbcTemplate.update(
	        "update Alumni set Did=?, alumni_name=?, gender=?, passout_year=?, address=?, alumni_email=?, contact=?, status=? where Alumni_id=?",
	        new PreparedStatementSetter() {

	            @Override
	            public void setValues(PreparedStatement ps) throws SQLException {
	                ps.setInt(1, alumni.getDeptid());
	                ps.setString(2, alumni.getName());
	                ps.setString(3, alumni.getGender());
	                ps.setInt(4, alumni.getYear());
	                ps.setString(5, alumni.getAddress());
	                ps.setString(6, alumni.getEmail());
	                ps.setString(7, alumni.getContact());
	                ps.setString(8, alumni.getStatus());  
	                ps.setInt(9, alumni.getAlumniid());    
	            }
	        }
	    );
	    return value > 0;
	}

	public List<Alumni> getAlumniByName(String name) {
	    System.out.println("Searching Alumni by Name in repo ===> " + name);

	    
	    List<Alumni> list = jdbcTemplate.query(
	        "SELECT * FROM alumni WHERE TRIM(alumni_name) LIKE ?", 
	        new Object[] {"%" + name.trim() + "%"},
	        new RowMapper<Alumni>() {
	            @Override
	            public Alumni mapRow(ResultSet rs, int rowNum) throws SQLException {
	                Alumni a = new Alumni();
	                a.setAlumniid(rs.getInt(1));
	                a.setDeptid(rs.getInt(2));
	                a.setName(rs.getString(3));
	                a.setGender(rs.getString(4));
	                a.setYear(rs.getInt(5));
	                a.setAddress(rs.getString(6));
	                a.setEmail(rs.getString(7));
	                a.setContact(rs.getString(8));
	                a.setStatus(rs.getString(9));

	                return a;
	            }
	        });

	    return list; 
	}


	public boolean deleteAlumniById(int id) {
		int val=jdbcTemplate.update("delete from alumni where  Alumni_id=?", new PreparedStatementSetter(){

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, id);
				
			}
			
		});
		return val>0;
	}

	public List<Alumni> getAlumniByIdd(Integer aid) {
	    System.out.println("REPO:" + aid);
	    String query = "SELECT * FROM alumni WHERE Alumni_id = ?";
	    
	    List<Alumni> result = jdbcTemplate.query(query, new Object[] {aid}, new RowMapper<Alumni>() {
	        @Override
	        public Alumni mapRow(ResultSet rs, int rowNum) throws SQLException {
	            Alumni a = new Alumni();
	            a.setAlumniid(rs.getInt(1));
	            a.setDeptid(rs.getInt(2));
	            a.setName(rs.getString(3));
	            a.setGender(rs.getString(4));
	            a.setYear(rs.getInt(5));
	            a.setAddress(rs.getString(6));
	            a.setEmail(rs.getString(7));
	            a.setContact(rs.getString(8));
	            a.setStatus(rs.getString(9));
	            return a;
	        }
	    });
	    
	    return result;  
	}

	//-------------------------------------------------------------
	
	public Alumni iseAlumniLogin(Alumni alumni) {
	    String sql = "SELECT * FROM alumni WHERE alumni_email = ? AND contact = ?";

	    try {
	        return jdbcTemplate.queryForObject(sql, new Object[]{alumni.getEmail(), alumni.getContact()}, new RowMapper<Alumni>() {
	            @Override
	            public Alumni mapRow(ResultSet rs, int rowNum) throws SQLException {
	                Alumni a = new Alumni();
	                a.setAlumniid(rs.getInt("Alumni_id"));
	                a.setDeptid(rs.getInt("Did"));
	                a.setName(rs.getString("alumni_name"));
	                a.setGender(rs.getString("gender"));
	                a.setYear(rs.getInt("passout_year"));
	                a.setAddress(rs.getString("address"));
	                a.setEmail(rs.getString("alumni_email"));
	                a.setContact(rs.getString("contact"));
	                a.setStatus(rs.getString("status"));
	                return a;
	            }
	        });
	    } catch (EmptyResultDataAccessException e) {
	        return null; 
	    }
	}
//------------------------------------------
	
	 public List<AlumniDTO> getAlumniByDepartment(int deptid) {
	        String sql = "SELECT Alumni_id, alumni_name, alumni_email FROM alumni WHERE Did = ?";
	        return jdbcTemplate.query(sql, new Object[] {deptid}, new RowMapper<AlumniDTO>() {
	            @Override
	            public AlumniDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
	                AlumniDTO dto = new AlumniDTO();
	                dto.setAlumniId(rs.getInt("Alumni_id"));
	                dto.setName(rs.getString("alumni_name"));
	                dto.setEmail(rs.getString("alumni_email"));
	                
	                return dto;
	            }
	        });
	    }

}
