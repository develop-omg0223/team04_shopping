package com.koreait.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.koreait.dto.UserDTO;

public class UserDAO {

	public Connection connection;
	public PreparedStatement preparedStatement;
	public ResultSet resultSet;

	private List<UserDTO> userList = new ArrayList<>();
	private Scanner sc = new Scanner(System.in);
//	철민      회원 DAO - 회원가입(아이디중복검사), 정보수정

//  철민 회원가입 메소드 
	public boolean join(UserDTO userDTO) {
		String query = "INSERT INTO TBL_USER (USER_NUMBER,USER_ID,USER_PW,USER_NAME,USER_PHONE,ADDR_NUMBER) "
				+ "VALUES(SEQ_USER.NEXTVAL, ?, ?, ?, ?, ?)";
		int result = 0;

		try {
			connection = DBConnector.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userDTO.getUserId());
			preparedStatement.setString(2, userDTO.getUserPw());
			preparedStatement.setString(3, userDTO.getUserName());
			preparedStatement.setString(4, userDTO.getUserPhone());
			preparedStatement.setString(5, userDTO.getAddrNumber());

			result = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("join() SQL 오류");
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("join() 접속 종료시 오류");
				e.printStackTrace();
			}
		}
		return result > 0;
	}

//  철민 아이디 중복검사 메소드 
	public boolean idCheck(String id) {
		String query = "SELECT USER_NUMBER FROM TBL_USER WHERE USER_ID = ?";
		int result = 0;

		try {
			connection = DBConnector.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				result = 1;
			}
		} catch (SQLException e) {
			System.out.println("idCheck() SQL 오류");
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("idCheck() 접속 종료시 오류");
				e.printStackTrace();
			}
		}
		return result > 0;
	}

//  철민 비밀번호 변경
	public boolean changePw(int userNumber, String oldPw, String newPW) {
		String query = "UPDATE TBL_USER SET USER_PW = ? WHERE USER_NUMBER = ? AND USER_PW = ? ";

		int result = 0;
		try {
			connection = DBConnector.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, newPW);
			preparedStatement.setInt(2, userNumber);
			preparedStatement.setString(3, oldPw);
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("changePw() SQL 오류");
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("changePw() 연결 해제 오류");
				e.printStackTrace();
			}
		}
		return result > 0;

	}

	public UserDTO findUserInfo(int userNumber) {
		UserDTO user = new UserDTO();
		String query = "SELECT USER_ID, USER_NAME, USER_PHONE, ADDR_NUMBER FROM TBL_USER WHERE USER_NUMBER = ?";

		try {
			connection = DBConnector.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, userNumber);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				user.setUserId(resultSet.getString("USER_ID"));
				user.setUserName(resultSet.getString("USER_NAME"));
				user.setUserPhone(resultSet.getString("USER_PHONE"));
				user.setAddrNumber(resultSet.getString("ADDR_NUMBER"));
			}
		} catch (SQLException e) {
			System.out.println("findUserInfo() SQL 오류");
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("findUserInfo() 연결 종료 오류");
				e.printStackTrace();
			}
		}
		return user;
	}

//  철민 회원정보 (전화번호, 주소 )변경 
	public boolean changeInfo(int userNumber, String phone, String addrNum, String pw) {
		String query = "UPDATE TBL_USER SET USER_PHONE = ?, ADDR_NUMBER = ?  WHERE USER_NUMBER = ? AND USER_PW =?";
		int result = 0;

		try {
			connection = DBConnector.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, phone);
			preparedStatement.setString(2, addrNum);
			preparedStatement.setInt(3, userNumber);
			preparedStatement.setString(4, pw);
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("changeInfo() SQL 오류");
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("changeInfo() 연결 해제 오류");
				e.printStackTrace();
			}

		}
		return result > 0;
	}

//	서울 로그인 메소드
	public UserDTO login(UserDTO userDTO) {

		String query = "SELECT USER_NUMBER, USER_ID, USER_PW, USER_NAME, USER_PHONE, ADDR_NUMBER FROM TBL_USER WHERE USER_ID = ? AND USER_PW = ?";

		UserDTO u = new UserDTO();

		try {
			connection = DBConnector.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userDTO.getUserId());
			preparedStatement.setString(2, userDTO.getUserPw());
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				u.setUserNumber(resultSet.getInt("USER_NUMBER"));
				u.setUserId(resultSet.getString("USER_ID"));
				u.setUserPw(resultSet.getString("USER_PW"));
				u.setUserName(resultSet.getString("USER_NAME"));
				u.setUserPhone(resultSet.getString("USER_PHONE"));
				u.setAddrNumber(resultSet.getString("ADDR_NUMBER"));

				System.out.println(u);
				return u;
			}

		} catch (SQLException e) {
			System.out.println("login() SQL 오류!!");
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("login() 연결 종료 오류!");
				e.printStackTrace();
			}
		}
		return null;
	}

//	서울 회원탈퇴 메소드 (아이디, 비밀번호, 이름 일치 시 탈퇴 가능)
	public boolean deleteUser(UserDTO userDTO) {
		String query = "DELETE FROM TBL_USER WHERE USER_ID = ? AND USER_PW = ? AND USER_NAME = ?";
		int result = 0;

		try {
			connection = DBConnector.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userDTO.getUserId());
			preparedStatement.setString(2, userDTO.getUserPw());
			preparedStatement.setString(3, userDTO.getUserName());
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("deleteUser() SQL 오류!!");
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
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
