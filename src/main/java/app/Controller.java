package app;

import org.springframework.web.bind.annotation.RestController;

import model.Academy;
import model.Course;
import model.Enrollment;
import model.Student;
import view.View;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class Controller {
    
    @RequestMapping("/")
    public String index() {
        return "<b>Welcome to Academy :)</b>";
    }
    
    @RequestMapping("/classes")
    public String classes() {
    		Academy academy = Academy.getInstance();
		ArrayList<Course> courses = academy.getCourses();
		return View.getCoursesView(courses);
    }
    
    @RequestMapping("/students")
    public String students() {
    		Academy academy = Academy.getInstance();
		ArrayList<Student> students = academy.getStudents();
		return View.getStudentsView(students);
    }
    
    @RequestMapping(value = "student")
    public String getStudent(@RequestParam("id") String studentId) {
    		Academy academy = Academy.getInstance();
    		return academy.getStudentById(UUID.fromString(studentId)).toString();
    }
    
    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String searchClasses(@RequestParam("pattern") String pattern) {
    		Academy academy = Academy.getInstance();
		ArrayList<Course> courses = academy.getCourses();
		ArrayList<Course> selected = new ArrayList<>();
    		for (Course course : courses) {
    			if (course.getCourseName().toLowerCase().contains(pattern.toLowerCase())) {
    				selected.add(course);
    			}
    		}
    		return View.getSelectedCoursesView(selected);
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
    		ArrayList<Enrollment> enrollments = academy.getEnrollments();
    		return View.getEnrollmentsView(enrollments);
    }
}
