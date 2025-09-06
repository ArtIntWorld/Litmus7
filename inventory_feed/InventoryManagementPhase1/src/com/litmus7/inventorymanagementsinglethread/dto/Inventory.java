package com.litmus7.inventorymanagementsinglethread.dto;

public class Inventory {
	
	private Integer sku, quantity;
	private String productName;
	private double price;
	
	public Inventory() { }
	
	public void setId(Integer sku) { this.sku = sku; }
	public Integer getId() { return sku; }
	
	public void setProductName(String productName) { this.productName = productName; }
	public String getProductName() { return productName; }
	
	public void setQuantity(Integer quantity) { this.quantity = quantity; }
	public Integer getQuantity() { return quantity; }
	
	public void setPrice(double price) { this.price = price; }
	public double getPrice() { return price; }

}
