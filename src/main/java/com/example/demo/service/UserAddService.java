package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserAddMapper;
import com.example.demo.dto.UserAddRequest;

@Service
public class UserAddService {
	
	@Autowired
	private UserAddMapper userAddMapper;
	
	public void save(UserAddRequest userAddRequest) {
		userAddMapper.save(userAddRequest);
	}

}
