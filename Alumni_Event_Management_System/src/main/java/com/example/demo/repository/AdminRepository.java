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

    public Admin login(String username, String password) {
        String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{username, password}, new AdminRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    private static class AdminRowMapper implements RowMapper<Admin> {
        @Override
        public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
            Admin admin = new Admin();
            admin.setAdminId(rs.getInt("Admin_id"));
            admin.setUsername(rs.getString("username"));
            admin.setPassword(rs.getString("password"));
            admin.setRole(rs.getString("Role"));
            admin.setAdminEmail(rs.getString("Admin_email"));
            admin.setContact(rs.getString("contact"));
            return admin;
        }
    }
}
