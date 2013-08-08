package com.imaginea.productapp.dao;

import java.util.List;

import com.imaginea.productapp.model.Product;

public interface ProductDAO
{

	public List<Product> getAllProducts();

	public Product getProductByID(Integer ID);

	public List<Product> getProductsByRange(Product startRange, Product endingRange);

	public boolean saveProduct(Product product);

	public boolean deleteProduct(Product product);

	public Integer createProduct(Product product);

}