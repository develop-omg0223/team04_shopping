package com.koreait.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.koreait.model.UserDTO;

public class UserDAO {

	public Connection connection;
	public PreparedStatement preparedStatement;
	public ResultSet resultSet;

	private List<UserDTO> userList = new ArrayList<>();
	private Scanner sc = new Scanner(System.in);
//	철민      회원 DAO - 회원가입(아이디중복검사), 정보수정

//	서울 로그인 메소드
	public boolean login(String id, String pw) {
		for (UserDTO u : userList) {
			if (u.getId().equals(id) && u.getPw().equals(pw)) {
				return true; // 로그인 성공
			}
		}
		return false; // 로그인 실패
	}
	
//	서울 회원탈퇴 메소드 (아이디, 비밀번호, 이름 일치 시 탈퇴 가능)
	public boolean deleteUser(String id, String pw, String name) {
		for(int i = 0; i < userList.size; i++) {
			UserDTO u = userList.get(i);
			if(u.getId().equals(id) && u.getPw().equals(pw) && u.getName().equals(name)) {
				userList.remove(i);
				return true;
			}
		}
		return false;
	}
}
