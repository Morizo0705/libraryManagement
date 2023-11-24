package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BookReturnMapper;
import com.example.demo.dto.BookReturnRequest;

@Service
public class BookReturnService {
	
	@Autowired
	private BookReturnMapper bookReturnMapper;

	//ユーザー情報更新
	//param userEdithRequest リクエストデータ

	public void renthisupdate(BookReturnRequest bookReturnRequest) {
		bookReturnMapper.renthisupdate(bookReturnRequest);
	}
	
	public void libupdate(BookReturnRequest bookReturnRequest) {
		bookReturnMapper.libupdate(bookReturnRequest);
	}

}
