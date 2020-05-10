package com.spring.entity;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="users")
public class User {
	
	private String name;
	private int age;
	private String email;
	private int phoneNumber;
	private String jobTitle;
	private String jobDescription;
	
	@Transient
	private String token;
	
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	
	@Override
	public String toString() {
		
		return "Name: " + name + ", Age: " + age + ", Email: " + email + ", PhoneNumber: " + phoneNumber + ", JobTitle: " + jobTitle + ", JobDescroption: " + jobDescription + ", Token: " + token;
	}
}
