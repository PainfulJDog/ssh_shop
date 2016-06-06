package com.wzf.category;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CategoryAction extends ActionSupport implements ModelDriven<Category>{
	private CategoryService categoryService;
	private Category category=new Category();
	
	@Override
	public Category getModel() {
		return category;
	}
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	/**
	 * 后台查询所有一级分类
	 */
	public String adminFindAll(){
		List<Category> categoryList=categoryService.findAllCategories();
		//压栈
		ActionContext.getContext().getValueStack().set("categoryList", categoryList);
		return "adminFindAllSuccess";
	}
	/**
	 * 后台添加一级分类
	 */
	public String add(){
		categoryService.save(category);
		return "saveSuccess";
	}
	/**
	 * 后台删除一级分类
	 */
	public String delete(){
		categoryService.delete(category);
		return "deleteSuccess";
	}
	/**
	 * 后台编辑一级分类：查询
	 */
	public String toEdit(){
		category=categoryService.findByCid(category.getCid());
		return "toEditSuccess";
	}
	/**
	 * 后台编辑一级分类：更新
	 */
	public String update(){
		categoryService.update(category);
		return "updateSuccess";
	}
}

