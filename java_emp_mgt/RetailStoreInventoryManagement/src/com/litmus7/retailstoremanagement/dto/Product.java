package com.litmus7.retailstoremanagement.dto;

public abstract class Product {
	
	private int id;
	private String name;
	protected String category;
    protected ProductStatus status;
	private double price;
	
	public Product(int id, String name, double price, ProductStatus status, String category){
		this.id = id;
		this.name = name;
		this.price = price;
		this.status = status;
		this.category = category;
	}
	
	public void setId(int id) { this.id = id; }
	public int getId() { return id; }
	
	public void setName(String name) { this.name = name; }
	public String getName() { return name; }
	
	public void setPrice(double price) { this.price = price; }
	public double getPrice() { return price; }
	
	public void setStatus(ProductStatus status) { this.status = status; }
	public ProductStatus getStatus() { return status; }
	
	public void setCategory(String category) { this.category = category; }
	public String getCategory() { return category; }
}
