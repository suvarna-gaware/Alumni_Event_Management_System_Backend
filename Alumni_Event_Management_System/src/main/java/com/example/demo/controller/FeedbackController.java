package com.example.demo.controller;


import com.example.demo.Exception.DepartmentNotFoundException;
import com.example.demo.model.Department;
import com.example.demo.model.Feedback;

import com.example.demo.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class FeedbackController {

    @Autowired
	FeedbackService feedbackService;

  

    @PostMapping("/createFeedback")
    public String createFeedback(@RequestBody Feedback feedback) {
        System.out.println("Feedback Data is: " + feedback);
        boolean b = feedbackService.isAddNewFeedback(feedback);
        System.out.println("Description: " + feedback.getFeedbackmsg());
        return b ? "Feedback Added" : "Feedback Not Added";
    }
    @GetMapping("/getFeedback")	
	public List<Feedback> getAllFeedback() {
	    List<Feedback> list = feedbackService.getAllFeedback();
	    list.forEach((l)->System.out.println(l));
	    if (list.size() != 0) {
	        return list;
	    } else {
	        throw new DepartmentNotFoundException("There are no feedback in the database");
	    }
	}




    }

