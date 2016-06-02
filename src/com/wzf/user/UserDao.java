package com.wzf.user;

import java.util.List;

import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

public class UserDao extends HibernateDaoSupport {

	public void save(User user) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(user);
	}

	public User findByCode(String code) {
		// TODO Auto-generated method stub
		List<User>list=(List<User>) this.getHibernateTemplate().find("from User where code=?", code);
		if(list.size()!=0){
			return list.get(0);
		}
		return null;
	}

	public void update(User userFromTable) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(userFromTable);
	}

	public User login(User user) {
		// TODO Auto-generated method stub
		List<User> listByUsername=(List<User>) this.getHibernateTemplate().find("from User where username=? and password=? and state =1", user.getUsername(),user.getPassword());
		List<User> listByEmail=(List<User>) this.getHibernateTemplate().find("from User where email=? and password=? and state =1", user.getUsername(),user.getPassword());
		if(listByUsername.size()!=0){
			return listByUsername.get(0);
		}else if (listByEmail.size()!=0) {
			return listByEmail.get(0);
		} 	
		return null;
	}

	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		List<User> list=(List<User>) this.getHibernateTemplate().find("from User where username=?", username);
		if(list.size()!=0){
			return list.get(0);
		}
		return null;
	}

	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		List<User> list=(List<User>) this.getHibernateTemplate().find("from User where email=?", email);
		if(list.size()!=0){
			return list.get(0);
		}
		return null;
	}

}
