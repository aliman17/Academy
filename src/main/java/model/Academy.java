package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.UUID;

import database.Constants;
import database.DatabaseHandler;

public class Academy {
	private static Academy instance = null;
		
	public static Academy getInstance() {
		if (instance == null) {
			instance = new Academy();
		}
		return instance;
	}
	
	public void addStudent(String firstName, String lastName) {
		//UUID student_id = UUID.randomUUID();
		UUID studentId = UUID.nameUUIDFromBytes((firstName+lastName).getBytes());
		Connection conn;
		try {
			conn = DatabaseHandler.getConnection();
			DatabaseHandler.update(conn, String.format("INSERT IGNORE INTO Students VALUES ('%s', '%s', '%s');", studentId.toString(), firstName, lastName));
			DatabaseHandler.closeConnection(conn);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Student getStudentById(UUID studentId) {
		Connection conn;
		ArrayList<HashMap<String, String>> students = null;
		try {
			conn = DatabaseHandler.getConnection();
			students = DatabaseHandler.query(conn, String.format("SELECT * FROM Students WHERE %s='%s'", Constants.STUDENT_ID_COL, studentId.toString()));
			DatabaseHandler.closeConnection(conn);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		HashMap<String, String> studentResult = students.get(0);
		String firstName = studentResult.get(Constants.STUDENT_FIRST_NAME_COL);
		String lastName = studentResult.get(Constants.STUDENT_LAST_NAME_COL);
		Student student = new Student(studentId, firstName, lastName);
		return student;
	}
	
	public void addCourse(String courseName) {
		//UUID courseId = UUID.randomUUID();
		UUID courseId = UUID.nameUUIDFromBytes(courseName.getBytes());
		Connection conn;
		try {
			conn = DatabaseHandler.getConnection();
			String sql = String.format("INSERT IGNORE INTO Courses VALUES ('%s', '%s');", courseId.toString(), courseName);
			DatabaseHandler.update(conn, sql);
			DatabaseHandler.closeConnection(conn);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Course getCourseById(UUID courseId) {
		Connection conn;
		ArrayList<HashMap<String, String>> courses = null;
		try {
			conn = DatabaseHandler.getConnection();
			String sql = String.format("SELECT * FROM Courses WHERE %s='%s'", Constants.COURSE_ID_COL, courseId.toString());
			courses = DatabaseHandler.query(conn, sql);
			DatabaseHandler.closeConnection(conn);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		HashMap<String, String> courseResult = courses.get(0);
		String courseName = courseResult.get(Constants.COURSE_NAME_COL);
		Course course = new Course(courseId, courseName);
		return course;
	}
	
	public void enroll(UUID studentId, UUID courseId) {
		Connection conn;
		try {
			conn = DatabaseHandler.getConnection();
			String sql = String.format("INSERT IGNORE INTO Enrollments VALUES('%s', '%s')", studentId.toString(), courseId.toString());
			DatabaseHandler.update(conn, sql);
			DatabaseHandler.closeConnection(conn);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void withdraw(UUID studentId, UUID courseId) {
		Connection conn;
		try {
			conn = DatabaseHandler.getConnection();
			String sql = String.format("DELETE FROM Enrollments WHERE %s='%s' AND %s='%s'", Constants.STUDENT_ID_COL, studentId.toString(), Constants.COURSE_ID_COL, courseId.toString());
			DatabaseHandler.update(conn, sql);
			DatabaseHandler.closeConnection(conn);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}	}
	
	public ArrayList<Course> getCourses() {
		Connection conn;
		ArrayList<HashMap<String, String>> courses = null;
		try {
			conn = DatabaseHandler.getConnection();
			String query = "SELECT * FROM Courses";
			courses = DatabaseHandler.query(conn, query);
			DatabaseHandler.closeConnection(conn);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ArrayList<Course> courses_ = new ArrayList<>();
		for (HashMap<String, String> result : courses) {
			String courseId = result.get(Constants.COURSE_ID_COL);
			String courseName = result.get(Constants.COURSE_NAME_COL);
			Course c = new Course(UUID.fromString(courseId), courseName);
			courses_.add(c);
		}
		return courses_;	
	}
	
	public ArrayList<Student> getStudents() {
		Connection conn;
		ArrayList<HashMap<String, String>> students = null;
		try {
			conn = DatabaseHandler.getConnection();
			String query = "SELECT * FROM Students";
			students = DatabaseHandler.query(conn, query);
			DatabaseHandler.closeConnection(conn);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ArrayList<Student> students_ = new ArrayList<>();
		
		for (HashMap<String, String> result : students) {
			String studentId = result.get(Constants.STUDENT_ID_COL);
			String firstName = result.get(Constants.STUDENT_FIRST_NAME_COL);
			String lastName = result.get(Constants.STUDENT_LAST_NAME_COL);
			Student s = new Student(UUID.fromString(studentId), firstName, lastName);
			students_.add(s);
		}
		return students_;
	}
	
	public ArrayList<Enrollment> getEnrollments() {
		Connection conn;
		ArrayList<HashMap<String, String>> enrollments = null;
		try {
			conn = DatabaseHandler.getConnection();
			String query = "SELECT * FROM Students s JOIN Enrollments e ON s.StudentID=e.StudentID JOIN Courses c ON e.CourseID=c.CourseID";
			enrollments = DatabaseHandler.query(conn, query);
			DatabaseHandler.closeConnection(conn);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		ArrayList<Enrollment> enrollments_ = new ArrayList<>();
		
		for (HashMap<String, String> result : enrollments) {
			
			String studentId = result.get(Constants.STUDENT_ID_COL);
			String firstName = result.get(Constants.STUDENT_FIRST_NAME_COL);
			String lastName = result.get(Constants.STUDENT_LAST_NAME_COL);
			
			Student student = new Student(UUID.fromString(studentId), firstName, lastName);
			
			String courseId = result.get(Constants.COURSE_ID_COL);
			String courseName = result.get(Constants.COURSE_NAME_COL);
			
			Course course = new Course(UUID.fromString(courseId), courseName);
			
			Enrollment enrollment = new Enrollment(student, course);
			
			enrollments_.add(enrollment);
		}
		return enrollments_;
	}
	
}
