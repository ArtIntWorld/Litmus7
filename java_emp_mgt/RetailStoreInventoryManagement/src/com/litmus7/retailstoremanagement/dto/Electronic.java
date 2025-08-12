package com.litmus7.retailstoremanagement.dto;

public class Electronic extends Product {
	
	private String brand;
	private int warrantyMonths;

	public Electronic(int id, String name, double price, ProductStatus status, String category, String brand, int warrantyMonths) {
		super(id, name, price, status, category);
		this.brand = brand;
		this.warrantyMonths = warrantyMonths;
	}
	
	public void setBrand(String brand) { this.brand = brand; }
	public String getBrand() { return brand; }
	
	public void setWarrantyMonths(int warrantyMonths) { this.warrantyMonths = warrantyMonths; }
	public int getWarrantyMonths() { return warrantyMonths; }
}
