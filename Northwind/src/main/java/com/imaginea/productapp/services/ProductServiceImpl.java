package com.imaginea.productapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imaginea.productapp.dao.ProductDAO;
import com.imaginea.productapp.model.Product;

@Service
public class ProductServiceImpl implements ProductService
{

	@Autowired
	private ProductDAO	productDao;

	@Override
	@Transactional
	public List<Product> getAllProducts() {
		return productDao.getAllProducts();
	}

	@Override
	@Transactional
	public Product getProductByID(Integer ID) {
		return productDao.getProductByID(ID);
	}

	@Override
	@Transactional
	public List<Product> getProductsByRange(Product startRange, Product endingRange) {
		return this.productDao.getProductsByRange(startRange, endingRange);
	}

	@Override
	@Transactional
	public boolean saveProduct(Product product) {
		return this.productDao.saveProduct(product);
	}

	@Override
	@Transactional
	public boolean deleteProduct(Product product) {
		return this.productDao.deleteProduct(product);
	}

	@Override
	@Transactional
	public Integer createProduct(Product product) {
		return this.productDao.createProduct(product);
	}

}