package com.wzf.admin;

public class AdminService {
	private AdminDao adminDao;

	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	public Administrator login(Administrator administrator) {
		return adminDao.login(administrator);
	}

}
