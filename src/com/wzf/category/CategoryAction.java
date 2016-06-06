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
	 * ��̨��ѯ����һ������
	 */
	public String adminFindAll(){
		List<Category> categoryList=categoryService.findAllCategories();
		//ѹջ
		ActionContext.getContext().getValueStack().set("categoryList", categoryList);
		return "adminFindAllSuccess";
	}
	/**
	 * ��̨���һ������
	 */
	public String add(){
		categoryService.save(category);
		return "saveSuccess";
	}
	/**
	 * ��̨ɾ��һ������
	 */
	public String delete(){
		categoryService.delete(category);
		return "deleteSuccess";
	}
	/**
	 * ��̨�༭һ�����ࣺ��ѯ
	 */
	public String toEdit(){
		category=categoryService.findByCid(category.getCid());
		return "toEditSuccess";
	}
	/**
	 * ��̨�༭һ�����ࣺ����
	 */
	public String update(){
		categoryService.update(category);
		return "updateSuccess";
	}
}

