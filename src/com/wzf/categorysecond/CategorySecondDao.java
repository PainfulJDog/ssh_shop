package com.wzf.categorysecond;

import java.util.List;

import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.wzf.utils.PageHibernateCallback;

public class CategorySecondDao extends HibernateDaoSupport{

	public int findCount() {
		// 查二级分类的总记录数
		List<Long> list=(List<Long>) this.getHibernateTemplate().find("select count(*) from CategorySecond");
		if(list.size()!=0){
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<CategorySecond> findByPage(int begin, int numPerPage) {
		String hql="from CategorySecond";
		List<CategorySecond> list=this.getHibernateTemplate().execute(new PageHibernateCallback<CategorySecond>(hql, null, begin, numPerPage));
		if(list.size()!=0){
			return list;
		}
		return null;
	}

	public void save(CategorySecond categorySecond) {
		this.getHibernateTemplate().save(categorySecond);
	}

	public List<CategorySecond> findAll() {
		return (List<CategorySecond>) this.getHibernateTemplate().find("from CategorySecond");
	}

}
