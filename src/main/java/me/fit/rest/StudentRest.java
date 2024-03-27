package me.fit.rest;

import java.util.List;

import org.jboss.resteasy.reactive.RestResponse.Status;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import me.fit.exception.StudentException;
import me.fit.model.Student;
import me.fit.service.StudentService;

@Path("/api/student/")
public class StudentRest {

	@Inject
	private StudentService studentService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/createStudent")
	public Response createStudent(Student student){
		Student s = null;
		try {
			s = studentService.createStudent(student);
		} catch (StudentException e) {
			return Response.status(Status.CONFLICT).entity(e.getMessage()).build();
		}
		return Response.ok().entity(s).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAllStudents")
	public Response getAllStudents() {
		List<Student> students =  studentService.getAllStudents();
		return Response.ok().entity(students).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getStudentsByName")
	public Response getStudentsByName(@QueryParam(value = "name") String name) {
		
		System.out.println("NAME QUERY PARAMETER: " + name);
		List<Student> students =  studentService.getStudentsByName(name);
		return Response.ok().entity(students).build();
	}

	

}
