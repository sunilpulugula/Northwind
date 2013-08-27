package com.imaginea.productapp.services;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.imaginea.productapp.dao.ProductDAO;
import com.imaginea.productapp.model.Product;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTests
{

	private static final Integer	ID	= 1; 

	@Mock
	private ProductDAO						productDAO;

	@Mock
	private Product								product;

	private ProductService				productService;

	@Before
	public void setUp() {

		productService = new ProductServiceImpl(productDAO);
		when(productDAO.getProductByID(ID)).thenReturn(product);
		when(product.getName()).thenReturn("Imaginea");
		when(product.getPrice()).thenReturn(new BigDecimal(10));
		when(product.getQunatityPerUnit()).thenReturn(new Integer(5));
		when(product.getUnitsInStock()).thenReturn(new Integer(30));
		when(product.getUnitsOnOrder()).thenReturn(new Integer(2));
		when(productService.getAllProducts()).thenReturn(new ArrayList(Arrays.asList(product)));

	}

	@Test
	public void getProductByIDShouldReturnProduct() {
		Product existingProduct = productService.getProductByID(ID);
		verify(productDAO).getProductByID(ID);
		assertNotNull(existingProduct);
		assertThat(existingProduct, is(product));
	}

	@Test(expected = NullPointerException.class)
	public void getProductByIDShouldReturnNullPointerExceptionforIdIsNull() {
		productService.getProductByID(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getProductByIDShouldReturnIllegalArgumentExceptionforIdIsNegative() {
		productService.getProductByID(-1);
	}

	@Test
	public void getAllProductsShouldCallGetAllProductsInDAO() {
		List<Product> list = productService.getAllProducts();
		verify(productDAO).getAllProducts();
		assertThat("Number Of Products is not correct", list.size() == 1);
	}

	@Test
	public void createProductShouldCallCreateProductOnDAO() {
		productService.createProduct(product);
		verify(productDAO).createProduct(product);
	}

	@Test(expected = NullPointerException.class)
	public void createProductWithNameIsNull() {
		when(product.getName()).thenReturn(null);
		productService.createProduct(product);
	}

	@Test(expected = NullPointerException.class)
	public void createProductWithPriceIsNull() {
		when(product.getPrice()).thenReturn(null);
		productService.createProduct(product);
	}

	@Test(expected = IllegalArgumentException.class)
	public void createProductWithPriceIsNegative() {
		when(product.getPrice()).thenReturn(new BigDecimal(-1));
		productService.createProduct(product);
	}

	@Test(expected = IllegalArgumentException.class)
	public void createProductWithUnitsInStockIsNegative() {
		when(product.getUnitsInStock()).thenReturn(-1);
		productService.createProduct(product);
	}

	@Test(expected = IllegalArgumentException.class)
	public void createProductWithUnitsOnOrderIsNegative() {
		when(product.getUnitsOnOrder()).thenReturn(-1);
		productService.createProduct(product);
	}

	@Test(expected = IllegalArgumentException.class)
	public void createProductWithQunatityPerUnitIsNegative() {
		when(product.getQunatityPerUnit()).thenReturn(-1);
		productService.createProduct(product);
	}

	@Test
	public void updateProductShouldCallUpdateProductDAO() {
		when(product.getProductID()).thenReturn(ID);
		productService.updateProduct(product);
		verify(productDAO).updateProduct(product);
	}

	@Test(expected = NullPointerException.class)
	public void updatingProductWithNULL() {
		productService.updateProduct(null);
	}

	@Test
	public void deleteProductShouldCallUpdateProductDAO() {
		when(product.getProductID()).thenReturn(ID);
		productService.deleteProduct(product);
		verify(productDAO).deleteProduct(product);
	}

	@Test(expected = NullPointerException.class)
	public void deletingProductWithNULL() {
		productService.deleteProduct(null);
	}

	@Test(expected = NullPointerException.class)
	public void deletingProductWithProductIDasNULL() {
		when(product.getProductID()).thenReturn(null);
		productService.deleteProduct(null);
	}

	@Test
	public void applyDiscountOnAllProducts() {
		when(product.getProductID()).thenReturn(ID);
		productService.applyDiscount(new BigDecimal(20));
		verify(productDAO).updateProduct(product);

	}

	@Test(expected = NullPointerException.class)
	public void applyDiscountonProductsWithNULL() {
		productService.applyDiscount(null);
	}

	@Test(expected = NullPointerException.class)
	public void applyDiscountonProductsWithNegativeValue() {
		productService.applyDiscount(new BigDecimal(-1));
	}

}
