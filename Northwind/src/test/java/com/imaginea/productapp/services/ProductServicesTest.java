package com.imaginea.productapp.services;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
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
@Ignore
public class ProductServicesTest {

	@Autowired
	private ProductService productService;

	@Test
	public void TestForCreateProduct() {
		Product newProduct = createNewProduct("TV", 399.99f);
		Integer productID = productService.createProduct(newProduct);
		Product existingProduct = productService.getProductByID(productID);
		Assert.assertEquals("Product not exist with the ID" + productID,
				productID, existingProduct.getPID());
		Assert.assertEquals("Product name is not equal", newProduct.getName(),
				existingProduct.getName());
		Assert.assertEquals("Product price is not equal",
				newProduct.getPrice(), existingProduct.getPrice());
	}

	@Test
	public void TestForGetAllProducts() {
		Integer preCount = productService.getAllProducts().size();
		productService.createProduct(createNewProduct("TV", 399.99f));
		Integer postCount = productService.getAllProducts().size();
		Assert.assertEquals("Count of product is not correct", preCount,
				preCount);
	}

	@Test
	public void TestForUpdateProduct() {
		Product newProduct = createNewProduct("TV", 399.99f);
		Integer productID = productService.createProduct(newProduct);
		Product existingProduct = productService.getProductByID(productID);
		existingProduct.setName("Washing Machine");
		existingProduct.setPrice(699.99f);
		productService.saveProduct(existingProduct);

		Product updatedProduct = productService.getProductByID(productID);
		Assert.assertEquals("Product not exist with the ID" + productID,
				productID, updatedProduct.getPID());
		Assert.assertEquals("Product name is not equal",
				existingProduct.getName(), updatedProduct.getName());
		Assert.assertEquals("Product price is not equal",
				existingProduct.getPrice(), updatedProduct.getPrice());
	}

	@Test
	public void TestForDeleteProduct() {
		List<Product> products = productService.getAllProducts();
		if (products.size() > 0) {
			Product product = products.get(0);
			productService.deleteProduct(product);
			Product emptyProduct = productService.getProductByID(product
					.getPID());
			Assert.assertEquals("Product is not deleted with product ID"
					+ product.getPID(), null, emptyProduct);
		}
	}

	@Test
	public void TestForGetProductsByRange() {
		List<Product> products = productService.getAllProducts();
		Integer startIndex = products.get(0).getPID();
		Integer endingIndex = products.get(products.size() - 1).getPID();
		if (endingIndex > startIndex) {
			List<Product> rangeProducts = productService.getProductsByRange(
					products.get(1), products.get(products.size() - 1));
			Assert.assertEquals("Number products in the range is not correct",
					products.size() - 1, rangeProducts.size());
		} else {
			List<Product> rangeProducts = productService.getProductsByRange(
					products.get(0), products.get(products.size() - 1));
			Assert.assertEquals("Number products in the range is not correct",
					products.size(), rangeProducts.size());
		}
	}

	private Product createNewProduct(String name, float price) {
		Product newProduct = new Product();
		newProduct.setName(name);
		newProduct.setPrice(price);
		return newProduct;
	}
}
