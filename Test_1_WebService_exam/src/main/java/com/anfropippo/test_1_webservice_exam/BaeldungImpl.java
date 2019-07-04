//Use custom->goal->"eec:java" to run the server and then u can run test
//by using only the test comman or u can builde and automatically 
//tests would be runned
package com.anfropippo.test_1_webservice_exam;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import javax.jws.WebService;

//Point to an interface defining an abstract contract for the web service
//All methods declared in the endpoint interface should be implemented
//but is not required to implement the intreface
@WebService(endpointInterface = "com.anfropippo.test_1_webservice_exam.Baeldung")

public class BaeldungImpl implements Baeldung {
    
    private Map<Integer, Student> students = new LinkedHashMap<Integer, Student>();
    public Map<Integer, LinkedList<Vote>> votes = new LinkedHashMap<Integer, LinkedList<Vote>>();
    
    public String hello(String name) {
        return "Hello" + name;
    }
    
    public String echo(String message) {
        return "Echo: " + message;
    }
    
    public String helloStudent(Student student) {
        students.put(students.size()+1, student);
        return "Hello" + student.getName();
    }
    
    public Map<Integer, Student> getStudents() {
        return students;
    }
    
    public Map<Integer, LinkedList<Vote>> getVotes() {
        return votes;
    }
    
    public String newVote(int student, Vote vote){
        if(votes.get(student) == null){
            LinkedList list = new LinkedList<Vote>();
            list.add(vote);
            votes.put(student, list);
        }
        else{
            votes.get(student).add(vote);
        }
        return students.get(student).getName() + " have a new vote in " + vote.getMatter() + " of " + vote.getVote();
        
    }
    
    public int studentAverage (int studentNumber){
        int average = 0;
        LinkedList<Vote> votess = votes.get(studentNumber);
        for(int i=0; i<(votess.size()); i++){
           average += votess.get(i).getVote();
        }
        average = average/(votess.size());
        return average;
    }
    
}
