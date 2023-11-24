package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.example.demo.dto.BookReturnRequest;

@Component
@Mapper
public interface BookReturnMapper {

	//ユーザー情報更新
	//@param bookUpdateRequest 更新用リクエストデータ	
	void renthisupdate(BookReturnRequest bookReturnRequest);

	void libupdate(BookReturnRequest bookReturnRequest);
}
