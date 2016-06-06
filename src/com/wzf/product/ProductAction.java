package com.wzf.product;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wzf.category.Category;
import com.wzf.category.CategoryService;
import com.wzf.categorysecond.CategorySecond;
import com.wzf.categorysecond.CategorySecondService;
import com.wzf.utils.Page;

public class ProductAction extends ActionSupport implements ModelDriven<Product>{
	private int cid;
	private int currentPage;
	private CategoryService categoryService;
	private ProductService productService;
	private Page page;
	private Product product=new Product();
	private int csid;
	private CategorySecondService categorySecondService;
	//文件上传需要的三个属性
	private File upload;				//上传的文件
	private String uploadContentType;	//上传文件的MIME类型
	private String uploadFileName;	//上传的文件的名称

	public void setCid(int cid) {
		this.cid = cid;
	}
	
	public int getCid() {
		return cid;
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
	
	public void setCsid(int csid) {
		this.csid = csid;
	}

	public int getCsid() {
		return csid;
	}

	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
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
	/**
	 * 根据二级分类id查询商品
	 * @return
	 */
	public String findByCsid(){
		//查询所有一级分类
		List<Category> categoryList=categoryService.findAllCategories();
		//获得值栈
		ActionContext.getContext().getValueStack().set("categoryList", categoryList);
		
		page=productService.findByCsid(csid,currentPage);
		return "findByCsidSuccess";
	}
	/**
	 * 后台查询所有商品
	 */
	public String adminFindAll(){
		page=productService.findByPage(currentPage);
		return "adminFindAllSuccess";
	}
	/**
	 * 跳转到添加商品页面
	 */
	public String addPage(){
		List<CategorySecond> categorySecondList=categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("categorySecondList", categorySecondList);
		return "addPageSuccess";
	}
	/**
	 * 保存商品，上传图片
	 * @throws IOException 
	 */
	public String save() throws IOException{
		//文件上传操作
		//获得上传路径
		String path=ServletActionContext.getServletContext().getRealPath("/products");
		String realPath=path+"/"+csid+"/"+uploadFileName;
		File diskFile=new File(realPath);
		//文件上传
		FileUtils.copyFile(upload, diskFile);
		//保存到数据库
		//设置二级分类
		CategorySecond categorySecond=new CategorySecond();
		categorySecond.setCsid(csid);
		product.setCategorySecond(categorySecond);
		//设置时间
		product.setPdate(new Date());
		//设置图片上传路径
		product.setImage("products/"+csid+"/"+uploadFileName);
		
		productService.save(product);
		return "saveSuccess";
	}
}
