package com.wzf.category;

import java.util.List;

import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

public class CategoryDao extends HibernateDaoSupport {

	//��ѯ����һ������
	public List<Category> findAllCategories() {
		// TODO Auto-generated method stub
		return (List<Category>) this.getHibernateTemplate().find("from Category");
	}

}
