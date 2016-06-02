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
	 * ����ҳ��ת��ע��ҳ��
	 * @return
	 */
	public String registerPage(){
		return "registerPage";
	}
	/**
	 * �û�ע�ᶯ��������ע�����
	 * @return
	 */
	@InputConfig(resultName="registerInput")
	public String register(){
		userService.register(user);
		this.addActionMessage("ע��ɹ�����ȥ���伤�");
		return "registerSuccess";
	}
	/**
	 * �û������
	 * @return
	 */
	public String active(){
		User userFromTable=userService.findByCode(user.getCode());
		if(userFromTable!=null){
			userFromTable.setState(1);
			userService.update(userFromTable);
			this.addActionMessage("����ɹ�����ȥ��¼��");
			return "activeResult";
		}
		this.addActionMessage("����ʧ�ܣ����Ĳ�������");
		return "activeResult";
	}
	
	/**
	 * ����ҳ��ת����¼ҳ��
	 * @return
	 */
	public String loginPage(){
		return "loginPage";
	}
	/**
	 * �û���¼����
	 * @return
	 */
	@InputConfig(resultName="loginInput")
	public String login(){
		User userFromDB=userService.login(user);
		if(userFromDB==null){
			this.addActionError("�û��������������û�δ���");
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
			//�û�������ʹ��
			response.getWriter().println("<font color='green'>�û�������ʹ�ã�</font>");
		}else{
			//�û����Ѿ�����
			response.getWriter().println("<font color='red'>�û����Ѿ����ڣ�</font>");
		}
		return NONE;
	}
}
