package com.example.demo.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class BookSearchRequest implements Serializable {

	private int book_id;
	
}