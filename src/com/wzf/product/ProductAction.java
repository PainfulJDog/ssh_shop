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
	//�ļ��ϴ���Ҫ����������
	private File upload;				//�ϴ����ļ�
	private String uploadContentType;	//�ϴ��ļ���MIME����
	private String uploadFileName;	//�ϴ����ļ�������

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
		//��ѯ����һ������
		List<Category> categoryList=categoryService.findAllCategories();
		//���ֵջ
		ActionContext.getContext().getValueStack().set("categoryList", categoryList);
		
		page=productService.findByPage(cid,currentPage);
		return "findByCidSuccess";
	}
	public String findByPid(){
		//��ѯ����һ������
		List<Category> categoryList=categoryService.findAllCategories();
		//���ֵջ
		ActionContext.getContext().getValueStack().set("categoryList", categoryList);
		product=productService.findByPid(product.getPid());
		return "findByPidSuccess";
	}
	/**
	 * ���ݶ�������id��ѯ��Ʒ
	 * @return
	 */
	public String findByCsid(){
		//��ѯ����һ������
		List<Category> categoryList=categoryService.findAllCategories();
		//���ֵջ
		ActionContext.getContext().getValueStack().set("categoryList", categoryList);
		
		page=productService.findByCsid(csid,currentPage);
		return "findByCsidSuccess";
	}
	/**
	 * ��̨��ѯ������Ʒ
	 */
	public String adminFindAll(){
		page=productService.findByPage(currentPage);
		return "adminFindAllSuccess";
	}
	/**
	 * ��ת�������Ʒҳ��
	 */
	public String addPage(){
		List<CategorySecond> categorySecondList=categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("categorySecondList", categorySecondList);
		return "addPageSuccess";
	}
	/**
	 * ������Ʒ���ϴ�ͼƬ
	 * @throws IOException 
	 */
	public String save() throws IOException{
		//�ļ��ϴ�����
		//����ϴ�·��
		String path=ServletActionContext.getServletContext().getRealPath("/products");
		String realPath=path+"/"+csid+"/"+uploadFileName;
		File diskFile=new File(realPath);
		//�ļ��ϴ�
		FileUtils.copyFile(upload, diskFile);
		//���浽���ݿ�
		//���ö�������
		CategorySecond categorySecond=new CategorySecond();
		categorySecond.setCsid(csid);
		product.setCategorySecond(categorySecond);
		//����ʱ��
		product.setPdate(new Date());
		//����ͼƬ�ϴ�·��
		product.setImage("products/"+csid+"/"+uploadFileName);
		
		productService.save(product);
		return "saveSuccess";
	}
}
