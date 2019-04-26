package com.cognizant.openshift.poc.usermgmtservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.cognizant.openshift.poc.usermgmtservice.bean.UserInfo;
import com.cognizant.openshift.poc.usermgmtservice.exception.ForbiddenException;
import com.cognizant.openshift.poc.usermgmtservice.service.UserService;


@RestController
@RequestMapping(value={"/user"})
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder; 

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserInfo> getUserById(@PathVariable("id") int id, Authentication authentication) {
        UserInfo user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<UserInfo>(HttpStatus.NOT_FOUND);
        }
        checkUserAccess(authentication, user);
        user.setPassword(null);
        return new ResponseEntity<UserInfo>(user, HttpStatus.OK);
    }

    @GetMapping(value = "/username/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserInfo> getUserByUserName(@PathVariable("userName") String userName, Authentication authentication) {
        UserInfo user = userService.findByUserName(userName);
        if (user == null) {
            return new ResponseEntity<UserInfo>(HttpStatus.NOT_FOUND);
        }
        checkUserAccess(authentication, user);
        user.setPassword(null);
        return new ResponseEntity<UserInfo>(user, HttpStatus.OK);
    }
    
	 @PostMapping(value="/create",headers="Accept=application/json")
	 public ResponseEntity<Void> createUser(@RequestBody UserInfo user, UriComponentsBuilder ucBuilder){
	     user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	     userService.createUser(user);
	     HttpHeaders headers = new HttpHeaders();
	     headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getUserId()).toUri());
	     return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	 }

	 @GetMapping(value="/get", headers="Accept=application/json")
	 public List<UserInfo> getAllUser() {	 
	  List<UserInfo> tasks=userService.getUser();
	  tasks.forEach(user -> user.setPassword(null));
	  return tasks;
	
	 }

	@PutMapping(value="/update", headers="Accept=application/json")
	public ResponseEntity<String> updateUser(@RequestBody UserInfo currentUser, Authentication authentication)
	{
	UserInfo user = userService.findById(currentUser.getUserId());
	if (user==null) {
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}
	checkUserAccess(authentication, user);
	userService.update(currentUser, currentUser.getUserId());
	return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{id}", headers ="Accept=application/json")
	public ResponseEntity<UserInfo> deleteUser(@PathVariable("id") int id, Authentication authentication){
		UserInfo user = userService.findById(id);
		if (user == null) {
			return new ResponseEntity<UserInfo>(HttpStatus.NOT_FOUND);
		}
		checkUserAccess(authentication, user);
		userService.deleteUserById(id);
		return new ResponseEntity<UserInfo>(HttpStatus.NO_CONTENT);
	}
	
	@PatchMapping(value="/{id}", headers="Accept=application/json")
	public ResponseEntity<UserInfo> updateUserPartially(@PathVariable("id") int id, @RequestBody UserInfo currentUser){
		UserInfo user = userService.findById(id);
		if(user ==null){
			return new ResponseEntity<UserInfo>(HttpStatus.NOT_FOUND);
		}
		UserInfo usr =	userService.updatePartially(currentUser, id);
		return new ResponseEntity<UserInfo>(usr, HttpStatus.OK);
	}
	
	private void checkUserAccess(Authentication authentication, UserInfo user) {
		if(!user.getUserName().equalsIgnoreCase(authentication.getName())) {
        	throw new ForbiddenException();
        }
	}
}
