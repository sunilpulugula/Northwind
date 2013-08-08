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
public class ProductAppController {

	@Autowired
	ProductService productService;

	private String message = null;

	private static final Logger logger = Logger
			.getLogger(ProductAppController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String retriveAllProducts(ModelMap model) {
		if (logger.isDebugEnabled()) {
			logger.debug("Strated Executing method index in Product_App_Controller");
		}
		List<Product> products = productService.getAllProducts();
		model.addAttribute("products", products);
		if (getMessage() != null) {
			model.addAttribute("message", getMessage());
			setMessage(null);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Ending execution of method index in Product_App_Controller");
		}
		return "index";
	}

	@RequestMapping("/delete/{productID}")
	public String deleteProduct(@PathVariable("productID") Integer productID) {

		if (logger.isDebugEnabled()) {
			logger.debug("Deleting product with product ID : " + productID);
		}
		productService.deleteProduct(productService.getProductByID(productID));
		if (logger.isDebugEnabled()) {
			logger.debug("Deletion done sucessfull with product ID : " + productID);
		}
		setMessage("Product with Product ID " + productID + " is deleted.");
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
		if (logger.isDebugEnabled()) {
			logger.debug("Updating detail of the product with product ID : "
					+ productID);
		}
		Product product = new Product();
		product.setProductID(productID);
		product.setName(productname);
		product.setPrice(unitPrice);
		product.setQunatityPerUnit(qunatityPerUnit);
		product.setUnitsInStock(unitsInStock);
		product.setUnitsOnOrder(unitsOnOrder);
		productService.saveProduct(product);
		setMessage("Product with Product ID " + product.getProductID()
				+ " is updated.");
		if (logger.isDebugEnabled()) {
			logger.debug("Updated detail of the product with product ID : "
					+ productID);
		}
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

		if (logger.isDebugEnabled()) {
			logger.debug("Applying Discount on all products");
		}
		BigDecimal discountPercentage = new BigDecimal(request
				.getParameter("discount"));
		List<Product> products = productService.getAllProducts();
		for (Product product : products) {
			BigDecimal price = product.getPrice();
				BigDecimal dicountPrice = price.subtract((discountPercentage.divide(new BigDecimal(100)).multiply(price)));
				product.setPrice(dicountPrice);
				productService.saveProduct(product);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Applied Discount on all products with a percentage :"
					+ discountPercentage);
		}
		setMessage("Discount of " + discountPercentage
				+ "% is applied on all Products ");
		return "redirect:/";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addProduct(HttpServletRequest request) {
		if (logger.isDebugEnabled()) {
			logger.debug("Adding new product");
		}
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
		productService.createProduct(product);
		setMessage("Product with Product ID " + product.getProductID()
				+ " is created.");
		if (logger.isDebugEnabled()) {
			logger.debug("Added New product to repository with Product ID :"
					+ product.getProductID());
		}
		return "redirect:/";
	}

	private void setMessage(String message) {
		this.message = message;
	}

	private String getMessage() {
		return message;
	}
}
