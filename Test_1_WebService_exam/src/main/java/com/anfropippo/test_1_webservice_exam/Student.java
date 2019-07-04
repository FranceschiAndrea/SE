//Use custom->goal->"eec:java" to run the server and then u can run test
//by using only the test comman or u can builde and automatically 
//tests would be runned
package com.anfropippo.test_1_webservice_exam;

import java.util.LinkedList;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlJavaTypeAdapter(StudentAdapter.class)

public interface Student {
    
    public String getName();
    public int getAge();
    
}
