package com.cg.onlineadvapi.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.onlineadvapi.domain.Category;
import com.cg.onlineadvapi.exception.CategoryException;
import com.cg.onlineadvapi.service.CategoryService;
import com.cg.onlineadvapi.serviceImpl.UserServiceImpl;


@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	Logger logger = LoggerFactory.getLogger(CategoryController.class);

	
//////////////vishal:
	
	@PostMapping("/addCategory")
	public ResponseEntity<?> addCategory(@RequestBody Category category,BindingResult bindingResult){
		
		logger.info("For adding CATEGORY");
		Category savedCategory = categoryService.addOrUpdateCategory(category);
//		if(savedCategory.getCategoryName().equalsIgnoreCase(category.getCategoryName())) {
//			throw new CategoryException(category.getCategoryName()+" already exist");
//		}
		return new ResponseEntity<Category>(savedCategory,HttpStatus.CREATED);
	}
	
	
}
