package mx.uin.mail.sender.service;

import mx.uin.mail.sender.model.MessageData;

import java.util.List;

public interface SenderService {
  int send(MessageData messageData);
}
