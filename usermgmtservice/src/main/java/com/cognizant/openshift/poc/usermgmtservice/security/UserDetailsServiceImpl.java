package com.cognizant.openshift.poc.usermgmtservice.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cognizant.openshift.poc.usermgmtservice.bean.UserInfo;
import com.cognizant.openshift.poc.usermgmtservice.service.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo applicationUser = userService.findByUserName(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(applicationUser.getUserName(), applicationUser.getPassword(), Collections.emptyList());
    }

}
