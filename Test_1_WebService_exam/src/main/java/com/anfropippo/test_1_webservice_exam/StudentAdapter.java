//Use custom->goal->"eec:java" to run the server and then u can run test
//by using only the test comman or u can builde and automatically 
//tests would be runned
package com.anfropippo.test_1_webservice_exam;

import javax.xml.bind.annotation.adapters.XmlAdapter;


public class StudentAdapter extends XmlAdapter<StudentImpl, Student> {

    //The marshal method trasform a bound type (Student,interface) into a 
    //value type (StudentImpl,concrete class) that can be handle by JAXB
    public StudentImpl marshal(Student student) throws Exception {
        
        if (student instanceof StudentImpl) {
            return (StudentImpl) student;
        }
        
        return new StudentImpl(student.getName(), student.getAge());
        
    }
    //Unmarshal do the reverse of marshal
    public Student unmarshal(StudentImpl student) throws Exception {
        return (Student)student;
    }
    
}
