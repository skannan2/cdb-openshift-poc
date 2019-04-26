package com.cognizant.openshift.poc.usermgmtservice.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.openshift.poc.usermgmtservice.bean.UserInfo;

@Repository
public class UserDaoImp implements UserDao{
	@Autowired
	private SessionFactory sessionFactory;
	  
	public void addUser(UserInfo user) {
		// TODO Auto-generated method stub
		 Session session = sessionFactory.getCurrentSession();
		  session.save(user); 
	}

	public List<UserInfo> getUser() {
		// TODO Auto-generated method stub
		 Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<UserInfo> list= session.createCriteria(UserInfo.class).list();
		return list;
	}

	public UserInfo findById(int id) {
		// TODO Auto-generated method stub
		 Session session = sessionFactory.getCurrentSession();
		 UserInfo user=(UserInfo) session.get(UserInfo.class,id);
		return user;
	}
	
	public List<UserInfo> findByUserName(String userName) {
		 Session session = sessionFactory.getCurrentSession();
		 Query<UserInfo> query = session.createQuery("FROM UserInfo ui WHERE ui.userName = :userName");
		 query.setParameter("userName",userName);
		 List<UserInfo> results = query.list();
		return results;
	}

	public UserInfo update(UserInfo val, int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		UserInfo user =(UserInfo)session.get(UserInfo.class, id);
		user.setPhoneNumber(val.getPhoneNumber());
		user.setEmail(val.getEmail());
		user.setAddress(val.getAddress());
		session.update(user);
		return user;
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		UserInfo user = findById(id);
		session.delete(user);
	}
	@Override
	public UserInfo updateCountry(UserInfo val, int id){
		Session session = sessionFactory.getCurrentSession();
		UserInfo user = (UserInfo)session.load(UserInfo.class, id);
		user.setPhoneNumber(val.getPhoneNumber());
		user.setEmail(val.getEmail());
		user.setAddress(val.getAddress());
		return user;
	}

}
