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

import com.example.demo.model.Department;
import com.example.demo.model.Feedback;
import com.example.demo.model.FeedbackDTO;

@Repository
public class FeedbackRepository {
    List<Feedback> list;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean isAddNewFeedback(Feedback feedback) {
        int val = jdbcTemplate.update(
            "INSERT INTO feedback (Alumni_id, Eventid, feedbackmsg) VALUES (?, ?, ?)",
            new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps) throws SQLException {
                	
                	  ps.setInt(1,feedback.getAlumniId());
                	  ps.setInt(2, feedback.getEventId());
                	  ps.setString(3,feedback.getFeedbackmsg());
//                    ps.setInt(1, feedback.getFeedbackid());
//                    ps.setString(2, feedback.getEventname());
//                    ps.setString(3, feedback.getAlumniemail());
//                    ps.setString(4, feedback.getFeedbackmsg());
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
            	f.setAlumniId(rs.getInt(2));
            	f.setEventId(rs.getInt(3));
            	f.setFeedbackmsg(rs.getString(4));
            	
                return f;
            }

        });
        return list;    
    }
    
    public List<Map<String, Object>> getEventsByAlumniId(int alumniId) {
        String sql = "SELECT e.eventid, e.eventname FROM event e JOIN alumni a ON e.deptid = a.Did WHERE a.Alumni_id = ?";
        return jdbcTemplate.queryForList(sql, alumniId);
    }


    public List<FeedbackDTO> getAllFeedbackDetails() {
        String sql = """
            SELECT 
                f.Fid,
                a.alumni_email,
                e.eventname,
                e.eventdate,
                f.feedbackmsg
            FROM 
                feedback f
            JOIN 
                alumni a ON f.Alumni_id = a.Alumni_id
            JOIN 
                event e ON f.Eventid = e.eventid
        """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> mapToFeedbackDTO(rs));
    }
    private FeedbackDTO mapToFeedbackDTO(ResultSet rs) throws SQLException {
        FeedbackDTO dto = new FeedbackDTO();
        dto.setFid(rs.getInt("Fid"));
        dto.setAlumniEmail(rs.getString("alumni_email"));
        dto.setEventName(rs.getString("eventname"));
        dto.setEventDate(rs.getString("eventdate"));
        dto.setFeedbackMsg(rs.getString("feedbackmsg"));
        return dto;
    }




    
    
    

    
    
}
