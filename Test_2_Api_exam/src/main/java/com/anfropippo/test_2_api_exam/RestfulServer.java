package com.anfropippo.test_2_api_exam;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;


public class RestfulServer {

    public static void main(String args[]) throws Exception {
        
        //Instantiate a JAXRSServerFactoryBean object and set the root
        //resource class
        JAXRSServerFactoryBean factoryBean = new JAXRSServerFactoryBean();
        factoryBean.setResourceClasses(CourseRepository.class);
        factoryBean.setResourceProvider(new SingletonResourceProvider(new CourseRepository()));
        
        //Indicate the URL where the web service is published
        factoryBean.setAddress("http://localhost:8080/");
        
        //the factoryBean can be used to create a new server that will 
        //start listening for incoming connections
        Server server = factoryBean.create();

        System.out.println("Server ready...");
        Thread.sleep(60 * 1000);
        System.out.println("Server exiting");
        server.destroy();
        System.exit(0);
    }
    
}
