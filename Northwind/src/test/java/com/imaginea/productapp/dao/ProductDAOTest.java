package com.imaginea.productapp.dao;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.imaginea.productapp.model.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-productapp-servlet.xml")
@Transactional
@Repository
public class ProductDAOTest {

	@Autowired
	private ProductDAO productDao;

	@Test
	public void TestForCreateProduct() {
		Product newProduct = createNewProduct("TV", new BigDecimal(399.99), 3,
				12, 4);
		Integer productID = productDao.createProduct(newProduct);
		Product existingProduct = productDao.getProductByID(productID);
		Assert.assertEquals("Product not exist with the ID" + productID,
				productID, existingProduct.getProductID());
		Assert.assertEquals("Product name is not equal", newProduct.getName(),
				existingProduct.getName());
		Assert.assertEquals("Product price is not equal",
				newProduct.getPrice(), existingProduct.getPrice());
		Assert.assertEquals("QunatityPerUnit is not equal",
				newProduct.getQunatityPerUnit(),
				existingProduct.getQunatityPerUnit());
		Assert.assertEquals("UnitsInStock is not equal",
				newProduct.getUnitsInStock(), existingProduct.getUnitsInStock());
		Assert.assertEquals("UnitsOnOrder is not equal",
				newProduct.getUnitsOnOrder(), existingProduct.getUnitsOnOrder());
	}

	@Test
	public void TestForGetAllProducts() {

		Integer preCount = productDao.getAllProducts().size();
		productDao.createProduct(createNewProduct("TV", new BigDecimal(399.99),
				3, 12, 4));
		Integer postCount = productDao.getAllProducts().size();
		Assert.assertEquals("Count of product is not correct", preCount,
				preCount);
	}

	@Test
	public void TestForGetProductsByRange() {
		List<Product> products = productDao.getAllProducts();
		Integer startIndex = products.get(0).getProductID();
		Integer endingIndex = products.get(products.size() - 1).getProductID();
		if (endingIndex > startIndex) {
			List<Product> rangeProducts = productDao.getProductsByRange(
					products.get(1), products.get(products.size() - 1));
			Assert.assertEquals("Number products in the range is not correct",
					products.size() - 1, rangeProducts.size());
		} else {
			List<Product> rangeProducts = productDao.getProductsByRange(
					products.get(0), products.get(products.size() - 1));
			Assert.assertEquals("Number products in the range is not correct",
					products.size(), rangeProducts.size());
		}
	}

	@Test
	public void TestForDeleteProduct() {
		List<Product> products = productDao.getAllProducts();
		if (products.size() > 0) {
			Product product = products.get(0);
			productDao.deleteProduct(product);
			Product emptyProduct = productDao.getProductByID(product
					.getProductID());
			Assert.assertEquals("Product is not deleted with product ID"
					+ product.getProductID(), null, emptyProduct);
		}
	}

	@Test
	public void TestForUpdateProduct() {
		Product newProduct = createNewProduct("TV", new BigDecimal(399.99), 3,
				12, 4);
		Integer productID = productDao.createProduct(newProduct);
		Product existingProduct = productDao.getProductByID(productID);
		existingProduct.setName("Washing Machine");
		existingProduct.setPrice(new BigDecimal(399.99));
		productDao.saveProduct(existingProduct);

		Product updatedProduct = productDao.getProductByID(productID);
		Assert.assertEquals("Product not exist with the ID" + productID,
				productID, updatedProduct.getProductID());
		Assert.assertEquals("Product name is not equal",
				existingProduct.getName(), updatedProduct.getName());
		Assert.assertEquals("Product price is not equal",
				existingProduct.getPrice(), updatedProduct.getPrice());
		Assert.assertEquals("QunatityPerUnit is not equal",
				newProduct.getQunatityPerUnit(),
				existingProduct.getQunatityPerUnit());
		Assert.assertEquals("UnitsInStock is not equal",
				newProduct.getUnitsInStock(), existingProduct.getUnitsInStock());
		Assert.assertEquals("UnitsOnOrder is not equal",
				newProduct.getUnitsOnOrder(), existingProduct.getUnitsOnOrder());
	}

	private Product createNewProduct(String name, BigDecimal price,
			int qunatityPerUnit, int unitsInStock, int unitsOnOrder) {
		Product newProduct = new Product();
		newProduct.setName(name);
		newProduct.setPrice(price);
		newProduct.setQunatityPerUnit(qunatityPerUnit);
		newProduct.setUnitsInStock(unitsInStock);
		newProduct.setUnitsOnOrder(unitsOnOrder);
		return newProduct;
	}

}
