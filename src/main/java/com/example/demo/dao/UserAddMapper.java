package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.UserAddRequest;

@Mapper
public interface UserAddMapper {
	
	//ユーザー情報登録
	//@param userAddRequest 登録用リクエストデータ	
	void save(UserAddRequest userRequest);

}