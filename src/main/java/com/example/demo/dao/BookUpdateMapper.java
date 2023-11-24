package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.example.demo.dto.BookUpdateRequest;

@Component
@Mapper
public interface BookUpdateMapper {

	//ユーザー情報更新
	//@param bookUpdateRequest 更新用リクエストデータ	
	void update(BookUpdateRequest bookUpdateRequest);

	void insert(BookUpdateRequest bookUpdateRequest);
	
}
