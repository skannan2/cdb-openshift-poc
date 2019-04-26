package com.cognizant.openshift.poc.usermgmtservice.dao;

import java.util.List;

import com.cognizant.openshift.poc.usermgmtservice.bean.UserInfo;

public interface UserDao {
	public void addUser(UserInfo user);
	public List<UserInfo> getUser();
	public UserInfo findById(int id);
	public List<UserInfo> findByUserName(String userName);
	public UserInfo update(UserInfo user, int id);
	public UserInfo updateCountry(UserInfo user, int id);
	public void delete(int id);
}
