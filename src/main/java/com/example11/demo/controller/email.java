package com.example11.demo.controller;

import com.example11.demo.model.MailSender;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.IOException;

@RestController
public class email {

    @RequestMapping(value = "/sendemail")
    public String sendEmail() throws AddressException, MessagingException, IOException, IOException {
        MailSender.sendmail();
        return "Email sent successfully";
    }}