package com.example.starter.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.starter.business.ChatBusiness;
import com.example.starter.exception.BaseException;
import com.example.starter.model.ChatMessageRequest;

@RestController
@RequestMapping("/chat")
public class ChatApi {

  private final ChatBusiness chatBusiness;

  public ChatApi(ChatBusiness chatBusiness) {
    this.chatBusiness = chatBusiness;
  }

  @PostMapping("/message")
  public ResponseEntity<Void> post(@RequestBody ChatMessageRequest request) throws BaseException {
    chatBusiness.post(request);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

}
