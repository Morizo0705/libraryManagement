package com.example.demo.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class BookInfo implements Serializable{

	@Id
	private int bookId;
	
	private String bookTitle;

	private String hurigana;
	
	private String rentStatus;

}