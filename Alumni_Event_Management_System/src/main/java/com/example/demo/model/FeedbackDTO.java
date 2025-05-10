package com.example.demo.model;

import lombok.Data;

@Data
public class FeedbackDTO {
	
	private int fid;
    private String alumniEmail;
    private String eventName;
    private String eventDate;
    private String feedbackMsg;

}
