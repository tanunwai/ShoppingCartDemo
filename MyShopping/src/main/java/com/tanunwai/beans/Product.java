package com.tanunwai.beans;

public class Product {
	private int id;
	private String name;
	private double price;
	private String category;
	private String images;
	public Product() {}
	public Product(int id, String name, double price, String category, String images) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.category = category;
		this.images = images;
	}
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", category=" + category + ", images="
				+ images + "]";
	}		
}
