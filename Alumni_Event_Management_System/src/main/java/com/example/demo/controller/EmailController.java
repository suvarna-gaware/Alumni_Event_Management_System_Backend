package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.AlumniDTO;
import com.example.demo.model.EmailRequest;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/email")
public class EmailController {

	@Autowired
	private JavaMailSender mailSender;

	@PostMapping("/send-alumni")
	public String sendEmailsToAlumni(@RequestBody EmailRequest request) {
		List<AlumniDTO> alumniList = request.getAlumniList();

		for (AlumniDTO alumni : alumniList) {
			try {
				SimpleMailMessage message = new SimpleMailMessage();
				message.setTo(alumni.getEmail());
				message.setSubject("Alumni Event Notification");
				message.setText("Dear " + alumni.getName() + ",\n\nYou have a new event scheduled in your department."
						+ " Please check the alumni portal for more details."
						+ "\n\nBest Regards,\nAlumni Event Management Team");

				mailSender.send(message);
			} catch (Exception e) {
				return "Failed to send email to " + alumni.getEmail() + ": " + e.getMessage();
			}
		}

		return "Emails sent successfully";
	}
}
