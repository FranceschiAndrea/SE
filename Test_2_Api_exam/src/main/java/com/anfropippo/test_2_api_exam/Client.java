package com.anfropippo.test_2_api_exam;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;


public class Client {

    private static String BASE_URL = "http://localhost:8080/baeldung/courses/";
    private static CloseableHttpClient client;
    
    public static void main(String args[]) throws Exception {
        
        client = HttpClients.createDefault();
        
        int functioN = 2;
        
        if (functioN == 1){      
            Vote vote = getVote(1, 1, 1);
            System.out.println(vote);     
        }
        if (functioN == 2){
            String source = "new_course.xml";
            String response = addCourse(source);
            System.out.println(response);
        }
        if (functioN == 2){
            String source = "new_student.xml";
            String response = addStudent(source);
            System.out.println(response);
        }
        
        client.close();
        
    }
    
    private static Vote getVote(int courseId, int studentId, int voteId) throws IOException {
        
          URL url = new URL(BASE_URL + courseId + "/" + studentId +"/" + "vote/" + voteId + "/object");
          
          InputStream input = url.openStream();
          
          Vote vote = JAXB.unmarshal(new InputStreamReader(input), Vote.class);
          
          return vote;
          
    }
    
    private static String addCourse(String source) throws Exception {       
        
        HttpPost httpPost = new HttpPost(BASE_URL + "new");
        
        InputStream resourceStream = Client.class.getClassLoader().getResourceAsStream(source);
        
        httpPost.setEntity(new InputStreamEntity(resourceStream));
        
        httpPost.setHeader("Content-Type", "text/xml");
        
        HttpResponse response = client.execute(httpPost);
        
        return response.toString();
        
    }
    
     private static String addStudent(String source) throws Exception {       
        
        HttpPost httpPost = new HttpPost(BASE_URL + "4/students");
        
        InputStream resourceStream = Client.class.getClassLoader().getResourceAsStream(source);
        
        httpPost.setEntity(new InputStreamEntity(resourceStream));
        
        httpPost.setHeader("Content-Type", "text/xml");
        
        HttpResponse response = client.execute(httpPost);
        
        return response.toString();
        
    }
    
    
}