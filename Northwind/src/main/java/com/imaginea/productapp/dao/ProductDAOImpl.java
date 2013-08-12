package com.imaginea.productapp.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.imaginea.productapp.model.Product;

public class ProductDAOImpl implements ProductDAO
{

	@Autowired
	private SessionFactory	sessionFactory;

	@Override
	public List<Product> getAllProducts() {
		return sessionFactory.getCurrentSession().createQuery("from Product").list();
	}

	@Override
	public Product getProductByID(Integer ID) {
		return (Product) sessionFactory.getCurrentSession().get(Product.class, ID);
	}

	@Override
	public List<Product> getProductsByRange(Product startRange, Product endingRange) {
		List<Product> list = sessionFactory.getCurrentSession()
																				.createQuery("from Product where PID >= "
																											+ "'"
																											+ startRange.getProductID()
																											+ "'"
																											+ " AND PID <= "
																											+ "'"
																											+ endingRange.getProductID()
																											+ "'")
																				.list();
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
		return (Integer) sessionFactory.getCurrentSession().save(product);
	}

}