package com.wzf.product;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.wzf.utils.PageHibernateCallback;

public class ProductDao extends HibernateDaoSupport {

	// ��������Ʒ��ֻ��ʾ10��
	public List<Product> findHotProducts() {
//		DetachedCriteria criteria=DetachedCriteria.forClass(Product.class);
//		criteria.add(Restrictions.eq("is_hot", 1));
//		List<Product> list=(List<Product>) this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		
		//��һ�ַ�ҳ����
		String hql="from Product where is_hot=?";
		List<Product> list=(List<Product>) this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{1}, 0, 10));
		return list;
	}

	public List<Product> findLatestProducts() {
		String hql="from Product order by pdate desc";
		List<Product> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, null, 0, 10));
		return list;
	}

	
	/**
	 * ĳһ�������µ���Ʒ����
	 * @param cid
	 * @return
	 */
	public Integer findCountByCid(int cid) {
//		String hql="select count(*) from Product p join p.categotySecond cs join cs.category c where c.cid=?";
		String hql="select count(*) from Product p,CategorySecond cs where p.categorySecond=cs and cs.category.cid=?";
		List<Long> list=(List<Long>) this.getHibernateTemplate().find(hql, cid);
		System.out.println("==============================:"+list.get(0).intValue());
		return list.get(0).intValue();
	}

	public List<Product> findByPage(int cid, int begin, int numPerPage) {
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid = ?";
//		String hql="select p from Product p,CategorySecond cs where p.categorySecond=cs and cs.category.cid=?";
//		String hql="select p from Product p where 1=?";
		List<Product> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{cid}, begin,numPerPage));
		return list;
	}

	public Product findByPid(Integer pid) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(Product.class, pid);
	}

	/**
	 * ͳ��ĳ�����������µļ�¼���������ڼ�����ҳ������Ϣ
	 * @param csid
	 * @return
	 */
	public int findCountByCsid(int csid) {
		// TODO Auto-generated method stub
		String hql="select count(*) from Product p join p.categorySecond cs where cs.csid=?";
		
		List<Long> list=(List<Long>) this.getHibernateTemplate().find(hql, csid);
		if(list.size()!=0){
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<Product> findByPageAndCsid(int csid, int begin, int numPerPage) {
		String hql1="select p from Product p,CategorySecond cs where p.categorySecond=cs and cs.csid=1";
		String hql="select p from Product p join p.categorySecond cs where cs.csid=?";
		List<Product> listTest=(List<Product>) this.getHibernateTemplate().find(hql1);
		List<Product> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{csid}, begin,numPerPage));
		return list;
	}

	public int findCount() {
		String hql="select count(*) from Product";
		List<Long> list=(List<Long>) this.getHibernateTemplate().find(hql);
		if(list.size()!=0){
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<Product> findByPage(int begin, int numPerPage) {
		String hql="from Product";
		List<Product> list=this.getHibernateTemplate().execute(new PageHibernateCallback<>(hql, null, begin, numPerPage));
		if(list.size()!=0){
			return list;
		}
		return null;
	}

	public void save(Product product) {
		this.getHibernateTemplate().save(product);
	}

	
}
