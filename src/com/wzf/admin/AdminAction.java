package com.wzf.admin;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminAction	extends ActionSupport implements ModelDriven<Administrator> {
	private Administrator administrator=new Administrator();
	private AdminService adminService;
	@Override
	public Administrator getModel() {
		return administrator;
	}
	
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	/**
	 * 跳转到登录页面
	 */
	public String tologin(){
		return LOGIN;
	}
	public String top(){
		return "top";
	}
	public String left(){
		return "left";
	}
	public String main(){
		return "main";
	}
	public String bottom(){
		return "bottom";
	}
	
	
	/**
	 * 后台登录方法
	 */
	public String login(){
		Administrator existAdministrator=adminService.login(administrator);
		if(existAdministrator==null){
			this.addActionError("用户名或密码错误！");
			return LOGIN;
		}
		ServletActionContext.getRequest().getSession().setAttribute("existAdministrator", existAdministrator);
		return "loginSuccess";
	}
	public String toHomePage(){
		return"toHomePage";
	}

}
