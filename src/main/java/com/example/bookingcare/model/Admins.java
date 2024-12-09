package com.example.bookingcare.model;

public class Admins {
	private int ID;
	private String Username;
	private String Password;
	private boolean isActive;
	private String Name;

	public Admins() {
		super();
	}

	public Admins(int ID, String username, String password, String name, boolean isActive) {
		super();
		this.ID = ID;
		this.Username = username;
		this.Password = password;
		this.Name = name;
		this.isActive = isActive; // Khá»Ÿi táº¡o isActive
	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	// Getter vÃ  Setter cho Username
	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		this.Username = username;
	}

	// Getter vÃ  Setter cho Password
	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		this.Password = password;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		this.Name = name;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Admins [ID=" + ID + ", Username=" + Username + ", Password=" + Password + ", Name=" + Name
				+ ", isActive=" + isActive + "]";
	}
}