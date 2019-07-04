//Use custom->goal->"eec:java" to run the server and then u can run test
//by using only the test comman or u can builde and automatically 
//tests would be runned
package com.anfropippo.test_1_webservice_exam;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "StudentMap")

public class StudentMap {

    private List<StudentEntry> entries = new ArrayList<StudentEntry>();
    
    @XmlElement(nillable = false, name = "entry")
    public List<StudentEntry> getEntries() {
        return entries;
    }
 
    @XmlType(name = "StudentEntry")
    public static class StudentEntry {
        private Integer id;
        private Student student;

        public Integer getId() {
            return id;
        }

        public Student getStudent() {
            return student;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public void setStudent(Student student) {
            this.student = student;
        }
        
    }
}
