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
		logger.error("Starting index method in controller");
		List<Product> products = productService.getAllProducts();
		model.addAttribute("products", products);
/*		model.addAttribute("message","hey hello world");
		
		Product product = new Product();
		product.setName("pro1");
		product.setPID(123);
		product.setPrice(12.23f);
		Product product1 = new Product();
		product1.setName("pro2");
		product1.setPID(1234);
		product1.setPrice(123.23f);
		
		List<Product> productlist = new ArrayList<Product>();
		productlist.add(product);
		productlist.add(product1);
		model.addAttribute("products", productlist);
		
		logger.error("message : "+model.get("message"));
		logger.error("products : "+model.get("products"));
		logger.error("Ending index method in controller");
*/		
		return "index";
	}
}
