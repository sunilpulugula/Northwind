package com.imaginea.productapp.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.imaginea.productapp.model.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:productapp-servlet.xml")
@Transactional
public class ProductDAOTest {

	@Autowired
	private ProductDAO productDao;

	@Test
	public void TestForCreateProduct() {
		Product newProduct = new Product();
		newProduct.setName("TV");
		newProduct.setPrice(300.00f);

		Integer productID = productDao.createProduct(newProduct);

		Product existingProduct = productDao.getProductByID(productID);

		Assert.assertEquals("Product not exist with the ID" + productID,
				productID, existingProduct.getPID());
		Assert.assertEquals("Product name is not equal", newProduct.getName(),
				existingProduct.getName());
		Assert.assertEquals("Product price is not equal",
				newProduct.getPrice(), existingProduct.getPrice());
	}

	@Test
	public void TestForGetAllProducts() {

		Integer preCount = productDao.getAllProducts().size();
		Product newProduct = new Product();
		newProduct.setName("TV");
		newProduct.setPrice(300.00f);
		productDao.createProduct(newProduct);
		Integer postCount = productDao.getAllProducts().size();
		Assert.assertEquals("Count of product is not correct", preCount,
				preCount);
	}
	
	@Test
	public void TestForUpdateProduct()
	{
	
	}
	
	@Test
	public void TestForDeleteProduct()
	{
		
	}

	@Test
	public void TestForGetProductsByRange()
	{
		
	}
}
