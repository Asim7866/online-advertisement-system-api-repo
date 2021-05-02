package com.cg.onlineadvapi.serviceImpl;

import java.util.List;


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

	@Override
	public Category addOrUpdateCategory(Category category) {
		List<Category> categoryList = categoryRepository.findAll();
		for (Category cat : categoryList) {
			if(cat.getCategoryName().equalsIgnoreCase(category.getCategoryName())){
				throw new CategoryException(category.getCategoryName() + " already exist");
			}
		}
		return category;
	}

//	@Override
//	public boolean duplicateCategoryCheck(Category category)
//	{	
//		if(categoryRepository.findByCategoryName(category.getCategoryName()).isEmpty())
//			return false;
//		else
//			return true;
//	}

}
