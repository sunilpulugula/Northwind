package com.imaginea.productapp.controller;

import static com.imaginea.productapp.utilities.LoggerUtil.Message.DEBUG;
import static com.imaginea.productapp.utilities.LoggerUtil.Message.ERROR;
import static com.imaginea.productapp.utilities.LoggerUtil.Message.INFO;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imaginea.productapp.dao.ProductDAOImpl;
import com.imaginea.productapp.model.Product;
import com.imaginea.productapp.services.ProductService;
import com.imaginea.productapp.utilities.LoggerUtil;

@Controller
@RequestMapping(value = "/productApp")
public class ProductAppController
{

	private final ProductService	productService;

	private final LoggerUtil			loggerUtil	= new LoggerUtil(Logger.getLogger(ProductDAOImpl.class));

	@Autowired
	public ProductAppController(ProductService productService) {
		this.productService = productService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String retriveAllProducts(ModelMap model) {
		loggerUtil.log(DEBUG, "Retriving all products from the inventory.");
		List<Product> products = productService.getAllProducts();
		model.addAttribute("products", products);
		return "index";
	}

	@RequestMapping("/delete/{productID}")
	public String deleteProduct(@PathVariable("productID") Integer productID) {
		loggerUtil.log(INFO, "Deleting product with product ID : " + productID);
		try {
			Product product = productService.getProductByID(productID);
			productService.deleteProduct(product);
			loggerUtil.log(INFO, "Product with product ID " + productID + " is deleted");
		}
		catch (IllegalArgumentException | NullPointerException e) {
			loggerUtil.log(ERROR, "Failed to delete Product");
			loggerUtil.log(ERROR, e.getStackTrace().toString());
		}
		return "redirect:/productApp/";
	}

	@RequestMapping("/edit/{productID}")
	public String editProduct(@PathVariable("productID") Integer productID, ModelMap model) {
		Product product = productService.getProductByID(productID);
		model.addAttribute("product", product);
		return "update";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public @ResponseBody
	String editProduct(@ModelAttribute(value = "product") Product product, BindingResult result) {
		loggerUtil.log(INFO, "Updating the product with product ID : " + product.getProductID());
		try {
			productService.updateProduct(product);
			return "Product with product ID " + product.getProductID() + " is updated.";
		}
		catch (IllegalArgumentException | NullPointerException e) {
			loggerUtil.log(ERROR, "Failed to Update Product with Product ID :" + product.getProductID());
			loggerUtil.log(ERROR, e.getStackTrace().toString());
			return "Product with product ID " + product.getProductID() + " is not updated.";
		}

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
	public @ResponseBody
	String applyDiscount(@RequestParam(value = "discount") BigDecimal discount) {
		try {
			productService.applyDiscount(discount);
			String message = "Discount of " + discount + "% is applied on all products";
			loggerUtil.log(INFO, message);
			return message;
		}
		catch (IllegalArgumentException | NullPointerException e) {
			loggerUtil.log(ERROR, "Failed to apply discount on all products");
			loggerUtil.log(ERROR, e.getStackTrace().toString());
			return "Failed to apply discount on all products ";
		}

	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody
	String addProduct(@ModelAttribute(value = "product") Product product, BindingResult result) {
		loggerUtil.log(DEBUG, "Adding new product");
		try {
			Integer productID = productService.createProduct(product);
			String message = " New Product is added in the Inventory with the product ID :" + productID;
			loggerUtil.log(INFO, message);
			return message;
		}
		catch (IllegalArgumentException | NullPointerException e) {
			loggerUtil.log(ERROR, "Failed to create product in inventory");
			loggerUtil.log(ERROR, e.getStackTrace().toString());
			return "Failed to create product in inventory";
		}

	}

}
