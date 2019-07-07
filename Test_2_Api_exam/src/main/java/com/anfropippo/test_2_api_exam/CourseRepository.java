package com.anfropippo.test_2_api_exam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

//The CourseRepository is the root resource here,
//so it’s mapped to handle all URLS starting with course.
@Path("baeldung")
//@Produces annotation is used to tell the server to convert 
//objects returned from methods within this class to XML documents 
//before sending them to clients.
@Produces("text/xml")

public class CourseRepository {

    private Map<Integer, Course> courses = new HashMap<>();
    
    private Course findById(int id) {
        for(Map.Entry<Integer, Course> course : courses.entrySet()) {
            if(course.getKey() == id) {
                return course.getValue();
            }
        }
        return null;
    }
    
    //For this example we populate some data using in-memory data instead 
    //of a full-fledged persistent solution
    {
        Student student1 = new Student();
        Student student2 = new Student();
        student1.setId(1);
        student1.setName("Student A");
        student2.setId(2);
        student2.setName("Student B");
        
        Vote vote1 = new Vote();
        vote1.setMatter("Mat");
        vote1.setVotation(7);
        List<Vote> votes = new ArrayList<>();
        votes.add(vote1);
        student1.setVotes(votes);
        
        List<Student> course1Students = new ArrayList<>();
        course1Students.add(student1);
        course1Students.add(student2);

        Course course1 = new Course();
        Course course2 = new Course();
        course1.setId(1);
        course1.setName("REST with Spring");
        course1.setStudents(course1Students);
        course2.setId(2);
        course2.setName("Learn Spring Security");

        courses.put(1, course1);
        courses.put(2, course2);
        
        System.out.println(courses);
    }
    
    @GET
    @Path("courses/{courseId}")
    public Course getCourse(@PathParam("courseId") int courseId) {
        return findById(courseId);
    }
    
    @POST
    @Path("courses/new")
    public Response updateCourse(Course course) {
        Course existingCourse = findById(course.getId());        
        if (existingCourse == null) {
            courses.put(course.getId(), course);
            return Response.ok().build();
        }
        else {
            return Response.notModified("Course already exist!").build();
        }
    }
    
    @PUT
    @Path("courses/{courseId}")
    public Response updateCourse(@PathParam("courseId") int courseId, Course course) {
        Course existingCourse = findById(courseId);        
        if (existingCourse == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        if (existingCourse.equals(course)) {
            return Response.notModified().build();    
        }
        courses.put(courseId, course);
        return Response.ok().build();
    }
    
    //the following "method" simple rename the path on the way of course, because all the method in corse are referred at the object course, but
    //the requests give only the course id so from the id we should kepp the object course from the list and then
    //we could use the method in the class course, otherwise we should pass an object course directli from the http request
    @Path("courses/{courseId}/students")
    public Course pathToStudent(@PathParam("courseId") int courseId) {
        return findById(courseId);
    }
    
    @GET
    @Path("courses/{courseId}/{studentId}/votes")
    public String getVotes(@PathParam("studentId") int studentId, @PathParam("courseId") int courseId) {
        return findById(courseId).getStudents().get(studentId-1).getVotes().toString();
    }
    
    @GET
    @Path("courses/{courseId}/{studentId}/vote/{voteNumber}/object")
    public Vote getVotesObject(@PathParam("studentId") int studentId, @PathParam("courseId") int courseId, @PathParam("voteNumber") int voteNumber) {
        System.out.println("HELLOOOOO!!");
        System.out.println(findById(courseId).getStudents().get(studentId-1).getVotes().get(voteNumber-1));
        return findById(courseId).getStudents().get(studentId-1).getVotes().get(voteNumber-1);
    }
    
}
