package com.example.starter.business;

import java.util.Optional;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.example.starter.exception.BaseException;
import com.example.starter.exception.ChatException;
import com.example.starter.model.ChatMessage;
import com.example.starter.model.ChatMessageRequest;
import com.example.starter.util.SecurityUtil;

@Service
public class ChatBusiness {

  private final SimpMessagingTemplate simpMessagingTemplate;

  public ChatBusiness(SimpMessagingTemplate simpMessagingTemplate) {
    this.simpMessagingTemplate = simpMessagingTemplate;
  }

  public Void post(ChatMessageRequest request) throws BaseException {
    final String destination = "chat";

    Optional<String> opt = SecurityUtil.getCurrentUserId();
    if (opt.isEmpty()) {
      throw ChatException.accessDenied();
    }

    // Validate message
    // ......

    ChatMessage payload = new ChatMessage();
    payload.setFrom(opt.get());
    payload.setMessage(request.getMessage());

    simpMessagingTemplate.convertAndSend(destination, payload);
    return null;
  }
}
