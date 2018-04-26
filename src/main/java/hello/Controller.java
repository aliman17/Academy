package hello;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.jsontype.impl.ClassNameIdResolver;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import model.Academy;
import model.Course;
import model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.UUID;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class Controller {
    
    @RequestMapping("/")
    public String index() {
        return "<b>Greetings from Spring Boot!</b>";
    }
    
    @RequestMapping("/classes")
    public String classes() {
    		Academy academy = Academy.getInstance();
		ArrayList<Course> courses = academy.getCourses();
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("<b>Available classes:</b>");
		for (Course course : courses) {
			buffer.append("</br>");
			buffer.append(course.toString());
		}
    		return buffer.toString();
    }
    
    @RequestMapping("/students")
    public String students() {
    		Academy academy = Academy.getInstance();
		ArrayList<Student> students = academy.getStudents();
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("<b>Students:</b>");
		for (Student student : students) {
			buffer.append("</br>");
			buffer.append(student.toString());
		}
    		return buffer.toString();
    }
    
    @RequestMapping(value = "student")
    public String getStudent(@RequestParam("id") UUID studentId) {
    		Academy academy = Academy.getInstance();
    		return academy.getStudentById(studentId).toString();
    }
    
    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String searchClasses(@RequestParam("pattern") String pattern) {
    		Academy academy = Academy.getInstance();
		ArrayList<Course> courses = academy.getCourses();
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("<b>Found classes:</b>");
    		for (Course course : courses) {
    			if (course.getName().toLowerCase().contains(pattern.toLowerCase())) {
    				buffer.append("</br>");
    				buffer.append(course.toString());
    			}
    		}
    		return buffer.toString();
    }
    
    @RequestMapping(value = "enroll")
    public String enroll(@RequestParam("studentId") UUID studentId, @RequestParam("classId") UUID courseId) {
    		Academy academy = Academy.getInstance();
    		academy.enroll(studentId, courseId);
    		return enrolled();
    }

    @RequestMapping(value = "withdraw")
    public String withdraw(@RequestParam("studentId") UUID studentId, @RequestParam("classId") UUID courseId) {
    	Academy academy = Academy.getInstance();
		academy.withdraw(studentId, courseId);
		return enrolled();
    }
    
    
    @RequestMapping("/enrolled")
    public String enrolled() {
    		Academy academy = Academy.getInstance();
    		HashMap<UUID, ArrayList<UUID>> enrollments = academy.getEnrollments();
    		    		
    		StringBuffer buffer = new StringBuffer();
    		buffer.append("<b>Enrollments:</b>");
    		buffer.append("</br>");
    		for (Entry<UUID, ArrayList<UUID>> entry : enrollments.entrySet()) {
    			UUID courseId = entry.getKey();
    			Course course = academy.getCourseById(courseId);
    			ArrayList<UUID> enrolledStudents = entry.getValue();
    			
    			buffer.append("<b>");
    			buffer.append(course.getName());
    			buffer.append("</b>");
    			buffer.append(" ");
    			buffer.append(course.getId());
    			buffer.append("  has enrolled students:");
    			
    			for (UUID studentId : enrolledStudents) {
    				buffer.append("<br/>");
    				Student student = academy.getStudentById(studentId);
    				buffer.append(student.getFirstName());
    				buffer.append(" ");
    				buffer.append(student.getLastName());
    				buffer.append(" - ");
    				buffer.append(student.getStudentId());
    			}
    			buffer.append("<br/><br/>");
    		}
		return buffer.toString();
    }
}
