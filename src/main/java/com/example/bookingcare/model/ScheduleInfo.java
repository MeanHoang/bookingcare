package com.example.bookingcare.model;

public class ScheduleInfo {
	private String scheduleDay;
	private String timeslotTime;
	private String patientName;
	private String patientPhone;
	private String patientEmail;
	private String patientGender;
	private String patientNote;
	private int registrationId;

	// Constructor
	public ScheduleInfo(String scheduleDay, String timeslotTime, String patientName, String patientPhone,
			String patientEmail, String patientGender, String patientNote) {
		this.scheduleDay = scheduleDay;
		this.timeslotTime = timeslotTime;
		this.patientName = patientName;
		this.patientPhone = patientPhone;
		this.patientEmail = patientEmail;
		this.patientGender = patientGender;
		this.patientNote = patientNote;
	}

	public ScheduleInfo() {
		super();
	}

	// Getters và Setters

	public String getScheduleDay() {
		return scheduleDay;
	}

	public int getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(int registrationId) {
		this.registrationId = registrationId;
	}

	public void setScheduleDay(String scheduleDay) {
		this.scheduleDay = scheduleDay;
	}

	public String getTimeslotTime() {
		return timeslotTime;
	}

	public void setTimeslotTime(String timeslotTime) {
		this.timeslotTime = timeslotTime;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientPhone() {
		return patientPhone;
	}

	public void setPatientPhone(String patientPhone) {
		this.patientPhone = patientPhone;
	}

	public String getPatientEmail() {
		return patientEmail;
	}

	public void setPatientEmail(String patientEmail) {
		this.patientEmail = patientEmail;
	}

	public String getPatientGender() {
		return patientGender;
	}

	public void setPatientGender(String patientGender) {
		this.patientGender = patientGender;
	}

	public String getPatientNote() {
		return patientNote;
	}

	public void setPatientNote(String patientNote) {
		this.patientNote = patientNote;
	}

	@Override
	public String toString() {
		return "ScheduleInfo [scheduleDay=" + scheduleDay + ", timeslotTime=" + timeslotTime + ", patientName="
				+ patientName + ", patientPhone=" + patientPhone + ", patientEmail=" + patientEmail + ", patientGender="
				+ patientGender + ", patientNote=" + patientNote + ", registrationId=" + registrationId + "]";
	}

	// toString method

}
