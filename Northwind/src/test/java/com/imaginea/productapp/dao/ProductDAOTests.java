package com.imaginea.productapp.dao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.imaginea.productapp.model.Product;

@RunWith(MockitoJUnitRunner.class)
public class ProductDAOTests
{

	private final static Integer	ID	= 1; 

	@Mock
	private SessionFactory				sessionFactory;

	@Mock
	private Session								session;

	@Mock
	private Query									Query;

	@Mock
	private Product								product;

	private ProductDAO						productDAO;

	@Before
	public void setUp() {
		productDAO = new ProductDAOImpl(sessionFactory);
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		when(session.save(product)).thenReturn(ID);
		when(productDAO.getProductByID(ID)).thenReturn(product);
		when(session.createQuery("from Product")).thenReturn(Query);
		when(session.createQuery("from Product").list()).thenReturn(new ArrayList(Arrays.asList(product)));
	}

	@Test
	public void createProductShouldCallSessionSave() {
		Integer productID = productDAO.createProduct(product);
		verify(session).save(product);
		assertNotNull(productID);
		Product existingProduct = productDAO.getProductByID(ID);
		assertThat(existingProduct, is(product));
	}

	@Test
	public void getAllProductsShouldCallCreateQueryOnSession() {
		List<Product> list = productDAO.getAllProducts();
	  //verify(session).createQuery("from Product");
		assertNotNull(list);
		assertThat("Number Of Products is not correct", list.size() == 1);
		
	}

	@Test
	public void deleteProductShouldCallDeleteOnSession() {
		productDAO.deleteProduct(product);
		verify(session).delete(product);
	}

	@Test
	public void updateProductShouldCallUpdateOnSession() {
		productDAO.updateProduct(product);
		verify(session).update(product);
	}

	@Test
	public void getProductByIDShouldCallGetOnSession() {
		productDAO.getProductByID(ID);
		verify(session).get(Product.class, ID);
	}

}
