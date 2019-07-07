package com.anfropippo.test_2_api_exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import javax.xml.bind.JAXB;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;


public class RestfullLiveTest {
    
    private static String BASE_URL = "http://localhost:8080/baeldung/courses/";
    private static CloseableHttpClient client;
    
    //Before running test we create a client used to comunicate with
    //the server
    @BeforeClass
    public static void createClient() {
        client = HttpClients.createDefault();
    }
    
    //And destroyed afterward
    @AfterClass
    public static void closeClient() throws IOException {
        client.close();
    }
    
    //Method to GET a Course instance giving its id
    private Course getCourse(int courseOrder) throws IOException {
        URL url = new URL(BASE_URL + courseOrder);
        InputStream input = url.openStream();
        Course course
          = JAXB.unmarshal(new InputStreamReader(input), Course.class);
        return course;
    }
    
    //Method to GET a Student instance giving the course id and student in
    //the resource
    private Student getStudent(int courseOrder, int studentOrder) throws IOException {
          URL url = new URL(BASE_URL + courseOrder + "/students/" + studentOrder);
          InputStream input = url.openStream();
          Student student
            = JAXB.unmarshal(new InputStreamReader(input), Student.class);
          return student;
    }
    
    //For this post we use a Student object unmarshaled from the
    //conflict_student.xml file located in the class path that contain
    //a Student with id=2 and name=Studente B
    @Test
    public void whenCreateConflictStudent_thenReceiveConflictResponse() throws IOException {
        final HttpPost httpPost = new HttpPost(BASE_URL + "1/students");
        final InputStream resourceStream = this.getClass().getClassLoader()
          .getResourceAsStream("conflict_student.xml");
        System.out.println("ECCOLOOOOOO:" + this.getClass().getClassLoader().getResourceAsStream("conflict_stuent.xml"));
        httpPost.setEntity(new InputStreamEntity(resourceStream));
        
        //The Content-Type header is set to tell the server that the 
        //content type of the request is XML
        httpPost.setHeader("Content-Type", "text/xml");
        
        //Since the uploaded Student object already existent in the Course
        //instance we expect that the creation fails with Conflict(409)
        final HttpResponse response = client.execute(httpPost);
        assertEquals(409, response.getStatusLine().getStatusCode());
    }
    
    //For this post we use a Student object unmarshaled from the
    //created_student.xml file located in the class path that contain
    //a Student with id=3 and name=Studente C
    @Test
    public void whenCreateValidStudent_thenReceiveOKResponse() throws IOException {
        final HttpPost httpPost = new HttpPost(BASE_URL + "2/students");
        final InputStream resourceStream = this.getClass().getClassLoader()
          .getResourceAsStream("created_student.xml");
        httpPost.setEntity(new InputStreamEntity(resourceStream));
        httpPost.setHeader("Content-Type", "text/xml");
        
        //In this case the Student object does not exist in the Course 
        //istance and should be succefully created
        final HttpResponse response = client.execute(httpPost);
        assertEquals(200, response.getStatusLine().getStatusCode());
        
        //We may confirm the new states of the web service resource
        final Student student = getStudent(2, 3);
        assertEquals(3, student.getId());
        assertEquals("Student C", student.getName());
    }
    
    @Test
    public void whenUpdateNonExistentCourse_thenReceiveNotFoundResponse() throws IOException {
        final HttpPut httpPut = new HttpPut(BASE_URL + "3");
        final InputStream resourceStream = this.getClass().getClassLoader().getResourceAsStream("non_existent_course.xml");
        httpPut.setEntity(new InputStreamEntity(resourceStream));
        httpPut.setHeader("Content-Type", "text/xml");

        final HttpResponse response = client.execute(httpPut);
        assertEquals(404, response.getStatusLine().getStatusCode());
    }

    @Test
    public void whenUpdateUnchangedCourse_thenReceiveNotModifiedResponse() throws IOException {
        final HttpPut httpPut = new HttpPut(BASE_URL + "1");
        final InputStream resourceStream = this.getClass().getClassLoader().getResourceAsStream("unchanged_course.xml");
        httpPut.setEntity(new InputStreamEntity(resourceStream));
        httpPut.setHeader("Content-Type", "text/xml");
        final HttpResponse response = client.execute(httpPut);
        assertEquals(304, response.getStatusLine().getStatusCode());
    }
    
    @Test
    public void whenUpdateValidCourse_thenReceiveOKResponse() throws IOException {
        final HttpPut httpPut = new HttpPut(BASE_URL + "2");
        final InputStream resourceStream = this.getClass().getClassLoader().getResourceAsStream("changed_course.xml");
        httpPut.setEntity(new InputStreamEntity(resourceStream));
        httpPut.setHeader("Content-Type", "text/xml");

        final HttpResponse response = client.execute(httpPut);
        assertEquals(200, response.getStatusLine().getStatusCode());

        final Course course = getCourse(2);
        assertEquals(2, course.getId());
        assertEquals("Apache CXF Support for RESTful", course.getName());
    }
    
    @Test
    public void whenDeleteInvalidStudent_thenReceiveNotFoundResponse() throws IOException {
        final HttpDelete httpDelete = new HttpDelete(BASE_URL + "1/students/3");
        final HttpResponse response = client.execute(httpDelete);
        assertEquals(404, response.getStatusLine().getStatusCode());
    }
    
    @Test
    public void whenDeleteValidStudent_thenReceiveOKResponse() throws IOException {
        final HttpDelete httpDelete = new HttpDelete(BASE_URL + "1/students/1");
        final HttpResponse response = client.execute(httpDelete);
        assertEquals(200, response.getStatusLine().getStatusCode());

        final Course course = getCourse(1);
        assertEquals(1, course.getStudents().size());
        assertEquals(2, course.getStudents().get(0).getId());
        assertEquals("Student B", course.getStudents().get(0).getName());
    }
    
    //Mio test riuscito dove prendo tutti i voti di uno studente sottoforma di string BUON PER VEDERE COME SI LEGGE DA UN INPUT STREAM
    @Test
    public void getStudentVotes() throws IOException {
          URL url = new URL(BASE_URL + "1/" + "1/" + "votes");
          InputStream input = url.openStream();
          System.out.println(input);
          
          //how to read from a input stream reader to have a string 
          String newLine = System.getProperty("\n\r");
          BufferedReader reader = new BufferedReader(new InputStreamReader(input));
          StringBuilder result = new StringBuilder();
          boolean flag = false;
          for (String line; (line = reader.readLine()) != null; ) {
              result.append(flag? newLine: "").append(line);
              flag = true;
          }
          String votes = result.toString();
          
          System.out.println(votes);
    }
    
    @Test
    public void getCourseObject() throws IOException {
          System.out.println(getCourse(1));
          
    }
    
    //this test is useful to miself to understand that unmarshal work on the classes created to me but not on the existent like List on LinkedList, for this classese
    //we shouldoverride the marshal and unmarshal like Test1_Web_Service
    @Test
    public void getVote() throws IOException {
          URL url = new URL(BASE_URL + "1/1/vote/1/object");
          InputStream input = url.openStream();
          Vote vote
            = JAXB.unmarshal(new InputStreamReader(input), Vote.class);
          
          System.out.println(vote);
          //from there we can use the assertEquals to doing th evarious test
    }
    
    //Questa da errore perche non riesce a fare l'unmarshal su Student la funzione di cui sopra
    /*@Test
    public void getStudentObject() throws IOException {
          System.out.println(getStudent(1, 1));
          
    }*/
    
}
