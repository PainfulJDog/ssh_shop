package com.wzf.user;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class UserAction extends ActionSupport implements ModelDriven<User> {
	private UserService userService;
	private User user=new User();
	@Override
	public User getModel() {
		return user;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	/**
	 * 从主页跳转到注册页面
	 * @return
	 */
	public String registerPage(){
		return "registerPage";
	}
	/**
	 * 用户注册动作，调用注册服务
	 * @return
	 */
	@InputConfig(resultName="registerInput")
	public String register(){
		userService.register(user);
		this.addActionMessage("注册成功！请去邮箱激活！");
		return "registerSuccess";
	}
	/**
	 * 用户激活动作
	 * @return
	 */
	public String active(){
		User userFromTable=userService.findByCode(user.getCode());
		if(userFromTable!=null){
			userFromTable.setState(1);
			userService.update(userFromTable);
			this.addActionMessage("激活成功！请去登录！");
			return "activeResult";
		}
		this.addActionMessage("激活失败！您的操作有误！");
		return "activeResult";
	}
	
	/**
	 * 从主页跳转到登录页面
	 * @return
	 */
	public String loginPage(){
		return "loginPage";
	}
	/**
	 * 用户登录动作
	 * @return
	 */
	@InputConfig(resultName="loginInput")
	public String login(){
		User userFromDB=userService.login(user);
		if(userFromDB==null){
			this.addActionError("用户名或密码错误或用户未激活！");
			return "loginInput";
		}
		ServletActionContext.getRequest().getSession().setAttribute("userFromDB", userFromDB);
		return "loginSuccess";
	}
	public String checkUsername() throws IOException{
		User userFromDB=userService.findByUsername(user.getUsername());
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		if(userFromDB==null){
			//用户名可以使用
			response.getWriter().println("<font color='green'>用户名可以使用！</font>");
		}else{
			//用户名已经存在
			response.getWriter().println("<font color='red'>用户名已经存在！</font>");
		}
		return NONE;
	}
}
