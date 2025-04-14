package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoginRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean isAlumniValid(String email, String contact) {
        String sql = "SELECT COUNT(*) FROM Alumni WHERE alumni_email = ? AND contact = ?";
        @SuppressWarnings("deprecation")
		Integer count = jdbcTemplate.queryForObject(sql, new Object[]{email, contact}, Integer.class);
        return count != null && count > 0;
    }

    public boolean isOrganizationValid(String email, String contact) {
        String sql = "SELECT COUNT(*) FROM Organization WHERE org_email = ? AND org_contact = ?";
        @SuppressWarnings("deprecation")
		Integer count = jdbcTemplate.queryForObject(sql, new Object[]{email, contact}, Integer.class);
        return count != null && count > 0;
    }
}
