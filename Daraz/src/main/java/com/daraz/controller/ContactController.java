package com.daraz.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.daraz.obj.Contact;
import com.daraz.obj.MailBody;
import com.daraz.service.EmailSenderService;

@RestController
public class ContactController {
	
	@Autowired
	private EmailSenderService emailSenderService;

	@CrossOrigin
	@PostMapping("/contact")
	public String contact (@RequestBody Contact mailBody) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo("proda5.order@gmail.com");
		mailMessage.setSubject(mailBody.getMessege());
		mailMessage.setFrom("nonlovesme@gmail.com");
		mailMessage.setText(mailBody.getMessege() + " Messege Sent By "+ mailBody.getName() + "  email: "+ mailBody.getEmail());
		emailSenderService.sendEmail(mailMessage);
		return "success";
	}
}
