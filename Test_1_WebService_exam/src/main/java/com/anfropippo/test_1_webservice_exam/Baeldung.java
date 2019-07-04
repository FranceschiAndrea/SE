//Use custom->goal->"eec:java" to run the server and then u can run test
//by using only the test comman or u can builde and automatically 
//tests would be runned

//This interface is optional you cannot use it
package com.anfropippo.test_1_webservice_exam;

import java.util.LinkedList;
import java.util.Map;
import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@WebService
public interface Baeldung {
    public String hello(String name);
 
    public String helloStudent(Student student);
    
    public String echo(String message);
    
    public int studentAverage(int number);
    
    public String newVote(int student, Vote vote);
    
    @XmlJavaTypeAdapter(VoteMapAdapter.class)
    public Map<Integer, LinkedList<Vote>> getVotes();
 
    //Apache CXF use JAXB but JAXB not directly support binding of a MAP
    //returned from getStudents, so we need an adapter to convert the MAP
    //in to a Java class that JAXB can use
    @XmlJavaTypeAdapter(StudentMapAdapter.class)
    public Map<Integer, Student> getStudents();
}
