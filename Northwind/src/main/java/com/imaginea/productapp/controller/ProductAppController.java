package com.imaginea.productapp.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.imaginea.productapp.model.Product;
import com.imaginea.productapp.services.ProductService;

@Controller
public class ProductAppController {

	@Autowired
	ProductService productService;
	
	private static final Logger logger = Logger.getLogger(ProductAppController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(ModelMap model)
	{
		if(logger.isDebugEnabled())
		{
			logger.debug("Strated Executing method index in Product_App_Controller");
		}
		List<Product> products = productService.getAllProducts();
		model.addAttribute("products", products);
		if(logger.isDebugEnabled())
		{
			logger.debug("Ending execution of method index in Product_App_Controller");
		}
		return "index";
	}
}
