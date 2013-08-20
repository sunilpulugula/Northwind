package com.imaginea.productapp.services;

import java.math.BigDecimal;
import java.util.List;

import com.imaginea.productapp.model.Product;

public interface ProductService
{

	public List<Product> getAllProducts();

	public Product getProductByID(Integer ID);

	public void updateProduct(Product product);

	public void deleteProduct(Product product);

	public Integer createProduct(Product product);

	public void applyDiscount(BigDecimal discountPercentage);

}
