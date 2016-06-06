package com.wzf.order;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.wzf.user.User;
import com.wzf.utils.Page;

@Transactional
public class OrderService {
	private OrderDao orderDao;
	
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public Integer save(Order order) {
		int oid=orderDao.save(order);
		return oid;
	}

	public Order findByOid(int oid) {
		return orderDao.findByOid(oid);
	}

	public void update(Order currentOrder) {
		orderDao.update(currentOrder);
	}

	public List<Order> findByUid(User userFromDB) {
		return orderDao.findByUid(userFromDB);
	}

	/**
	 * 业务层按状态查询订单
	 */
	public Page<Order> findByPage(int currentPage, int state) {
		Page<Order> page=new Page<Order>();
		page.setCurrentPage(currentPage);
		int numPerPage=10;
		page.setNumPerPage(numPerPage);
		int totalRecords=orderDao.findCount(state);
		page.setTotalRecords(totalRecords);
		int totalPages=0;
		if(totalRecords%numPerPage==0){
			totalPages=totalRecords/numPerPage;
		}else{
			totalPages=totalRecords/numPerPage+1;
		}
		page.setTotalPages(totalPages);
		int begin=(currentPage-1)*numPerPage;
		List<Order> list=orderDao.findByPage(state,begin,numPerPage);
		page.setList(list);
		return page;
	}

	/**
	 * 业务层查询所有订单
	 */
	public Page<Order> findByPage(int currentPage) {
		Page<Order> page=new Page<Order>();
		page.setCurrentPage(currentPage);
		int numPerPage=10;
		page.setNumPerPage(numPerPage);
		int totalRecords=orderDao.findCount();
		page.setTotalRecords(totalRecords);
		int totalPages=0;
		if(totalRecords%numPerPage==0){
			totalPages=totalRecords/numPerPage;
		}else{
			totalPages=totalRecords/numPerPage+1;
		}
		page.setTotalPages(totalPages);
		int begin=(currentPage-1)*numPerPage;
		List<Order> list=orderDao.findByPage(begin,numPerPage);
		page.setList(list);
		return page;
	}

}
