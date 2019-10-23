package com.daraz.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.daraz.obj.Contact_mail;
import com.daraz.repo.ContactMailRepo;

@Service
public class ContactMailService {

@Autowired
private ContactMailRepo contactMailRepo;
@Autowired
private EmailSenderService emailSenderService;

public void sendContactMail(Contact_mail contact_mail) {
	contactMailRepo.save(contact_mail);
	SimpleMailMessage mailMessage = new SimpleMailMessage();
	mailMessage.setTo("proda5.order@gmail.com");
	mailMessage.setSubject(contact_mail.getSubject());
	mailMessage.setFrom("nonlovesme@gmail.com");
	mailMessage.setText("Sender's name: "+contact_mail.getSenderName()+"."+"\n" +
	"E-mail: "+contact_mail.getMailAddress()+"\n"+contact_mail.getMessage());
	emailSenderService.sendEmail(mailMessage);
}
}
