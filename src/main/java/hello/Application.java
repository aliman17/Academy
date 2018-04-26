package hello;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import model.Academy;
import model.Course;
import model.Student;

@SpringBootApplication
public class Application {
    
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        
        String[] courses = {"Mathematics", "English", "Sociology", "Chemistry", "Geography", "History"};
        String[] students = {"Janez Novak", "Ales Omerzel", "Borut Pahor"};
        
        Academy academy = Academy.getInstance();
        
        // Create courses.
        ArrayList<Course> courses_ = new ArrayList<>();
        for (String course : courses) {
            Course c = academy.addCourse(course);
            courses_.add(c);
        }
        
        // Create students.
        ArrayList<Student> students_ = new ArrayList<>();
        for (String student : students) {
        		String[] tmp = student.split(" ");
        		String firstName = tmp[0];
        		String lastName = tmp[1];
        		Student s = academy.addStudent(firstName, lastName);
        		students_.add(s);
        }
        
        // Enroll students to some courses.
        UUID student1 = students_.get(0).getStudentId();
        
        Controller c = new Controller();
        c.enroll(student1, courses_.get(0).getId());
        c.enroll(student1, courses_.get(1).getId());
        c.enroll(student1, courses_.get(2).getId());

        UUID student2 = students_.get(1).getStudentId();

        c.enroll(student2, courses_.get(0).getId());
        c.enroll(student2, courses_.get(1).getId());    
        
    }

}
