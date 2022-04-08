package mx.uin.mail.sender.service.reader;

import mx.uin.mail.sender.model.MessageData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class TextDataReader implements DataReader {
  @Override
  public List<MessageData> create() {
    // 1. direcciones csv
    // 2. machote el del body
    InputStream dataEmails = getFileFromResourceAsStream("data-emails.csv");
    InputStream machote = getFileFromResourceAsStream("machote.txt");

    List<String> dataEmailsLines = convertoToLines(dataEmails);
    List<String> machoteLines = convertoToLines(machote);

    //Crear los obejetos MessageData en base a las lineas extraidas de los archivos

    return null;
  }

  private List<String> convertoToLines(InputStream dataEmails) {
    List<String> lines = new ArrayList<>();

    try (InputStreamReader streamReader =
           new InputStreamReader(dataEmails, StandardCharsets.UTF_8);
         BufferedReader reader = new BufferedReader(streamReader)) {

      String line;
      while ((line = reader.readLine()) != null) {
        lines.add(line);
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
    return lines;
  }

  private InputStream getFileFromResourceAsStream(String fileName) {

    // The class loader that loaded the class
    ClassLoader classLoader = getClass().getClassLoader();
    InputStream inputStream = classLoader.getResourceAsStream(fileName);

    // the stream holding the file content
    if (inputStream == null) {
      throw new IllegalArgumentException("file not found! " + fileName);
    } else {
      return inputStream;
    }

  }

}
