package model;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.UUID;

public class Academy {
	private static Academy instance = null;
	
	private ArrayList<Student> students = new ArrayList<>();
	private ArrayList<Course> courses = new ArrayList<>();
	private Enrollment enrollment = new Enrollment();
	
	public static Academy getInstance() {
		if (instance == null) {
			instance = new Academy();
		}
		return instance;
	}
	
	public Student addStudent(String first_name, String last_name) {
		//UUID student_id = UUID.randomUUID();
		UUID student_id = UUID.nameUUIDFromBytes((first_name+last_name).getBytes());
		Student student = new Student(student_id, first_name, last_name);
		students.add(student);
		return student;
	}
	
	public Student getStudentById(UUID studentId) {
		// TODO: Could be optimized by using hashmap.
		for (Student student : students) {
			if (student.getStudentId().equals(studentId)) {
				return student;
			}
		}
		// TODO: else throw exception
		return null;
	}
	
	public Course addCourse(String class_name) {
		//UUID courseId = UUID.randomUUID();
		UUID courseId = UUID.nameUUIDFromBytes(class_name.getBytes());
		Course course = new Course(courseId, class_name);
		courses.add(course);
		enrollment.registerCourse(course);
		return course;
	}
	
	public Course getCourseById(UUID courseId) {
		// TODO: Could be optimized by using hashmap.
		for (Course course : courses) {
			if (course.getId().equals(courseId)) {
				return course;
			}
		}
		// TODO: else throw exception
		return null;
	}
	
	public void enroll(UUID studentId, UUID courseId) {
		enrollment.enroll(studentId, courseId);
	}
	
	public void withdraw(UUID studentId, UUID courseId) {
		enrollment.withdraw(studentId, courseId);
	}
	
	public ArrayList<Course> getCourses() {
		return (ArrayList<Course>) this.courses.clone();
	}
	
	public ArrayList<Student> getStudents() {
		return (ArrayList<Student>) this.students.clone();
	}
	
	public HashMap<UUID, ArrayList<UUID>> getEnrollments() {
		return enrollment.getEnrollments();
	}
	
}
