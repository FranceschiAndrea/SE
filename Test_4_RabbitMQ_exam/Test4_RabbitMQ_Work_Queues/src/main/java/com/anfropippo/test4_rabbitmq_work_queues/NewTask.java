package com.anfropippo.test4_rabbitmq_work_queues;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class NewTask {
    private final static String QUEUE_NAME = "hello";
    public static void main(String[] argv) throws Exception {
        
        //Create a connection with the server
        ConnectionFactory cf = new ConnectionFactory();
        cf.setHost("localhost"); //Localhost or ip address
        try (Connection conn = cf.newConnection();
             Channel channel = conn.createChannel()) {
            
            //Create a queue and send a message to that queue
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            //This part allow us to send message from the command line
            String message = String.join(" .", argv);
            //the first parameter is the name of the exchange
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }
        
    }
}
