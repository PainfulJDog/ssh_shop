package com.wzf.categorysecond;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wzf.category.Category;
import com.wzf.category.CategoryService;
import com.wzf.utils.Page;

public class CategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond>{
	private int currentPage;
	private CategorySecondService categorySecondService;
	private CategoryService categoryService;
	private int cid;
	private CategorySecond categorySecond=new CategorySecond();
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	@Override
	public CategorySecond getModel() {
		// TODO Auto-generated method stub
		return categorySecond;
	}
	/**
	 * ��ѯ���ж�������
	 */
	public String adminFindAll(){
		Page<CategorySecond> page=categorySecondService.findByPage(currentPage);
		ActionContext.getContext().getValueStack().set("page", page);
		return "adminFindAllSuccess";
	}
	/**
	 * ��ת����Ӷ�������ҳ��
	 */
	public String toAddCategorySecond(){
		//��ѯ����һ������
		List<Category> categoryList=categoryService.findAllCategories();
		//ѹջ
		ActionContext.getContext().getValueStack().set("categoryList", categoryList);
		return "toAddCategorySecondSuccess";
	}
	public String add(){
		Category category=new Category();
		category.setCid(cid);
		categorySecond.setCategory(category);
		categorySecondService.save(categorySecond);
		return "addSuccess";
	}

	
}
