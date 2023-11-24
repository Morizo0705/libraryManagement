package com.example.demo.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserAddRequest implements Serializable {
	
	//社員番号
	@NotEmpty
	(message = "社員番号を入力してください")
	@Pattern
	(regexp = "^HP\\d{6}$", message = "HP◯◯◯◯◯◯の形で入力してください(◯は数値)")
	private String syaId;
	
	//名前
	@NotEmpty
	(message = "名前を入力して下さい")
	@Size
	(max = 100, message = "名前は100桁以内で入力して下さい")
	private String name;
	
	//住所
	@Size
	(max = 255, message ="住所は255桁以内で入力して下さい")
	private String address;
	
	//電話番号
	@Pattern
	(regexp = "^0\\d{9,10}$", message = "電話番号の形式で入力してください")
	private String phone;
	
	//メールアドレス
	@Pattern
	(regexp = "^[a-zA-Z]+\\.[a-zA-Z]+@hopes-ise\\.co\\.jp$", message = "HOPESのメールアドレスを正しく入力してください")
	private String mail;
	
	@Pattern
	(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$", message = "パスワードは最低1つの小文字、1つの大文字、1つの数字を含む（最低8文字以上）で登録してください")
	private String password;
	
}
