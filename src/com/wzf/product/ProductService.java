package com.wzf.product;

import java.util.List;

public class ProductService {
	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public List<Product> findHotProducts() {
		// TODO Auto-generated method stub
		return productDao.findHotProducts();
	}

	public List<Product> findLatestProducts() {
		// TODO Auto-generated method stub
		return productDao.findLatestProducts();
	}
	
}
