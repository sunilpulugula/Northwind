package com.imaginea.productapp.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.imaginea.productapp.model.Product;

public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Product> getAllProducts() {
		List<Product> products = sessionFactory.getCurrentSession().createQuery("from product").list();
		return products;
	}

	@Override
	public Product getProductByID(Integer ID) {
		return (Product) sessionFactory.getCurrentSession().get(Product.class, ID);
	}

	@Override
	public List<Product> getProductsByRange(Product startRange,
			Product endingRange) {
		List<Product> list = sessionFactory.getCurrentSession().createQuery("from product WHERE age BETWEEN 1 AND 5;").list();
		return list;
	}

	@Override
	public boolean saveProduct(Product product) {
		if (product != null) {
		sessionFactory.getCurrentSession().update(product);
		return true;
		}
		return false;
	}

	@Override
	public boolean deleteProduct(Product product) {
		if (product != null) {
			sessionFactory.getCurrentSession().delete(product);
			return true;
		}
		return false;
	}

	@Override
	public Integer createProduct(Product product) {
		return (Integer)sessionFactory.getCurrentSession().save(product);
	}

}