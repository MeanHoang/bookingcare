package com.example.bookingcare.model;

public class Clinic {
	private int id;
	private String name;
	private String description;
	private String address;
	private String workingTime;

	// Constructors
	public Clinic() {
	}

	public Clinic(int id, String name, String description, String address, String workingTime) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.address = address;
		this.workingTime = workingTime;
	}

	// Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWorkingTime() {
		return workingTime;
	}

	public void setWorkingTime(String workingTime) {
		this.workingTime = workingTime;
	}

	@Override
	public String toString() {
		return "Clinic{" + "id=" + id + ", name='" + name + '\'' + ", description='" + description + '\''
				+ ", address='" + address + '\'' + ", workingTime='" + workingTime + '\'' + '}';
	}
}
