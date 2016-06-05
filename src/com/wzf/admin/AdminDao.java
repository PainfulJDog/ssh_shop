package com.wzf.admin;

import java.util.List;

import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

public class AdminDao extends HibernateDaoSupport{

	public Administrator login(Administrator administrator) {
		// TODO Auto-generated method stub
		List<Administrator> list=(List<Administrator>) this.getHibernateTemplate().find("from Administrator where aName=? and aPassword=?", administrator.getaName(),administrator.getaPassword());
		if(list.size()!=0){
			return list.get(0);
		}
		return null;
	}

}
