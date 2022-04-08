package mx.uin.mail.sender.service.reader;

import mx.uin.mail.sender.model.MessageData;

import java.util.List;

public interface DataReader {
  List<MessageData> create();
}
