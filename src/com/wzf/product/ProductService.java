package com.wzf.product;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.wzf.utils.Page;
@Transactional
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

	public Page<Product> findByPage(int cid, int currentPage) {
		int numPerPage=12;
		int totalPages=0;
		Page<Product> page=new Page<Product>();
		page.setCurrentPage(currentPage);
		page.setNumPerPage(numPerPage);
		//查当前一级分类下的总记录数
		int totalRecords=productDao.findCountByCid(cid);
		page.setTotalRecords(totalRecords);
		if(totalRecords%numPerPage==0){
			totalPages=totalRecords/numPerPage;
		}else{
			totalPages=totalRecords/numPerPage+1;
		}
		page.setTotalPages(totalPages);
		
		int begin=(currentPage-1)*numPerPage;
		List<Product> list=productDao.findByPage(cid,begin,numPerPage);
		page.setList(list);
		return page;
	}

	public Product findByPid(Integer pid) {
		// TODO Auto-generated method stub
		return productDao.findByPid(pid);
	}

	public Page findByCsid(int csid, int currentPage) {
		int numPerPage=8;
		int totalPages=0;
		Page<Product> page=new Page<Product>();
		page.setCurrentPage(currentPage);
		page.setNumPerPage(numPerPage);
		//查当前一级分类下的总记录数
		int totalRecords=productDao.findCountByCsid(csid);
		page.setTotalRecords(totalRecords);
		//计算总页数
		if(totalRecords%numPerPage==0){
			totalPages=totalRecords/numPerPage;
		}else{
			totalPages=totalRecords/numPerPage+1;
		}
		page.setTotalPages(totalPages);
		
		int begin=(currentPage-1)*numPerPage;
		List<Product> list=productDao.findByPageAndCsid(csid,begin,numPerPage);
		page.setList(list);
		return page;
	}

	/**
	 * 后台查询所有商品
	 */
	public Page findByPage(int currentPage) {
		// 封装Page对象
		Page<Product> page=new Page<Product>();
		page.setCurrentPage(currentPage);
		int numPerPage=10;
		page.setNumPerPage(numPerPage);
		int totalRecords=productDao.findCount();
		page.setTotalRecords(totalRecords);
		int totalPages=0;
		if(totalRecords % numPerPage==0){
			totalPages=totalRecords/numPerPage;
		}else{
			totalPages=totalRecords/numPerPage+1;
		}
		page.setTotalPages(totalPages);
		int begin=(currentPage-1)*numPerPage;
		List<Product> list=productDao.findByPage(begin, numPerPage);
		page.setList(list);
		return page;
	}

	public void save(Product product) {
		productDao.save(product);
	}
	
}
