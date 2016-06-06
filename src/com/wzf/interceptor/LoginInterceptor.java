package com.wzf.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.wzf.admin.Administrator;

public class LoginInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		Administrator existAdministrator=(Administrator) ServletActionContext.getRequest().getSession().getAttribute("existAdministrator");
		if(existAdministrator!=null){
			return actionInvocation.invoke();
		}else{
			ActionSupport action=(ActionSupport) actionInvocation.getAction();
			action.addActionError("您还没有登录，请先登录！");
			return "loginBack";
		}
	}

}
