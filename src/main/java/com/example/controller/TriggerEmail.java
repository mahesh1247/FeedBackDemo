package com.example.controller;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.FeedBackForm;
import com.example.repository.FeedBackFormRepository;

@RestController
@RequestMapping(value="/triggerEmail")
public class TriggerEmail {
	
	@Autowired
	FeedBackFormRepository feedBackFormRepository;
	
	@Autowired
	JavaMailSender mailsender;
	
	@RequestMapping(value="/{userId}",method = RequestMethod.GET)
	public ResponseEntity triggerEmail(@PathVariable Integer userId){
		FeedBackForm form = feedBackFormRepository.findOne(userId);
		 MimeMessage mail = mailsender.createMimeMessage();
	        try {
	            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
	            helper.setTo(form.getEmail());
	            helper.setFrom("**********");
	            helper.setSubject("Acknowledgement for feedback");
	            helper.setText("we have recevied you feedback for the following comment :--"+form.getComments());
	        } catch (MessagingException e) {
	            e.printStackTrace();
	        } finally {}
	        mailsender.send(mail);
	        //return helper;
		return null;
		
	}
	
	}

