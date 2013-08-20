package com.imaginea.productapp.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imaginea.productapp.model.Product;

@Service("ProductDAO")
public class ProductDAOImpl implements ProductDAO
{

	private final SessionFactory	sessionFactory;

	@Autowired
	public ProductDAOImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Product> getAllProducts() {
		return sessionFactory.getCurrentSession().createQuery("from Product").list();
	}

	@Override
	public Product getProductByID(Integer id) {
		return (Product) sessionFactory.getCurrentSession().get(Product.class, id);
	}

	@Override
	public void updateProduct(Product product) {
		sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().update(product);
	}

	@Override
	public void deleteProduct(Product product) {
		sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().delete(product);
	}

	@Override
	public Integer createProduct(Product product) {
		return (Integer) sessionFactory.getCurrentSession().save(product);
	}

}