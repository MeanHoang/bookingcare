package com.example.bookingcare.model;

import java.util.Date;

public class Registration {
	private int id;
	private int doctorId;
	private int scheduleId;
	private String timeslotName;
	private String fullName;
	private Date day;
	private Date birthday;
	private String gender;
	private String phoneNumber;
	private String email;
	private String province;
	private String district;
	private String commune;
	private int price;
	private String note;
	private boolean isActive;
	private String clinicAddress; // New field for clinic address

	// Constructors
	public Registration() {
	}

	public Registration(int id, int doctorId, int scheduleId, String timeslotName, String fullName, Date day,
			String gender, String phoneNumber, String email, String province, String district, String commune,
			int price, String note, boolean isActive, String clinicAddress) {
		this.id = id;
		this.doctorId = doctorId;
		this.scheduleId = scheduleId;
		this.timeslotName = timeslotName;
		this.fullName = fullName;
		this.day = day;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.province = province;
		this.district = district;
		this.commune = commune;
		this.price = price;
		this.note = note;
		this.isActive = isActive;
		this.clinicAddress = clinicAddress; // Initialize the new field
	}

	// Getters and Setters

	public int getId() {
		return id;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getTimeslotName() {
		return timeslotName;
	}

	public void setTimeslotName(String timeslotName) {
		this.timeslotName = timeslotName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCommune() {
		return commune;
	}

	public void setCommune(String commune) {
		this.commune = commune;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		isActive = active;
	}

	public String getClinicAddress() {
		return clinicAddress; // Getter for clinicAddress
	}

	public void setClinicAddress(String clinicAddress) {
		this.clinicAddress = clinicAddress; // Setter for clinicAddress
	}

	@Override
	public String toString() {
		return "Registration [id=" + id + ", doctorId=" + doctorId + ", scheduleId=" + scheduleId + ", timeslotName="
				+ timeslotName + ", fullName=" + fullName + ", day=" + day + ", birthday=" + birthday + ", gender="
				+ gender + ", phoneNumber=" + phoneNumber + ", email=" + email + ", province=" + province
				+ ", district=" + district + ", commune=" + commune + ", price=" + price + ", note=" + note
				+ ", isActive=" + isActive + ", clinicAddress=" + clinicAddress + "]";
	}

}
