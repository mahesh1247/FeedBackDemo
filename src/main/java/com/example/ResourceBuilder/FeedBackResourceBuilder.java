package com.example.ResourceBuilder;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;

import com.example.controller.FeedBackFormController;
import com.example.controller.TriggerEmail;
import com.example.entities.FeedBackForm;
import com.example.resources.FeedBackFormResource;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@Service
public class FeedBackResourceBuilder extends ResourceAssemblerSupport<FeedBackForm, FeedBackFormResource> {
	public FeedBackResourceBuilder() {
		  super(FeedBackFormController.class, FeedBackFormResource.class);
		 }
		 
		 public FeedBackFormResource toResource(FeedBackForm feedBackForm) {
		  FeedBackFormResource resource = instantiateResource(feedBackForm);
		  resource.feedBackForm = feedBackForm;
		        resource.add(linkTo(FeedBackFormController.class).slash(feedBackForm.getId()).withSelfRel());
					resource.add(linkTo(TriggerEmail.class).slash(feedBackForm.getId()).withRel("triggerMail"));
		  return resource;
		 }
}
