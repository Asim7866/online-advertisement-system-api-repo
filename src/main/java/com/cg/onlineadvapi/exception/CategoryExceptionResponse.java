package com.cg.onlineadvapi.exception;

public class CategoryExceptionResponse {
	private String categoryName;

	public CategoryExceptionResponse(String categoryName) {
		super();
		this.categoryName = categoryName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
