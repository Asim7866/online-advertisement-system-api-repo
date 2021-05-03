package com.cg.onlineadvapi.serviceImpl;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.onlineadvapi.domain.Category;
import com.cg.onlineadvapi.exception.CategoryException;
import com.cg.onlineadvapi.repository.CategoryRepository;
import com.cg.onlineadvapi.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService{

	Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Category saveOrUpdate(Category category) {
		logger.info("--For adding or updating CATEGORY--");
			String newCatergory = category.getCategoryName();
			logger.info("--Checking whether category name is duplicated or not--");
			List<Category> categoryList = categoryRepository.findAll();
			logger.info("--Iterating through list--");
			for (Category category2 : categoryList) {
				String existingCategory = category2.getCategoryName();
				logger.info("--Checks categoryName is present or not--");
				if(existingCategory.equals(newCatergory)) {
					logger.error("--Category already exist--");
					throw new CategoryException("Category already exist"); 
				}
			}
			logger.info(category.toString()+"--Category Saved--");
			return categoryRepository.save(category);
			
		}

	@Override
	public void deleteById(Integer categoryId) {
		logger.info("--Entered into deleteById method--");
		logger.info("--Checking whether category is present or not--");
		 Optional<Category> category = categoryRepository.findById(categoryId);
		 logger.info("--if category is present--");
		 if(category.isPresent()) {
			 logger.info("--Category deleted successfully--");
			 categoryRepository.deleteById(categoryId);
		 }else {
			 logger.error(categoryId+" not found");
			 throw new CategoryException(categoryId+" not found");
		 }
		
	}

	@Override
	public List<Category> viewAllCategory() {
		logger.info("--Entered into viewAllCategory method--");
		logger.info("--Checking list of Category in table--");
		List<Category> category = categoryRepository.findAll();
		logger.info("--Checks wether list is empty or not--");
		boolean result = category.isEmpty();
		if(result==true) {
			logger.error("--No Category available--");
			throw new CategoryException("No Category available");
		}else {
			logger.info("--Returned list of Category if present--");
			return category;
		}
	}

	
	}

