package com.cg.onlineadvapi.service;

import java.util.List;
import com.cg.onlineadvapi.domain.Category;

public interface CategoryService {

	/**
	 * This method is used to create category for user to post advertise managed by admin module
	 * @param category
	 * @return category detail
	 */
	public Category saveOrUpdate(Category category);
	
	public void deleteById(Integer categoryId);
	 
	public List<Category> viewAllCategory();

}
