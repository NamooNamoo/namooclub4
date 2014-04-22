package com.namoo.club.dao;

import java.util.List;

import com.namoo.club.dto.Category;

public interface CategoryDao {

	 //List<Category> readAllCategories();

	 List<Category> readCategoriesByCommunityNo(int communityNo);

	 void createCategory(Category category);

	 void deleteCategory(int categoryNo);
}
