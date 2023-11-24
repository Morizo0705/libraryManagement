package com.example.demo.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class BookReturnRequest implements Serializable{
	
	//社員番号
	@NotEmpty
	(message = "社員番号を入力してください")
	@Pattern
	(regexp = "^HP\\d{6}$", message = "HP◯◯◯◯◯◯の形で入力してください(◯は数値)")
	private String syaId;
	
	//ユーザーID
	@NotNull
	private int bookId;
}
