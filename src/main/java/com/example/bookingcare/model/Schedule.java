package com.example.bookingcare.model;

public class Schedule {
	private int id;
	private int doctorId;
	private String day;
	private int timeslotId;
	private boolean isActive;
	private boolean isUsed;

	// Constructor không tham số
	public Schedule() {
	}

	// Constructor có tham số
	public Schedule(int id, int doctorId, String day, int timeslotId, boolean isActive, boolean isUsed) {
		this.id = id;
		this.doctorId = doctorId;
		this.day = day;
		this.timeslotId = timeslotId;
		this.isActive = isActive;
		this.isUsed = isUsed;
	}

	// Getter và Setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public int getTimeslotId() {
		return timeslotId;
	}

	public void setTimeslotId(int timeslotId) {
		this.timeslotId = timeslotId;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		isActive = active;
	}

	public boolean isUsed() {
		return isUsed;
	}

	public void setUsed(boolean used) {
		isUsed = used;
	}

	// Phương thức toString
	@Override
	public String toString() {
		return "Schedule{" + "id=" + id + ", doctorId=" + doctorId + ", day='" + day + '\'' + ", timeslotId="
				+ timeslotId + ", isActive=" + isActive + ", isUsed=" + isUsed + '}';
	}
}
