package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BookUpdateMapper;
import com.example.demo.dto.BookUpdateRequest;

@Service
public class BookUpdateService {

	@Autowired
	private BookUpdateMapper bookUpdateMapper;

	//ユーザー情報更新
	//param userEdithRequest リクエストデータ

	public void update(BookUpdateRequest bookUpdateRequest) {
		bookUpdateMapper.update(bookUpdateRequest);
	}
	
	public void insert(BookUpdateRequest bookUpdateRequest) {
		bookUpdateMapper.insert(bookUpdateRequest);
	}
}
