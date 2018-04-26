package model;

import java.util.UUID;

public class Course {
	private UUID courseId;
	private String courseName;
	
	public Course(UUID courseId, String courseName) {
		this.courseId = courseId;
		this.courseName = courseName;
	}

	public UUID getId() {
		return courseId;
	}
	
	public String getName() {
		return courseName;
	}
	
	public String toString() {
		return courseName + " " + courseId;
	}
}
