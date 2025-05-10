
package com.example.demo.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Exception.FeedbackNotFoundException;
import com.example.demo.model.Feedback;
import com.example.demo.model.FeedbackDTO;
import com.example.demo.service.FeedbackService;

@RestController
@RequestMapping("/feedback") // security
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

   @GetMapping("/events/byAlumniId/{alumniId}")
   public List<Map<String, Object>> getEventsByAlumniId(@PathVariable Integer alumniId) {
	   System.out.print("=========="+alumniId);
	   
	   List<Map<String, Object>> list=feedbackService.getEventsByAlumniId(alumniId);
	   
	   System.err.println("--> "+list);
	   return list;
       //return feedbackService.getEventsByAlumniId(alumniId);
   }

   @GetMapping("/with-details")
   public List<FeedbackDTO> getFeedbackWithDetails() {
	   System.out.println("Fetching feedback with details...");
	   
       return feedbackService.getAllFeedbackDetails();
   }


    }

