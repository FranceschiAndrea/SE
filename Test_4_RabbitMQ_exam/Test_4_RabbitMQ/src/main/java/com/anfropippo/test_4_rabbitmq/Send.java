//To start rabbitmq server use "sudo rabbitmq-server start" from the shell
//to stop it use Ctrl+C, for the exec of send and recv use profile setted
//in the pom file
package com.anfropippo.test_4_rabbitmq;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class Send {
    private final static String QUEUE_NAME = "hello";
    public static void main(String[] argv) throws Exception {
        
        //Create a connection with the server
        ConnectionFactory cf = new ConnectionFactory();
        cf.setHost("localhost"); //Localhost or ip address
        try (Connection conn = cf.newConnection();
             Channel channel = conn.createChannel()) {
            
            //Create a queue and send a message to that queue
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "Dio Si!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }
        
    }
}
