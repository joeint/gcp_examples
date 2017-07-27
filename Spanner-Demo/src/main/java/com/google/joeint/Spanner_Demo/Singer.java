package com.google.joeint.Spanner_Demo;

public class Singer {

	private final long singerId;
	private final String firstName;
	private final String lastName;

	public Singer(long singerId, String firstName, String lastName) {
		this.singerId = singerId;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public long getSingerId() {
		return singerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
}
