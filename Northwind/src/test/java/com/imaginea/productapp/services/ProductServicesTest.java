package com.imaginea.productapp.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:productapp-servlet.xml")
@Transactional
public class ProductServicesTest {

	@Autowired
	private ProductService productService;
	
	@Test
	public void TestForCreateProduct() {
	}

	@Test
	public void TestForGetAllProducts() {

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
