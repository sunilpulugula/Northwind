package com.imaginea.productapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.imaginea.productapp.services.ProductService;

@Controller
public class ProductAppController {

	@Autowired
	ProductService productService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(ModelMap model)
	{
		//model.addAttribute("productList", productService.getAllProducts());
		model.addAttribute("helloworld","hey hello world");
		return "index";
	}
}
