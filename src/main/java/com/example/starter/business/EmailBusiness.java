package com.example.starter.business;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;

import com.example.starter.exception.BaseException;
import com.example.starter.exception.EmailException;
import com.example.starter.service.EmailService;

@Service
public class EmailBusiness {

  private final EmailService emailService;

  public EmailBusiness(EmailService emailService) {
    this.emailService = emailService;
  }

  public void sendActivateUserEmail(String email, String name, String token) throws BaseException {

    String html = null;
    try {
      html = readEmailTemplate("email-activate-user.html");
    } catch (IOException e) {
      throw EmailException.templateNotFound();
    }

    String finalLink = "http://localhost:4200/activate/" + token;
    html = html.replace("${P_NAME}", name);
    html = html.replace("${LINK}", finalLink);

    String subject = "Please activate your account";

    emailService.send(email, subject, html);
  }

  private String readEmailTemplate(String filename) throws IOException {
    File file = ResourceUtils.getFile("classpath:email/" + filename);
    return FileCopyUtils.copyToString(new FileReader(file));
  }

}
