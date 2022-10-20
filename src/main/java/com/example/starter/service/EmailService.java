package com.example.starter.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

  @Value("${app.email.from}")
  private String from;

  private final JavaMailSender mailSender;

  public EmailService(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  public void send(String to, String subject, String html) {
    MimeMessagePreparator messege = mimeMessage -> {
      MimeMessageHelper helpder = new MimeMessageHelper(mimeMessage, true, "UTF-8");
      helpder.setFrom(from);
      helpder.setTo(to);
      helpder.setSubject(subject);
      helpder.setText(html, true);
    };
    mailSender.send(messege);
  }

}
