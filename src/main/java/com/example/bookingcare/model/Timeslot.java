package com.example.bookingcare.model;

public class Timeslot {
	private int id;
	private String name;
	private String startTime;
	private String endTime;
	private Boolean isActive;

	// Constructor không tham số
	public Timeslot() {
	}

	public Boolean getIsActive() { // Đây là getter chính xác
		return isActive;
	}

	public void setIsActive(Boolean isActive) { // Setter chính xác
		this.isActive = isActive;
	}

	// Constructor có tham số
	public Timeslot(int id, String name, String startTime, String endTime) {
		this.id = id;
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	// Getter và Setter
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

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	// Phương thức toString
	@Override
	public String toString() {
		return "Timeslot{" + "id=" + id + ", name='" + name + '\'' + ", startTime='" + startTime + '\'' + ", endTime='"
				+ endTime + '\'' + '}';
	}
}
