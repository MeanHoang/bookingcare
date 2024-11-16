package com.example.bookingcare.model;

public class Admins {
	
	private int ID;
	private String Username;
	private String Password;
	private String Name;
	
	//con
	public Admins() {
		super();
	}
	public Admins(int iD, String username, String password, String name) {
		super();
		ID = iD;
		Username = username;
		Password = password;
		Name = name;
	}
	
	//Set get method
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	
	@Override
	public String toString() {
		return "Admins [ID=" + ID + ", Username=" + Username + ", Password=" + Password + ", Name=" + Name + "]";
	}
	
	
}