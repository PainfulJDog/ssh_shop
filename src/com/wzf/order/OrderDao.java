package com.wzf.order;

import java.util.List;

import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.wzf.user.User;
import com.wzf.utils.PageHibernateCallback;

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

	/**
	 * 查询订单总数
	 */
	public int findCount() {
		String hql="Select count(*) from Order";
		List<Long> list=(List<Long>) this.getHibernateTemplate().find(hql);
		if(list.size()!=0){
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<Order> findByPage(int begin, int numPerPage) {
		String hql="from Order order by ordertime desc";
		List<Order> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql, null, begin, numPerPage));
		if(list.size()!=0){
			return list;
		}
		return null;
	}

	public int findCount(int state) {
		// TODO Auto-generated method stub
		String hql="select count(*) from Order where state=?";
		List<Long> list=(List<Long>) this.getHibernateTemplate().find(hql,state);
		if(list.size()!=0){
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<Order> findByPage(int state, int begin, int numPerPage) {
		String hql="from Order where state=? order by ordertime desc ";
		List<Order> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql, new Object[]{state}, begin, numPerPage));
		if(list.size()!=0){
			return list;
		}
		return null;
	}

}
