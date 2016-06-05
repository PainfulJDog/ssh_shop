package com.wzf.order;

import java.util.List;

import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.wzf.user.User;

public class OrderDao extends HibernateDaoSupport{

	public int save(Order order) {
		// TODO Auto-generated method stub
		int oid=(int) this.getHibernateTemplate().save(order);
		return oid;
	}

	public Order findByOid(int oid) {
		return this.getHibernateTemplate().get(Order.class, oid);
	}

	public void update(Order currentOrder) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(currentOrder);
	}

	public List<Order> findByUid(User userFromDB) {
		// TODO Auto-generated method stub
		List<Order> orderList=(List<Order>) this.getHibernateTemplate().find("from Order o where o.user.uid=? order by ordertime desc", userFromDB.getUid());
		return orderList;
	}

}
