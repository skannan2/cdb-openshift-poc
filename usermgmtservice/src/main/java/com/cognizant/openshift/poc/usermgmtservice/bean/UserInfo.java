package com.cognizant.openshift.poc.usermgmtservice.bean;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter 
@Setter 
@NoArgsConstructor
@Table(name="UserInfo")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserInfo {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int userId;
	@Column(name="userName", unique=true, nullable=false)
	private String userName;
	@Column(name="password", nullable=false)
	private String password;
	@Column(name="email", unique=true, nullable=false)
	private String email;
	@Column(name="phoneNumber")
	private String phoneNumber;
	@Column(name="address")
	private String address;
	@Column(name="lastUpdatedTime", nullable=false)
	@UpdateTimestamp
	private Instant lastUpdatedTime;
	@Column(name="createdTime")
	@CreationTimestamp
	private Instant createdTime;
	

}
