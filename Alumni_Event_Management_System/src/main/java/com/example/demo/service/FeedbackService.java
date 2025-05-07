package com.example.demo.service;

import com.example.demo.model.Department;
import com.example.demo.model.Feedback;
import com.example.demo.repository.FeedbackRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("feedbackService")
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepo;

	public boolean isAddNewFeedback(Feedback feedback) {
		return feedbackRepo.isAddNewFeedback(feedback);
	}

    public List<Feedback> getAllFeedback() {
	
	return feedbackRepo. getAllFeedback();
}


}
