package com.anfropippo.test4_rabbitmq_work_queues;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Worker {

    private final static String QUEUE_NAME = "hello";
    public static void main(String[] argv) throws Exception {
        
        //Create a connection with the server
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        
        //We declare the queue in theconsumer too, because we should start
        //consumer before producer and we want to be shure that the queue 
        //exist
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        //Since sender will push the message asynchronously, we use a callback
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
            
            try{
                doWork(message);
            } catch (InterruptedException ex) {
                Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
            } finally{
                System.out.println(" [x] Done");
            }            
        };
        
        boolean autoAck = true;
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
        
      }
    
      private static void doWork(String task) throws InterruptedException {
          System.out.println("doing work ....");
          for(char ch: task.toCharArray()){
              if(ch == '.') Thread.sleep(10000);
          }
      }
    
}
