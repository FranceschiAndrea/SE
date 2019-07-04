//Use custom->goal->"eec:java" to run the server and then u can run test
//by using only the test comman or u can builde and automatically 
//tests would be runned
package com.anfropippo.test_1_webservice_exam;

import javax.xml.ws.Endpoint;


public class Server {
    public static void main(String args[]) throws InterruptedException {
        BaeldungImpl implementor = new BaeldungImpl();
        String address = "http://localhost:8080/baeldung";
        Endpoint.publish(address, implementor);
        System.out.println("Server started");
        Thread.sleep(60 * 1000); 
        System.out.println("Server stopped");       
        System.exit(0);
    }
}
