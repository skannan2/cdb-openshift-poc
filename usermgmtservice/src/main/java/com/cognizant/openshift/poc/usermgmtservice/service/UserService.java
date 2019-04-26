package com.cognizant.openshift.poc.usermgmtservice.service;

import java.util.List;

import com.cognizant.openshift.poc.usermgmtservice.bean.UserInfo;

public interface UserService {
	public void createUser(UserInfo user);
	public List<UserInfo> getUser();
	public UserInfo findById(int id);
	public UserInfo findByUserName(String userName);
	public UserInfo update(UserInfo user, int id);
	public void deleteUserById(int id);
	public UserInfo updatePartially(UserInfo user, int id);
	public UserInfo verifyUser(UserInfo user);
}
