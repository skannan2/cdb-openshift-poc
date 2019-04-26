package com.cognizant.openshift.poc.usermgmtservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.openshift.poc.usermgmtservice.bean.UserInfo;
import com.cognizant.openshift.poc.usermgmtservice.dao.UserDao;

@Service
@Transactional
public class UserServiceImp implements UserService {
	@Autowired
	UserDao userDao;


	public List<UserInfo> getUser() {
		// TODO Auto-generated method stub
		return userDao.getUser();
	}

	public UserInfo findById(int id) {
		// TODO Auto-generated method stub
		return userDao.findById(id);
	}
	
	public UserInfo findByUserName(String userName) {
		return userDao.findByUserName(userName).get(0);
	}

	public void createUser(UserInfo user) {
		// TODO Auto-generated method stub
		userDao.addUser(user);
	}

	public void deleteUserById(int id) {
		// TODO Auto-generated method stub
		userDao.delete(id);
	}
	@Override
	public UserInfo updatePartially(UserInfo user, int id) {
		userDao.updateCountry(user,id);
		return userDao.findById(id);
	}

	@Override
	public UserInfo update(UserInfo user,int id) {
		// TODO Auto-generated method stub
		return userDao.update(user, id);
	}

	@Override
	public UserInfo verifyUser(UserInfo user) {
		
		return null;
	}

}
