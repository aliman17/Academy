package model;

import java.util.UUID;

public class Student {
	
	private UUID studentId;
	private String firstName;
	private String lastName;
	
	public Student(UUID studentId, String firstName, String lastName) {
		this.studentId = studentId;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public UUID getStudentId() {
		return studentId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public String toString() {
		return firstName + " " + lastName + " " + studentId;
	}
}
