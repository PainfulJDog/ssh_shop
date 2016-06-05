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
	//������Ʒ���ϣ��ṩgetter
	private List<Product>hotProductList;
	//������Ʒ���ϣ��ṩgetter
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
		//��ѯ����һ������
				List<Category> categoryList=categoryService.findAllCategories();
				ActionContext.getContext().getSession().put("categoryList", categoryList);
				//ע�����ʹ��ServletActionContext����ת��ָ��ҳ����޷�ȡ��ֵ��Ҫˢ�º���ܵ�����ֵ
//				ServletActionContext.getRequest().getSession().setAttribute("categoryList", categoryList);
				
				//��ѯ������Ʒ
				hotProductList=productService.findHotProducts();
				//��ѯ������Ʒ
				latestProductList=productService.findLatestProducts();
				return "index";
	}

//	public String index(){
//		//��ѯ����һ������
//		List<Category> categoryList=categoryService.findAllCategories();
//		ActionContext.getContext().getSession().put("categoryList", categoryList);
//		//ע�����ʹ��ServletActionContext����ת��ָ��ҳ����޷�ȡ��ֵ��Ҫˢ�º���ܵ�����ֵ
////		ServletActionContext.getRequest().getSession().setAttribute("categoryList", categoryList);
//		
//		//��ѯ������Ʒ
//		hotProductList=productService.findHotProducts();
//		//��ѯ������Ʒ
//		latestProductList=productService.findLatestProducts();
//		return "index";
//	}
}
