package com.imaginea.productapp.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.imaginea.productapp.model.Product;
import com.imaginea.productapp.services.ProductService;

@Controller
public class ProductAppController
{

	@Autowired
	ProductService							productService;
	private String							message			= null;
	private final String				DEBUG				= "DEBUG";
	private final String				INFORMATION	= "INFO";
	private final String				WARNING			= "WARN";
	private final String				ERROR				= "ERROR";
	private final String				FATAL				= "FATAL";

	private static final Logger	logger			= Logger.getLogger(ProductAppController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String retriveAllProducts(ModelMap model) {
		logThisMessage("Strated executing retriveAllProducts API in Product_App_Controller", DEBUG);
		logThisMessage("Executing the service retrive all products ", DEBUG);
		List<Product> products = productService.getAllProducts();
		model.addAttribute("products", products);
		if (getMessage() != null) {
			model.addAttribute("message", getMessage());
			setMessage(null);
		}
		logThisMessage("Execution of the retriveAllProducts API in Product_App_Controller is done", DEBUG);
		return "index";
	}

	@RequestMapping("/delete/{productID}")
	public String deleteProduct(@PathVariable("productID") Integer productID) {
		logThisMessage("Deleting product with product ID : " + productID, INFORMATION);
		Product product = productService.getProductByID(productID);
		if (product != null) {
			logThisMessage("Executing the service delete product ", DEBUG);
			productService.deleteProduct(product);
			setMessage("Product with Product ID " + productID + " is deleted.");
			logThisMessage("Deletion of product with product ID : " + productID + " is done", INFORMATION);
			return "redirect:/";
		}
		logThisMessage("Product with product ID " + productID + " does not exist", ERROR);
		setMessage("Product with Product ID " + productID + " can not be deleted because product ID does no exist in the Repository");
		return "redirect:/";
	}

	@RequestMapping("/edit/{productID}")
	public String editProduct(@PathVariable("productID") Integer productID, ModelMap model) {
		Product product = productService.getProductByID(productID);
		model.addAttribute("product", product);
		return "update";
	}

	@RequestMapping(value = "/edit/update", method = RequestMethod.POST)
	public String updateProduct(HttpServletRequest request) {

		Integer productID = Integer.parseInt(request.getParameter("productID"));
		String productname = request.getParameter("productName");
		BigDecimal unitPrice = new BigDecimal(request.getParameter("price"));
		Integer qunatityPerUnit = Integer.parseInt(request.getParameter("qunatityPerUnit"));
		Integer unitsInStock = Integer.parseInt(request.getParameter("unitsInStock"));
		Integer unitsOnOrder = Integer.parseInt(request.getParameter("unitsOnOrder"));
		logThisMessage("Updating the product with product ID : " + productID, INFORMATION);
		Product product = new Product();
		product.setProductID(productID);
		product.setName(productname);
		product.setPrice(unitPrice);
		product.setQunatityPerUnit(qunatityPerUnit);
		product.setUnitsInStock(unitsInStock);
		product.setUnitsOnOrder(unitsOnOrder);
		logThisMessage("Executing the service save product ", DEBUG);
		if (productService.saveProduct(product)) {
			setMessage("Product with product ID " + product.getProductID() + " is updated.");
			logThisMessage("Product with product ID : " + productID + " updated", INFORMATION);
			return "redirect:/";
		}
		logThisMessage("Product with product ID " + productID + " failed to update", ERROR);
		return "redirect:/";
	}

	@RequestMapping(value = "/addProduct")
	public String addProduct() {
		return "addProduct";
	}

	@RequestMapping(value = "/applyDiscount")
	public String applyDiscount() {
		return "applyDiscount";
	}

	@RequestMapping(value = "/discount", method = RequestMethod.POST)
	public String applyDiscount(HttpServletRequest request) {

		logThisMessage("Applying Discount on all products", INFORMATION);
		BigDecimal discountPercentage = new BigDecimal(request.getParameter("discount"));
		List<Product> products = productService.getAllProducts();
		for (Product product : products) {
			Integer productID = product.getProductID();
			BigDecimal price = product.getPrice();
			BigDecimal dicountPrice = price.subtract((discountPercentage.divide(new BigDecimal(100)).multiply(price)));
			product.setPrice(dicountPrice);
			logThisMessage("Executing the service save product for the product ID :" + productID, DEBUG);
			if (productService.saveProduct(product)) {
				logThisMessage("Discount is applied on the product with product ID :" + productID, INFORMATION);
			}
			else {
				logThisMessage("Failed to apply discount on the product with product ID :" + productID, WARNING);
			}
		}
		logThisMessage("Applied Discount on all products(which are elgible for discount) with a percentage :" + discountPercentage, INFORMATION);
		setMessage("Discount of " + discountPercentage + "% is applied on all Products (which are elgible for discount)");
		return "redirect:/";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addProduct(HttpServletRequest request) {
		logThisMessage("Adding new product", DEBUG);
		String productname = request.getParameter("productName");
		BigDecimal unitPrice = new BigDecimal(request.getParameter("price"));
		Integer qunatityPerUnit = Integer.parseInt(request.getParameter("qunatityPerUnit"));
		Integer unitsInStock = Integer.parseInt(request.getParameter("unitsInStock"));
		Integer unitsOnOrder = Integer.parseInt(request.getParameter("unitsOnOrder"));
		Product product = new Product();
		product.setName(productname);
		product.setPrice(unitPrice);
		product.setQunatityPerUnit(qunatityPerUnit);
		product.setUnitsInStock(unitsInStock);
		product.setUnitsOnOrder(unitsOnOrder);
		logThisMessage("Executing the service create product", DEBUG);
		Integer productID = productService.createProduct(product);
		if (productID != null) {
			setMessage("Product with Product ID " + productID + " is created.");
			logThisMessage("Added new product to repository with product ID :" + productID, INFORMATION);
			return "redirect:/";
		}
		logThisMessage("Unable to add new product ro repository ", ERROR);
		setMessage("Failed to create new product");
		return "redirect:/";
	}

	private void setMessage(String message) {
		this.message = message;
	}

	private String getMessage() {
		return message;
	}

	private void logThisMessage(String message, String severity) {
		switch (severity) {
			case "DEBUG":
				if (logger.isDebugEnabled()) {
					logger.debug(message);
				}
				break;
			case "INFO":
				if (logger.isInfoEnabled()) {
					logger.info(message);
				}
				break;
			case "WARN":
				logger.warn(message);
				break;
			case "ERROR":
				logger.error(message);
				break;
			case "FATAL":
				logger.fatal(message);
				break;
		}
	}
}
