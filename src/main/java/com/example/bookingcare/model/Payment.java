package com.example.bookingcare.model;

import java.util.Date;

public class Payment {
	private int id;
	private String name;
	private int price;
	private String payMethod;
	private String status;
	private Date date;
	private boolean codConfirmation;
	private String transactionId;

	// Constructors
	public Payment() {
	}

	public Payment(int id, String name, int price, String payMethod, String status, Date date, boolean codConfirmation,
			String transactionId) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.payMethod = payMethod;
		this.status = status;
		this.date = date;
		this.codConfirmation = codConfirmation;
		this.transactionId = transactionId;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isCodConfirmation() {
		return codConfirmation;
	}

	public void setCodConfirmation(boolean codConfirmation) {
		this.codConfirmation = codConfirmation;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
}
