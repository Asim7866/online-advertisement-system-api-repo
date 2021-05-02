package com.cg.onlineadvapi.web;

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


@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
//	@Autowired
//	private 
	
	@PostMapping("")
	public ResponseEntity<?> addCategory(@RequestBody Category category,BindingResult bindingResult){
		Category savedCategory = categoryService.addOrUpdateCategory(category);
		if(savedCategory.getCategoryName().equalsIgnoreCase(category.getCategoryName())) {
			throw new CategoryException(category.getCategoryName()+" already exist");
		}
		return new ResponseEntity<Category>(savedCategory,HttpStatus.CREATED);
	}
	
	
}
