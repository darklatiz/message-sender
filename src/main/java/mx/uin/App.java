package mx.uin;

import mx.uin.mail.sender.service.reader.DataReader;
import mx.uin.mail.sender.service.reader.TextDataReader;

public class App {
  public static void main(String[] args) {
    DataReader textDataReader = new TextDataReader();
    textDataReader.create(); // lee archivos

    //leer informacion

//    loop
//      emailSenderService.send();


    System.out.println("Hello World!");
  }
}
