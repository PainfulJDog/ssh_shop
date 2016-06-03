package com.wzf.user;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class UserAction extends ActionSupport implements ModelDriven<User> {
	private UserService userService;
	private User user=new User();
	private String checkCode;//����ҳ�����û��������֤�루Ҫ��setter��
	@Override
	public User getModel() {
		return user;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
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
		//��session�л�ȡ��֤���ֵ
		String checkCodeFromSession=(String) ServletActionContext.getRequest().getSession().getAttribute("checkCode");
		if(checkCode==null|| !checkCode.equalsIgnoreCase(checkCodeFromSession)){
			this.addActionError("��֤�����");
			return "registerInput";
		}
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
	/**
	 * ע��ʱ���첽У���û����Ƿ����
	 * @return
	 * @throws IOException
	 */
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
	/**
	 * ע��ʱ���첽У�������Ƿ����
	 * @return
	 * @throws IOException
	 */
	public String checkEmail() throws IOException{
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String email=user.getEmail();
		String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(email);
		if(matcher.matches()){
			User userFromDB=userService.findByEmail(email);
			if(userFromDB==null){
				//�û�������ʹ��
				response.getWriter().println("<font color='green'>�������ʹ�ã�</font>");
			}else{
				//�û����Ѿ�����
				response.getWriter().println("<font color='red'>�����ѱ�ʹ�ã�</font>");
			}
			
		}else{
			response.getWriter().println("<font color='red'>�����ʽ����ȷ��</font>");
		}
		
		return NONE;
	}
	/**
	 * ע��ʱ���첽У����֤���Ƿ�������ȷ
	 * @return
	 * @throws IOException
	 */
	public String checkCheckCode() throws IOException{
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String checkCodeFromSession=(String) ServletActionContext.getRequest().getSession().getAttribute("checkCode");
		if(checkCode==null|| !checkCode.equalsIgnoreCase(checkCodeFromSession)){
			response.getWriter().println("<font color='red'>��֤����������</font>");
		}else{
			response.getWriter().println("<font color='green'>��֤��������ȷ��</font>");
		}
		return NONE;
		
	}
	/**
	 * �û�ע������
	 * @return
	 */
	public String logout(){
		ServletActionContext.getRequest().getSession().invalidate();
		return "logoutSuccess";
	}
}
