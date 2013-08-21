package com.imaginea.productapp.services;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.google.common.base.Preconditions.*;
import static com.imaginea.productapp.utilities.LoggerUtil.Message.*;

import com.imaginea.productapp.dao.ProductDAO;
import com.imaginea.productapp.dao.ProductDAOImpl;
import com.imaginea.productapp.model.Product;
import com.imaginea.productapp.utilities.LoggerUtil;

@Service("ProductService")
public class ProductServiceImpl implements ProductService
{

	private static final LoggerUtil	loggerUtil	= new LoggerUtil(Logger.getLogger(ProductDAOImpl.class));
	private final ProductDAO				productDao;

	@Autowired
	public ProductServiceImpl(ProductDAO productDao) {
		super();
		this.productDao = productDao;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Product> getAllProducts() {
		loggerUtil.log(INFO, "Reterving all products from inventory");
		return productDao.getAllProducts();
	}

	@Override
	@Transactional(readOnly = true)
	public Product getProductByID(Integer id) {
		checkNotNull(id, "Product ID should not be NULL");
		checkArgument(id >= 0, "Product Id should not be negative");
		loggerUtil.log(INFO, "Reterving a product with Product ID " + id + "from inventory");
		return productDao.getProductByID(id);
	}

	@Override
	@Transactional
	public void updateProduct(Product product) {
		checkPreCondition(product);
		checkNotNull(product.getProductID(), "Product ID should not be null");
		checkNotNull(productDao.getProductByID(product.getProductID()), "Product with this product ID is not availble in inventory.");
		loggerUtil.log(DEBUG, "Updating a product with Product ID " + product.getProductID() + "in inventory");
		productDao.updateProduct(product);
		loggerUtil.log(INFO, "Updated a product with Product ID " + product.getProductID() + " in inventory");
	}

	@Override
	@Transactional
	public void deleteProduct(Product product) {
		checkNotNull(product, "Product should not be NULL");
		checkNotNull(product.getProductID(), "Product ID should not be null");
		checkNotNull(productDao.getProductByID(product.getProductID()), "Product with this product ID is not availble in inventory.");
		loggerUtil.log(DEBUG, "Deleting a product with Product ID " + product.getProductID() + "in inventory");
		productDao.deleteProduct(product);
		loggerUtil.log(INFO, "Deleted a product with Product ID " + product.getProductID() + "in inventory");
	}

	@Override
	@Transactional
	public Integer createProduct(Product product) {
		checkPreCondition(product);
		loggerUtil.log(INFO, "Creating a new product in the inventory");
		return productDao.createProduct(product);
	}

	private void checkPreCondition(Product product) {
		checkNotNull(product, "Product should not be NULL");
		checkNotNull(product.getPrice(), "Product Price cant be NULL");
		checkNotNull(product.getName(), "Product Name cant be NULL");
		checkArgument(product.getPrice().intValue() >= 0, "Product Price should not be negative");
		checkArgument(product.getQunatityPerUnit() >= 0, "Quantity per Unit should not be negative");
		checkArgument(product.getUnitsInStock() >= 0, "Units in stock should not be negative");
		checkArgument(product.getUnitsOnOrder() >=0 ,"Units On Order cant be negative");
	}
	
	@Override
	@Transactional
	public void applyDiscount(BigDecimal discountPercentage)
	{
		checkNotNull(discountPercentage, "Discount Percentage should not be NULL");
		loggerUtil.log(DEBUG, "Applying Discount on all products");
		List<Product> products = getAllProducts();
		for (Product product : products) {
			Integer productID = product.getProductID();
			BigDecimal price = product.getPrice();
			BigDecimal dicountPrice = price.subtract((discountPercentage.divide(new BigDecimal(100)).multiply(price)));
			product.setPrice(dicountPrice);
			loggerUtil.log(DEBUG, "Executing the service save product for the product ID :" + productID);
			updateProduct(product);
		}
	}

}