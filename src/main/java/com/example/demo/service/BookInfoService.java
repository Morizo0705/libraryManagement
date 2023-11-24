package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BookInfoMapper;
import com.example.demo.entity.BookInfo;

@Service
public class BookInfoService {

	//ユーザー情報Mapper
	@Autowired
	private BookInfoMapper bookInfoMapper;
	
	//ユーザー情報全件検索
	//return検索結果
	public List<BookInfo> findAll(){
		return bookInfoMapper.findAll();
		
	}
}
