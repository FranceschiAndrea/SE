package com.anfropippo.test_1_webservice_exam;


import java.util.Map;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;



public class Client {

    private static QName SERVICE_NAME 
      = new QName("http://test_1_sebservice.anfropippo.com/", "Baeldung");
    private static QName PORT_NAME 
      = new QName("http://test_1_sebservice.anfropippo.com/", "BaeldungPort");
    
    public static void main(String args[]) throws Exception {
        
        Service service;
        Baeldung baeldungProxy;
        BaeldungImpl baeldungImpl;
        
        service = Service.create(SERVICE_NAME);
        final String endpointAddress = "http://localhost:8080/baeldung";
        service.addPort(PORT_NAME, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);
        
        baeldungImpl = new BaeldungImpl();
        baeldungProxy = service.getPort(PORT_NAME, Baeldung.class);
        
        Student student1 = new StudentImpl("Adam");
        baeldungProxy.helloStudent(student1);

        Student student2 = new StudentImpl("Eve");
        baeldungProxy.helloStudent(student2);

        Map<Integer, Student> students = baeldungProxy.getStudents();
        
        System.out.println(students);
        
    }
    
}