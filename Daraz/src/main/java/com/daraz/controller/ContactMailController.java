package com.daraz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.daraz.obj.Contact_mail;
import com.daraz.obj.MailBody;
import com.daraz.service.ContactMailService;

@RestController
public class ContactMailController {
  @Autowired	
  private ContactMailService contactMailService;	
	@CrossOrigin
	@PostMapping("/sendFeedback")
	public String takeOrder(@RequestPart("contactMail") Contact_mail contact_mail) {
		contactMailService.sendContactMail(contact_mail);
		return "Mail sent";
	}
}
