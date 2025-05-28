package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class AdminDashboardController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/counts")
    public Map<String, Long> getDashboardCounts() {
        Map<String, Long> counts = new HashMap<>();

        String alumniSql = "SELECT COUNT(*) FROM alumni";
        String upcomingEventSql = "SELECT COUNT(*) FROM event WHERE eventdate >= CURDATE()";
        String deptSql = "SELECT COUNT(*) FROM department";
        String orgSql = "SELECT COUNT(*) FROM organization";
        String attendanceSql = "SELECT COUNT(*) FROM eventattendance WHERE status = 'present'";

        counts.put("alumniCount", jdbcTemplate.queryForObject(alumniSql, Long.class));
        counts.put("upcomingEventCount", jdbcTemplate.queryForObject(upcomingEventSql, Long.class));
        counts.put("departmentCount", jdbcTemplate.queryForObject(deptSql, Long.class));
        counts.put("organizerCount", jdbcTemplate.queryForObject(orgSql, Long.class));
        counts.put("attendanceCount", jdbcTemplate.queryForObject(attendanceSql, Long.class));

        return counts;
    }
}

