package com.wzf.product;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wzf.category.Category;
import com.wzf.category.CategoryService;
import com.wzf.utils.Page;

public class ProductAction extends ActionSupport implements ModelDriven<Product>{
	private int cid;
	private int currentPage;
	private CategoryService categoryService;
	private ProductService productService;
	private Page page;
	private Product product=new Product();

	public void setCid(int cid) {
		this.cid = cid;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public Page getPage() {
		return page;
	}

	@Override
	public Product getModel() {
		return product;
	}
	
	public String findByCid(){
		//查询所有一级分类
		List<Category> categoryList=categoryService.findAllCategories();
		//获得值栈
		ActionContext.getContext().getValueStack().set("categoryList", categoryList);
		
		page=productService.findByPage(cid,currentPage);
		return "findByCidSuccess";
	}
	public String findByPid(){
		//查询所有一级分类
		List<Category> categoryList=categoryService.findAllCategories();
		//获得值栈
		ActionContext.getContext().getValueStack().set("categoryList", categoryList);
		product=productService.findByPid(product.getPid());
		return "findByPidSuccess";
	}


}
