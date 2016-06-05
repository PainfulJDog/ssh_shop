package com.wzf.index;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wzf.category.Category;
import com.wzf.category.CategoryService;
import com.wzf.product.Product;
import com.wzf.product.ProductService;

public class IndexAction extends ActionSupport {
	private CategoryService categoryService;
	private ProductService productService;
	//热门商品集合，提供getter
	private List<Product>hotProductList;
	//最新商品集合，提供getter
	private List<Product> latestProductList;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public List<Product> getHotProductList() {
		return hotProductList;
	}

	public List<Product> getLatestProductList() {
		return latestProductList;
	}

	@Override
	public String execute() throws Exception {
		//查询所有一级分类
				List<Category> categoryList=categoryService.findAllCategories();
				ActionContext.getContext().getSession().put("categoryList", categoryList);
				//注意如果使用ServletActionContext，跳转到指定页面后无法取到值，要刷新后才能迭代出值
//				ServletActionContext.getRequest().getSession().setAttribute("categoryList", categoryList);
				
				//查询热门商品
				hotProductList=productService.findHotProducts();
				//查询最新商品
				latestProductList=productService.findLatestProducts();
				return "index";
	}

//	public String index(){
//		//查询所有一级分类
//		List<Category> categoryList=categoryService.findAllCategories();
//		ActionContext.getContext().getSession().put("categoryList", categoryList);
//		//注意如果使用ServletActionContext，跳转到指定页面后无法取到值，要刷新后才能迭代出值
////		ServletActionContext.getRequest().getSession().setAttribute("categoryList", categoryList);
//		
//		//查询热门商品
//		hotProductList=productService.findHotProducts();
//		//查询最新商品
//		latestProductList=productService.findLatestProducts();
//		return "index";
//	}
}
