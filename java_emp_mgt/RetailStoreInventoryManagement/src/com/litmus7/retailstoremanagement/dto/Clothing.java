package com.litmus7.retailstoremanagement.dto;

public class Clothing extends Product {

	private String size;
	private String material;
	
	public Clothing(int id, String name, double price, ProductStatus status, String category, String size, String material) {
		super(id, name, price, status, category);
		this.size = size;
		this.material = material;
	}
	
	public void setSize(String size) { this.size = size; }
	public String getSize() { return size; }
	
	public void setMaterial(String material) { this.material = material; }
	public String getMaterial() { return material; }

}
