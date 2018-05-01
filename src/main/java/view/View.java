package view;

import java.util.ArrayList;

import model.Course;
import model.Enrollment;
import model.Student;

public class View {

	public static String getCoursesView(ArrayList<Course> courses) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<b>Available classes:</b>");
		for (Course course : courses) {
			buffer.append("</br>");
			buffer.append(course.toString());
		}
		return buffer.toString();
	}
	
	public static String getStudentsView(ArrayList<Student> students) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<b>Students:</b>");
		for (Student student : students) {
			buffer.append("</br>");
			buffer.append(student.toString());
		}
    		return buffer.toString();
	}
	
	public static String getSelectedCoursesView(ArrayList<Course> courses) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<b>Selected classes:</b>");
		for (Course course : courses) {
			buffer.append("</br>");
			buffer.append(course.toString());
		}
		return buffer.toString();
	}
	
	public static String getEnrollmentsView(ArrayList<Enrollment> enrollments) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<b>Enrollments:</b>");
		for (Enrollment enrollment : enrollments) {
			String student = enrollment.getStudent().toString();
			String course = enrollment.getCourse().toString();
			
			buffer.append("</br>");
			buffer.append("<b>");
			buffer.append(student);
			buffer.append("</b>");
			buffer.append(" ");
			buffer.append(course);
		}
		return buffer.toString();
	}
}
