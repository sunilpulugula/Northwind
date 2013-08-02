package com.imaginea.productapp.services;

import java.util.List;

import com.imaginea.productapp.dao.ProductDAO;
import com.imaginea.productapp.model.Product;

public class ProductServiceImpl implements ProductService {

	ProductDAO productDao;

	@Override
	public List<Product> getAllProducts() {
		return productDao.getAllProducts();
	}

	@Override
	public Product getProductByID(Integer ID) {
		return productDao.getProductByID(ID);
	}

	@Override
	public List<Product> getProductsByRange(Product startRange,
			Product endingRange) {
		return this.productDao.getProductsByRange(startRange, endingRange);
	}

	@Override
	public boolean saveProduct(Product product) {
		return this.productDao.saveProduct(product);
	}

	@Override
	public boolean deleteProduct(Product product) {
		return this.productDao.deleteProduct(product);
	}

	@Override
	public Integer createProduct(Product product) {
		return this.createProduct(product);
	}

}