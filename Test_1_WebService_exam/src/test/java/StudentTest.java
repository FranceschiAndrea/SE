//Use custom->goal->"eec:java" to run the server and then u can run test
//by using only the test comman or u can builde and automatically 
//tests would be runned


import com.anfropippo.test_1_webservice_exam.Baeldung;
import com.anfropippo.test_1_webservice_exam.BaeldungImpl;
import com.anfropippo.test_1_webservice_exam.Student;
import com.anfropippo.test_1_webservice_exam.StudentImpl;
import com.anfropippo.test_1_webservice_exam.Vote;
import com.anfropippo.test_1_webservice_exam.VoteImpl;
import java.util.Map;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;
import static junit.framework.TestCase.assertEquals;
import org.junit.Before;
import org.junit.Test;


public class StudentTest {
    
    //Fildes declaration for the test class.
    //QName in defined by Namespace URI and a localpart, if PORT_NAME 
    //argument, of the QName type, is omitted, Apache CXF assume as 
    //namespace uri the package name of the endpoint interface in the reverse
    //order and the it's logical part is the interface name appended by Port
    private static QName SERVICE_NAME 
      = new QName("http://test_1_sebservice.anfropippo.com/", "Baeldung");
    private static QName PORT_NAME 
      = new QName("http://test_1_sebservice.anfropippo.com/", "BaeldungPort");
 
    private Service service;
    private Baeldung baeldungProxy;
    private BaeldungImpl baeldungImpl;
    
    //Initiazer block to initiate the service field of the 
    //javax.ws.Service before running any test
    {
        service = Service.create(SERVICE_NAME);
        final String endpointAddress = "http://localhost:8080/baeldung";
        service.addPort(PORT_NAME, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);       
    }
    
    //@Before is used to run this function before every test
    @Before
    public void reinstantiateBaeldungInstances() {
        baeldungImpl = new BaeldungImpl();
        baeldungProxy = service.getPort(PORT_NAME, Baeldung.class);
    }
    
    @Test
    public void whenUsingHelloMethod_thenCorrect() {
        final String endpointResponse = baeldungProxy.hello("Baeldung");
        final String localResponse = baeldungImpl.hello("Baeldung");
        System.out.println(endpointResponse);
        assertEquals(localResponse, endpointResponse);
    }

    @Test
    public void whenUsingEchoMethod_thenCorrect() {
        final String endpointResponse = baeldungProxy.echo("Prova nuova funzione");
        final String localResponse = baeldungImpl.echo("Prova nuova funzione");
        System.out.println(endpointResponse);
        assertEquals(localResponse, endpointResponse);
    }
    
    @Test
    public void whenUsingHelloStudentMethod_thenCorrect() {
        final Student student = new StudentImpl("John Doe", 22);
        final String endpointResponse = baeldungProxy.helloStudent(student);
        final String localResponse = baeldungImpl.helloStudent(student);
        System.out.println(endpointResponse);
        assertEquals(localResponse, endpointResponse);
    }

    @Test
    public void usingGetStudentsMethod_thenCorrect() {
        final Student student1 = new StudentImpl("Adam", 21);
        baeldungProxy.helloStudent(student1);

        final Student student2 = new StudentImpl("Eve", 19);
        baeldungProxy.helloStudent(student2);

        final Map<Integer, Student> students = baeldungProxy.getStudents();

        
        assertEquals("Adam", students.get(1).getName());
        assertEquals(21, students.get(1).getAge());
        assertEquals("Eve", students.get(2).getName());
        assertEquals(19, students.get(2).getAge());
        
    }
    
    @Test
    public void usingStudentAverage() {
        final Student student1 = new StudentImpl("Adam", 21);
        baeldungProxy.helloStudent(student1);
        
        final Vote vote1 = new VoteImpl("Story", 7);
        baeldungProxy.newVote(1, vote1);
        final Vote vote2 = new VoteImpl("Lenguage", 7);
        baeldungProxy.newVote(1, vote2);

        final Student student2 = new StudentImpl("Eve", 19);
        baeldungProxy.helloStudent(student2);
        
        final Vote vote3 = new VoteImpl("Story", 10);
        baeldungProxy.newVote(2, vote3);
        final Vote vote4 = new VoteImpl("Lenguage", 8);
        baeldungProxy.newVote(2, vote4);
        final int averageStudent1 = baeldungProxy.studentAverage(1);
        final int averageStudent2 = baeldungProxy.studentAverage(2);
        System.out.println("Test: "+ averageStudent1 + " / " + averageStudent2);

        
        assertEquals(7, averageStudent1);
        assertEquals(9, averageStudent2);
        
    }
    
}
