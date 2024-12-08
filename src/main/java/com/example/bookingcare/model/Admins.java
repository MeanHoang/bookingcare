package com.example.bookingcare.model;

public class Admins {
	    private int ID;
	    private String Username;
	    private String Password;
	    private boolean isActive; // Thêm thuộc tính is_active
	    private String Name;

	    // Constructor mặc định
	    public Admins() {
	        super();
	    }

	    // Constructor với các tham số
	    public Admins(int ID, String username, String password, String name, boolean isActive) {
	        super();
	        this.ID = ID;
	        this.Username = username;
	        this.Password = password;
	        this.Name = name;
	        this.isActive = isActive; // Khởi tạo isActive
	    }

	    // Getter và Setter cho ID
	    public int getID() {
	        return ID;
	    }

	    public void setID(int ID) {
	        this.ID = ID;
	    }

	    // Getter và Setter cho Username
	    public String getUsername() {
	        return Username;
	    }

	    public void setUsername(String username) {
	        this.Username = username;
	    }

	    // Getter và Setter cho Password
	    public String getPassword() {
	        return Password;
	    }

	    public void setPassword(String password) {
	        this.Password = password;
	    }

	    // Getter và Setter cho Name
	    public String getName() {
	        return Name;
	    }

	    public void setName(String name) {
	        this.Name = name;
	    }

	    // Getter và Setter cho isActive
	    public boolean isActive() {
	        return isActive;
	    }

	    public void setActive(boolean isActive) {
	        this.isActive = isActive;
	    }

	    // Phương thức toString
	    @Override
	    public String toString() {
	        return "Admins [ID=" + ID + ", Username=" + Username + ", Password=" + Password + ", Name=" + Name 
	                + ", isActive=" + isActive + "]";
	    }
}