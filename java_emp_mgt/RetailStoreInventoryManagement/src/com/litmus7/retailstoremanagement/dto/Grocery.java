package com.litmus7.retailstoremanagement.dto;

import java.util.Date;

public class Grocery extends Product {

	private Date expiryDate;
	private double weightKg;
	
	public Grocery(int id, String name, double price, ProductStatus status, String category, Date expiryDate, double weightKg) {
		super(id, name, price, status, category);
		this.expiryDate = expiryDate;
		this.weightKg =weightKg;
	}
	
	public void setExpiryDate(Date expiryDate) { this.expiryDate = expiryDate; }
	public Date getExpiryDate() { return expiryDate; }
	
	public void setWeightKg(double weightKg) { this.weightKg = weightKg; }
	public double getWeightKg() { return weightKg; }
	
}
