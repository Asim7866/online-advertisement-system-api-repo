package com.cg.onlineadvapi.web;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.onlineadvapi.domain.Category;
import com.cg.onlineadvapi.service.CategoryService;
import com.cg.onlineadvapi.serviceImpl.MapValidationErrorService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/category") 
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	@ApiOperation(value = "Used to add category")
	@PostMapping("/addCategory")
	public ResponseEntity<?> addCategory( @Valid @RequestBody Category category,BindingResult result){
		ResponseEntity <?> errorMessage =mapValidationErrorService.mapValidationError(result);
		if(errorMessage!=null) return errorMessage;
		
		categoryService.saveOrUpdate(category);
		return new ResponseEntity<String>("Category added successfully",HttpStatus.CREATED);

	}

	@ApiOperation(value = "View all Category(for admin)")
	@GetMapping("/getAllCategory")
	public List<Category> getAllUser(){
		return categoryService.viewAllCategory();
	}
	
	@ApiOperation(value = "View all Category(for admin)")
	@DeleteMapping("/deletecategoryById/{category_id}")
	public void deleteById(@PathVariable Integer category_id ){
		categoryService.deleteById(category_id);
	}
	
	
	
}
