package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.ResourceBuilder.FeedBackResourceBuilder;
import com.example.entities.FeedBackForm;
import com.example.repository.FeedBackFormRepository;
import com.example.resources.FeedBackFormResource;


@RestController
@RequestMapping(value="/feedbackform")
public class FeedBackFormController {

	@Autowired
	FeedBackFormRepository feedBackFormRepository;
	@Autowired
	FeedBackResourceBuilder resourceBuilder;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<FeedBackFormResource>> getFeedBackForms(){
		List<FeedBackFormResource> resource = resourceBuilder.toResources(feedBackFormRepository.findAll());
		return new ResponseEntity<List<FeedBackFormResource>>(resource,HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<FeedBackFormResource>  saveFormData(RequestEntity<FeedBackForm> feedBackForm){
		FeedBackForm savedEntity = feedBackFormRepository.save(feedBackForm.getBody());
		FeedBackFormResource  resource = resourceBuilder.toResource(savedEntity);
		return new ResponseEntity<FeedBackFormResource>(resource, HttpStatus.CREATED);
		
	}
	
}
