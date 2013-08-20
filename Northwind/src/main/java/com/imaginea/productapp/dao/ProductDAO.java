package com.imaginea.productapp.dao;

import java.util.List;

import com.imaginea.productapp.model.Product;

public interface ProductDAO
{

	public List<Product> getAllProducts();

	public Product getProductByID(Integer ID);

	public void updateProduct(Product product);

	public void deleteProduct(Product product);

	public Integer createProduct(Product product);

}