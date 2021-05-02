package com.cg.onlineadvapi.serviceTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cg.onlineadvapi.domain.Category;
import com.cg.onlineadvapi.repository.CategoryRepository;
import com.cg.onlineadvapi.serviceImpl.CategoryServiceImpl;

class CategoryServiceImplTest {

	@Mock
	private CategoryRepository categoryRepository;
	
	@InjectMocks
	private CategoryServiceImpl categoryServiceImpl;
	
	private Category category;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		category = new Category();
	}
	
	@Test
	public void test_addOrUpdateCategory_GivenCategory_ShouldReturnSavedCategory() {
		BDDMockito.given(categoryRepository.save(category))
		.willReturn(new Category("Mobile","Phone"));
		Category expCategory= categoryServiceImpl.addOrUpdateCategory(category);
		
		assertNotNull(expCategory);
		assertEquals("Mobile", expCategory.getCategoryName());
		assertEquals("Phone",expCategory.getCategory_description());
		
	}
	
}
