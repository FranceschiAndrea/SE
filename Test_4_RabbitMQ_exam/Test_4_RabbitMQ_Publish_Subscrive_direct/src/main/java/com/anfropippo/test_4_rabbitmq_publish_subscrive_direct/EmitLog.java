package com.anfropippo.test_4_rabbitmq_publish_subscrive_direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class EmitLog {

    private static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] argv) throws Exception {
      ConnectionFactory factory = new ConnectionFactory();
      factory.setHost("localhost");
      try (Connection connection = factory.newConnection();
           Channel channel = connection.createChannel()) {
          channel.exchangeDeclare(EXCHANGE_NAME, "direct");

          String severity = getSeverity(argv);
          String message = getMessage(argv);

          channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes("UTF-8"));
          System.out.println(" [x] Sent '" + severity + "':'" + message + "'");
      }
    }
    
    //se nell'argv non c'è niente ritorna semplicemente la stringa info perche è quello che dovrebbe fare 
    private static String getSeverity(String[] strings) {
        if (strings.length < 1)
            return "info";
        return strings[0];
    }

    //se c'è almeno un argomento in  argv usa questa funzione per leggere il testo in argv[1] oppure scrive hello world
    private static String getMessage(String[] strings) {
        if (strings.length < 2)
            return "Hello World!";
        return joinStrings(strings, " ", 1);
    }

    private static String joinStrings(String[] strings, String delimiter, int startIndex) {
        int length = strings.length;
        if (length == 0) return "";
        if (length <= startIndex) return "";
        StringBuilder words = new StringBuilder(strings[startIndex]);
        for (int i = startIndex + 1; i < length; i++) {
            words.append(delimiter).append(strings[i]);
        }
        return words.toString();
    }
    
}
