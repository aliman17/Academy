package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Enrollment {
	
	private HashMap<UUID, ArrayList<UUID>> enrollment = new HashMap<>();
	
	public void registerCourse(Course course) {
		enrollment.put(course.getId(), new ArrayList<UUID>());
	}
	
	public void enroll(UUID studentId, UUID courseId) {
		if (enrollment.containsKey(courseId)) {
			enrollment.get(courseId).add(studentId);
		} else {
			ArrayList<UUID> enrolledStudents = new ArrayList<>();
			enrolledStudents.add(studentId);
			enrollment.put(courseId, enrolledStudents);
		}
	}
	
	public void withdraw(UUID studentId, UUID courseId) {
		if (enrollment.containsKey(courseId)) {
			enrollment.get(courseId).remove(studentId);
		} else {
			// TODO: throw exception for no existing class
		}
	}
	
	public HashMap<UUID, ArrayList<UUID>> getEnrollments() {
		return (HashMap<UUID, ArrayList<UUID>>) enrollment.clone();
	}
	
	
	
}
