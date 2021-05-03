package com.cg.onlineadvapi.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlineadvapi.domain.Category;
import com.cg.onlineadvapi.exception.CategoryException;
import com.cg.onlineadvapi.repository.CategoryRepository;
import com.cg.onlineadvapi.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

	@Override
	public Category addOrUpdateCategory(Category category) {
		
		logger.info("For adding or updating CATEGORY");
		
		String newCatergory = category.getCategoryName();
		List<Category> categoryList = categoryRepository.findAll();
		for (Category category2 : categoryList) {
			String existingCategory = category2.getCategoryName();
			if(existingCategory.equals(newCatergory)) {
				throw new CategoryException("Category already  exist");
			}
		}
		return categoryRepository.save(category);
		
}		
}


