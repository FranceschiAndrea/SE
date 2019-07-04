//Use custom->goal->"eec:java" to run the server and then u can run test
//by using only the test comman or u can builde and automatically 
//tests would be runned
package com.anfropippo.test_1_webservice_exam;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.xml.bind.annotation.adapters.XmlAdapter;


public class StudentMapAdapter extends XmlAdapter<StudentMap, Map<Integer, Student>> {

    //The marshal method trasform a bound type into a value type that
    //can be handle by JAXB
    public StudentMap marshal(Map<Integer, Student> boundMap) throws Exception {
        StudentMap valueMap = new StudentMap();
        for (Map.Entry<Integer, Student> boundEntry : boundMap.entrySet()) {
            StudentMap.StudentEntry valueEntry  = new StudentMap.StudentEntry();
            valueEntry.setStudent(boundEntry.getValue());
            valueEntry.setId(boundEntry.getKey());
            valueMap.getEntries().add(valueEntry);
        }
        return valueMap;
    }
    
    public Map<Integer, Student> unmarshal(StudentMap valueMap) throws Exception {
        Map<Integer, Student> boundMap = new LinkedHashMap<Integer, Student>();
        for (StudentMap.StudentEntry studentEntry : valueMap.getEntries()) {
            boundMap.put(studentEntry.getId(), studentEntry.getStudent());
        }
        return boundMap;
    }
    
}
