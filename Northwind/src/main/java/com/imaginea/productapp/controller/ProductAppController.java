package com.imaginea.productapp.controller;

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
		if(getMessage() != null)
		{
			model.addAttribute("message", getMessage());
			setMessage(null);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Ending execution of method index in Product_App_Controller");
		}
		return "index";
	}

	@RequestMapping("/delete/{PID}")
	public String deleteProduct(@PathVariable("PID") Integer PID) {

		if (logger.isDebugEnabled()) {
			logger.debug("Deleting product with product ID : " + PID);
		}
		productService.deleteProduct(productService.getProductByID(PID));
		if (logger.isDebugEnabled()) {
			logger.debug("Deletion done sucessfull with product ID : " + PID);
		}
		setMessage("Product with Product ID "+PID+" is deleted.");
		return "redirect:/";
	}

	@RequestMapping("/edit/{PID}")
	public String editProduct(@PathVariable("PID") Integer PID, ModelMap model) {
		Product product = productService.getProductByID(PID);
		model.addAttribute("product", product);
		return "update";
	}

	@RequestMapping(value = "/edit/update", method = RequestMethod.POST)
	public String updateProduct(HttpServletRequest request) {

		Integer pid = Integer.parseInt(request.getParameter("Product_ID"));
		String productname = request.getParameter("Product_Name");
		Float price = Float.parseFloat(request.getParameter("price"));
		if (logger.isDebugEnabled()) {
			logger.debug("Updating detail of the product with product ID : "
					+ pid);
		}
		Product product = new Product();
		product.setPID(pid);
		product.setName(productname);
		product.setPrice(price);
		productService.saveProduct(product);
		setMessage("Product with Product ID "+product.getPID()+" is updated.");
		if (logger.isDebugEnabled()) {
			logger.debug("Updated detail of the product with product ID : "
					+ pid);
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
		Float discountPercentage = Float.parseFloat(request
				.getParameter("Discount"));
		List<Product> products = productService.getAllProducts();
		for (Product product : products) {
			if (product.getPrice() > 0) {
				product.setPrice(((100 - discountPercentage) * product
						.getPrice()) / 100);
				productService.saveProduct(product);
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Applied Discount on all products with a percentage :"+discountPercentage);
		}
		setMessage("Discount of "+discountPercentage+"% is applied on all Products ");
		return "redirect:/";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addProduct(HttpServletRequest request) {
		if (logger.isDebugEnabled()) {
			logger.debug("Adding new product");
		}
		String productname = request.getParameter("Product_Name");
		Float price = Float.parseFloat(request.getParameter("price"));
		Product product = new Product();
		product.setName(productname);
		product.setPrice(price);
		productService.createProduct(product);
		setMessage("Product with Product ID "+product.getPID()+" is created.");
		if (logger.isDebugEnabled()) {
			logger.debug("Added New product to repository with Product ID :"
					+ product.getPID());
		}
		return "redirect:/";
	}
	
	private void setMessage(String message)
	{
		this.message = message;
	}
	
	private String getMessage()
	{
		return message;
	}
}
