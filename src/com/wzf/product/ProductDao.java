package com.wzf.product;

import java.util.Date;
import java.util.List;

import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

public class ProductDao extends HibernateDaoSupport {

	public List<Product> findHotProducts() {
		// 查热门商品，只显示10个
		this.getHibernateTemplate().find("", values);
		return null;
	}

	public List<Product> findLatestProducts() {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().find("", values);
		return null;
	}

	
}
