package app;



import java.sql.SQLException;

// Run the project by:
// mvn package && java -jar target/gs-spring-boot-0.1.0.jar

import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import database.DatabaseHandler;
import model.Academy;

@SpringBootApplication
public class Application {
    
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        
        try {
			DatabaseHandler.initDatabase();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        String[] courses = {"Mathematics", "English", "Sociology", "Chemistry", "Geography", "History"};
        String[] students = {"Janez Novak", "Ana Novak", "Bor Novak"};
        
        Academy academy = Academy.getInstance();
        
        // Create courses.
        for (String course : courses) {
            academy.addCourse(course);
        }
        
        // Create students.
        for (String student : students) {
        		String[] name = student.split(" ");
        		academy.addStudent(name[0], name[1]);
        }
        
        // Enroll Ana to Math.
        academy.enroll(
        		UUID.fromString("403c3a65-903e-3603-8477-457a0326bf15"), 
        		UUID.fromString("540b21ec-db27-3f50-87ee-585cedd6d5d0"));
        // Enroll Janez to Math.
        academy.enroll(
        		UUID.fromString("88bfd3e8-1d13-3fb5-9928-e0b916ee2c9b"), 
        		UUID.fromString("540b21ec-db27-3f50-87ee-585cedd6d5d0"));
        // Enroll Bor to Math
        academy.enroll(
        		UUID.fromString("c5bdbd61-7274-3445-a043-f90c092afe88"), 
        		UUID.fromString("540b21ec-db27-3f50-87ee-585cedd6d5d0"));
        // Enroll Ana to Chemistry.
        academy.enroll(
        		UUID.fromString("403c3a65-903e-3603-8477-457a0326bf15"), 
        		UUID.fromString("84cf6008-945c-3b55-8ed7-c0869557b70d"));
        // Enroll Ana to Sociology.
        academy.enroll(
        		UUID.fromString("403c3a65-903e-3603-8477-457a0326bf15"), 
        		UUID.fromString("2363ebcd-d984-3556-b3d0-8534c5c250c8"));
        // Enroll Bor to Sociology
        academy.enroll(
        		UUID.fromString("c5bdbd61-7274-3445-a043-f90c092afe88"), 
        		UUID.fromString("2363ebcd-d984-3556-b3d0-8534c5c250c8"));
        
    }

}
