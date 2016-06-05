package com.wzf.order;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.wzf.user.User;

@Transactional
public class OrderService {
	private OrderDao orderDao;
	
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public Integer save(Order order) {
		// TODO Auto-generated method stub
		int oid=orderDao.save(order);
		return oid;
	}

	public Order findByOid(int oid) {
		// TODO Auto-generated method stub
		return orderDao.findByOid(oid);
	}

	public void update(Order currentOrder) {
		// TODO Auto-generated method stub
		orderDao.update(currentOrder);
	}

	public List<Order> findByUid(User userFromDB) {
		// TODO Auto-generated method stub
		
		return orderDao.findByUid(userFromDB);
	}

}
