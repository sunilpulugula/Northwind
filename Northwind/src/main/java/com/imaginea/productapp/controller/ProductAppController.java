package com.imaginea.productapp.controller;

import java.util.List;

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

	private static final Logger logger = Logger
			.getLogger(ProductAppController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String retriveAllProducts(ModelMap model) {
		if (logger.isDebugEnabled()) {
			logger.debug("Strated Executing method index in Product_App_Controller");
		}
		List<Product> products = productService.getAllProducts();
		model.addAttribute("products", products);
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
		return "redirect:/";
	}

	@RequestMapping("/edit/{PID}")
	public String editProduct(@PathVariable("PID") Integer PID, ModelMap model) {
		logger.error("Editing product with product id : " + PID);
		Product product = productService.getProductByID(PID);
		model.addAttribute("product", product);
		return "update";
	}

}
