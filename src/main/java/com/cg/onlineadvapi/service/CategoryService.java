package com.cg.onlineadvapi.service;

import com.cg.onlineadvapi.domain.Category;

public interface CategoryService {
	
	/**
	 * This addCategory method will add new or update existing category in the database
	 * @param Category to be updated/added
	 * @return Category which is updated/added
	 */
	public  Category addOrUpdateCategory(Category category);
	
	
	

	
}
