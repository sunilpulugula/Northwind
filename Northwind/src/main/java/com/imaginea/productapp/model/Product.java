package com.imaginea.productapp.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@Column(name = "PID")
	@GeneratedValue
	private Integer productID;

	@Column(name = "Name")
	private String name;

	@Column(name = "UnitPrice")
	private BigDecimal price;
	
	@Column(name = "QunatityPerUnit")
	private int qunatityPerUnit;
	
	@Column(name = "UnitsInStock")
	private int unitsInStock;
	
	@Column(name = "UnitsOnOrder")
	private int unitsOnOrder;
	
	public int getQunatityPerUnit() {
		return qunatityPerUnit;
	}

	public void setQunatityPerUnit(int qunatityPerUnit) {
		this.qunatityPerUnit = qunatityPerUnit;
	}

	public int getUnitsInStock() {
		return unitsInStock;
	}

	public void setUnitsInStock(int unitsInStock) {
		this.unitsInStock = unitsInStock;
	}

	public int getUnitsOnOrder() {
		return unitsOnOrder;
	}

	public void setUnitsOnOrder(int unitsOnOrder) {
		this.unitsOnOrder = unitsOnOrder;
	}

	public Integer getProductID() {
		return productID;
	}

	public void setProductID(Integer productID) {
		this.productID = productID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
