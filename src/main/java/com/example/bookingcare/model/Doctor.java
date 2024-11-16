package com.example.bookingcare.model;

import java.util.Date;

public class Doctor {
	private int id;
	private String fullname;
	private String username;
	private String password;
	private String phonenumber;
	private String address;
	private String avatarUrl;
	private String position;
	private String email;
	private Date birthday;
	private Gender gender;
	private double experience;
	private String description;
	private int price;
	private int clinicId;
	private int specialtyId;

	// Constructors
	public Doctor() {
	}

	public Doctor(int id, String fullname, String username, String password, String phonenumber, String address,
			String avatarUrl, String position, String email, Date birthday, Gender gender, double experience,
			String description, int price, int clinicId, int specialtyId) {
		this.id = id;
		this.fullname = fullname;
		this.username = username;
		this.password = password;
		this.phonenumber = phonenumber;
		this.address = address;
		this.avatarUrl = avatarUrl;
		this.position = position;
		this.email = email;
		this.birthday = birthday;
		this.gender = gender;
		this.experience = experience;
		this.description = description;
		this.price = price;
		this.clinicId = clinicId;
		this.specialtyId = specialtyId;
	}

	// Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public double getExperience() {
		return experience;
	}

	public void setExperience(double experience) {
		this.experience = experience;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getClinicId() {
		return clinicId;
	}

	public void setClinicId(int clinicId) {
		this.clinicId = clinicId;
	}

	public int getSpecialtyId() {
		return specialtyId;
	}

	public void setSpecialtyId(int specialtyId) {
		this.specialtyId = specialtyId;
	}

	@Override
	public String toString() {
		return "Doctor{" + "id=" + id + ", fullname='" + fullname + '\'' + ", username='" + username + '\''
				+ ", password='" + password + '\'' + ", phonenumber='" + phonenumber + '\'' + ", address='" + address
				+ '\'' + ", avatarUrl='" + avatarUrl + '\'' + ", position='" + position + '\'' + ", email='" + email
				+ '\'' + ", birthday=" + birthday + ", gender=" + gender + ", experience=" + experience
				+ ", description='" + description + '\'' + ", price=" + price + ", clinicId=" + clinicId
				+ ", specialtyId=" + specialtyId + '}';
	}
}
