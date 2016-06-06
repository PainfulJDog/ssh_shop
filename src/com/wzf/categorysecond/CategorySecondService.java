package com.wzf.categorysecond;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.wzf.utils.Page;
@Transactional
public class CategorySecondService {
	private CategorySecondDao categorySecondDao;

	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}

	public Page<CategorySecond> findByPage(int currentPage) {
		// 封装page
		Page<CategorySecond> page=new Page<CategorySecond>();
		page.setCurrentPage(currentPage);
		int numPerPage=10;
		page.setNumPerPage(numPerPage);
		int totalRecords=categorySecondDao.findCount();
		page.setTotalRecords(totalRecords);
		
		int totalPages=0;
		if(totalRecords % numPerPage==0){
			totalPages=totalRecords/numPerPage;
		}else{
			totalPages=totalRecords/numPerPage+1;
		}
		page.setTotalPages(totalPages);
		//每页显示的数据
		int begin=(currentPage-1)*numPerPage;
		List<CategorySecond> list=categorySecondDao.findByPage(begin,numPerPage);
		page.setList(list);
		return page;
	}

	public void save(CategorySecond categorySecond) {
		categorySecondDao.save(categorySecond);
	}

	public List<CategorySecond> findAll() {
		return categorySecondDao.findAll();
	}

}
