package com.example.demo.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserInfo implements Serializable{

	private String syaId;
	
	private String name;
	
	private String address;
	
	private String phone;
	
	private String mail;
	
	private String password;
	
}
