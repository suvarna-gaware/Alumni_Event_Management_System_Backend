package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Exception.FeedbackNotFoundException;
import com.example.demo.model.Feedback;
import com.example.demo.service.FeedbackService;

@RestController
@RequestMapping()
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

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        if (!list.isEmpty()) {
            return list;
        } else {
            throw new FeedbackNotFoundException("There are no feedback in the database");
        }
    }





    }

