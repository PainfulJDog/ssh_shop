package com.wzf.category;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class CategoryService {
	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public List<Category> findAllCategories() {
		return categoryDao.findAllCategories();
	}

	public void save(Category category) {
		categoryDao.save(category);
	}

	public void delete(Category category) {
		// TODO Auto-generated method stub
		categoryDao.delete(category);
	}

	public Category findByCid(int cid) {
		// TODO Auto-generated method stub
		return categoryDao.findByCid(cid);
	}

	public void update(Category category) {
		categoryDao.update(category);
		
	}
	
}
