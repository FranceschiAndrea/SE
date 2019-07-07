package com.anfropippo.test_2_api_exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Course")

public class Course {
    
    private int id;
    private String name;
    private List<Student> students = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Course{" + "id=" + id + ", name=" + name + ", students=" + students + '}' + "\n";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.id;
        hash = 41 * hash + Objects.hashCode(this.name);
        hash = 41 * hash + Objects.hashCode(this.students);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Course other = (Course) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    
    private Student findById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }
    
    //Implementation of some REST API
    //That was different from the approach in a typical Spring project
    //where the API operation are defined in a controller   
    @GET
    @Path("{studentId}")
    public Student getStudent(@PathParam("studentId")int studentId) {
        return findById(studentId);
    }
    
    //this return 200 OK response if the create operation was sucessfull or 409 Conflict otherwise
    @POST
    //We can omit this cause is empty
    @Path("")
    public Response createStudent(Student student) {
        for (Student element : students) {
            if (element.getId() == student.getId()) {
                return Response.status(Response.Status.CONFLICT).build();
            }
        }
        students.add(student);
        System.out.println(students);
        return Response.ok(student).build();
    }

    @DELETE
    @Path("{studentId}")
    public Response deleteStudent(@PathParam("studentId") int studentId) {
        Student student = findById(studentId);
        if (student == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        students.remove(student);
        return Response.ok().build();
    }
    
    
    
}
