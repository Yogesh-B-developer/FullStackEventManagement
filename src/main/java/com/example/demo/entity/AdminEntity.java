package com.example.demo.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="admincopy")
public class AdminEntity {

	@Id
	private String emailId;
	
	
	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}

	private String name;
	
	private String password;
	private String phoneNumber;
	private String role;
	
	
	


    @OneToMany(mappedBy = "adminForeignKey", cascade = CascadeType.ALL, orphanRemoval = true,fetch=FetchType.EAGER)
    private Set<EventsEntity> events = new HashSet<>();
    
    
	public AdminEntity() {
		super();
	}


	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Override
	public String toString() {
		return "AdminEntity [emailId=" + emailId + ", name=" + name + ", password=" + password + ", phoneNumber="
				+ phoneNumber + "]";
	}




}
