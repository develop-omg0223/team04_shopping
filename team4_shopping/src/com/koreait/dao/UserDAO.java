package com.koreait.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	
//  철민 회원가입 메소드 
	public List<UserDTO> join(UserDTO userDTO) {
		System.out.println("아이디 입력 : ");
		userDTO.setId(sc.nextLine());
		for (UserDTO u : userList) {
			if (u.getId().equals(userDTO.getId())) {
				return userList;	// 아이디 중복확인  
			}
		}
		System.out.println("비밀번호 입력 : ");
		userDTO.setPw(sc.nextLine());
		System.out.println("이름 입력 : ");
		userDTO.setName(sc.nextLine());
		System.out.println("나이 입력 : ");
		userDTO.setAge(sc.nextInt());
		userList.add(userDTO);
		return userList;	// 회원가입 성공 

	}
	
// 철민 아이디 중복검사 메소드 
	public boolean idCheck (String id ) {
		for(UserDTO u : userList) {
			if (u.getId().equals(id)) {
				return true;	// 같은 아이디 존재 
			}
		}
		return false;	// 같은 아이디 없음 
	}
	

//	서울 로그인 메소드
	public String login(UserDTO userDTO) {
		String query = "SELECT USER_NAME FROM TBL_USER WHERE USER_ID = ? AND USER_PW = ?";
		UserDTO u = new UserDTO();
		
		try {
			connection = DBConnector.getConnection();	
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userDTO.getId());
			preparedStatement.setString(2, userDTO.getPw());
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			System.out.println("login() SQL 오류!!");
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) {
					resultSet.close();
				}
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("login() 연결 종료 오류!");
				e.printStackTrace();
			}
		}
		return u;
	}
	
//	서울 회원탈퇴 메소드 (아이디, 비밀번호, 이름 일치 시 탈퇴 가능)
	public boolean deleteUser(UserDTO userDTO) {
		String query = "DELETE FROM TBL_USER WHERE USER_ID = ? AND USER_PW = ? AND USER_NAME = ?";
		int result = 0;
		
		try {
			connection = DBConnector.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userDTO.getId());
			preparedStatement.setString(2, userDTO.getPw());
			preparedStatement.setString(3, userDTO.getName());
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("deleteUser() SQL 오류!!");
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("deleteUser() 연결 종료 오류!!");
				e.printStackTrace();
			}
		}
		return result > 0;	
	}
}
