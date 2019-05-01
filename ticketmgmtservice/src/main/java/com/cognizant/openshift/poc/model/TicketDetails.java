package com.cognizant.openshift.poc.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="ticket")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class TicketDetails implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer ticketid;
	private String title;
	private String description;
	private String priority;
	private String phone;
	@CreationTimestamp
	private LocalDateTime createdate;
	private String createby;
	@UpdateTimestamp
	private LocalDateTime lastupdatetime;
	private String lastupdatedby;
	private String assignedto;
	private String status;
	
	public Integer getTicketid() {
		return ticketid;
	}
	public void setTicketid(Integer ticketid) {
		this.ticketid = ticketid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public LocalDateTime getCreatedate() {
		return createdate;
	}
	public void setCreatedate(LocalDateTime createdate) {
		this.createdate = createdate;
	}
	public String getCreateby() {
		return createby;
	}
	public void setCreateby(String createby) {
		this.createby = createby;
	}
	public LocalDateTime getLastupdatetime() {
		return lastupdatetime;
	}
	public void setLastupdatetime(LocalDateTime lastupdatetime) {
		this.lastupdatetime = lastupdatetime;
	}
	public String getLastupdatedby() {
		return lastupdatedby;
	}
	public void setLastupdatedby(String lastupdatedby) {
		this.lastupdatedby = lastupdatedby;
	}
	public String getAssignedto() {
		return assignedto;
	}
	public void setAssignedto(String assignedto) {
		this.assignedto = assignedto;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "TicketDetails [ticketid=" + ticketid + ", title=" + title + ", description=" + description
				+ ", priority=" + priority + ", phone=" + phone + ", createdate=" + createdate + ", createby="
				+ createby + ", lastupdatetime=" + lastupdatetime + ", lastupdatedby=" + lastupdatedby + ", assignedto="
				+ assignedto + ", status=" + status + "]";
	}
}
