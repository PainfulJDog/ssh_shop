package com.wzf.category;

import java.util.List;

import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

public class CategoryDao extends HibernateDaoSupport {

	//查询所有一级分类
	public List<Category> findAllCategories() {
		return (List<Category>) this.getHibernateTemplate().find("from Category");
	}

	public void save(Category category) {
		this.getHibernateTemplate().save(category);
	}

	public void delete(Category category) {
		this.getHibernateTemplate().delete(category);
	}

	public Category findByCid(int cid) {
		return this.getHibernateTemplate().get(Category.class, cid);
	}

	public void update(Category category) {
		this.getHibernateTemplate().update(category);
	}

}
