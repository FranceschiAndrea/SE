//Use custom->goal->"eec:java" to run the server and then u can run test
//by using only the test comman or u can builde and automatically 
//tests would be runned
package com.anfropippo.test_1_webservice_exam;

import java.util.LinkedList;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Student")

public class StudentImpl implements Student{
    
    private String name;
    private int age;

    public StudentImpl(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    StudentImpl(){} //????
    
    public StudentImpl(String name) {
        this.name = name;
    }
    
    public StudentImpl(int age){
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return name +"" + age ;
    }
    
}
