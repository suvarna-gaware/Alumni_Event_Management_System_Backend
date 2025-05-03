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

import com.example.demo.model.Department;
import com.example.demo.model.Feedback;

@Repository
public class FeedbackRepository {
    List<Feedback> list;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean isAddNewFeedback(Feedback feedback) {
        int val = jdbcTemplate.update(
            "INSERT INTO feedback(fid, feedback_msg, ename, alumni_email) VALUES (?, ?, ?, ?)",
            new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps) throws SQLException {
                    ps.setInt(1, feedback.getFid());
                    ps.setString(2, feedback.getFeedbackmsg());
                    ps.setString(3, feedback.getEname());
                    ps.setString(4, feedback.getAlumniemail());
                }
            });
        return val > 0;
    }

    
    public List<Feedback> getAllFeedback() {
        List<Feedback> list = jdbcTemplate.query("SELECT * FROM Feedback", new RowMapper<Feedback>() {

            @Override
            public Feedback mapRow(ResultSet rs, int rowNum) throws SQLException {
            	Feedback f = new Feedback();
                f.setFid(rs.getInt(1));         
                f.setFeedbackmsg(rs.getString(2));
                f.setEname(rs.getString(3));
                f.setAlumniemail(rs.getString(4));
                return f;
            }

        });
        return list;
    }
    

    
    
}
