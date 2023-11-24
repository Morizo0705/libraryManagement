package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.example.demo.entity.BookInfo;

@Component
@Mapper
public interface BookInfoMapper {
	
	//ユーザー情報全件検索
	//@param user 検索用リクエストデータ
	//@return 検索結果
	List<BookInfo>findAll();
	

}
