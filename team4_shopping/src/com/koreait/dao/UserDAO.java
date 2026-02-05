package com.koreait.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.koreait.dto.UserDTO;

/**
 * @author 오명근, 윤철민, 서서울
 * @since jdk17
 * 
 * 사용자 관련 데이터베이스 접근을 담당하는 DAO 클래스.
 *
 * TBL_USER 테이블을 대상으로 회원가입, 로그인, 회원정보 조회, 비밀번호 변경, 회원정보 수정, 회원탈퇴 기능을 제공한다.
 *
 */

public class UserDAO {

	public Connection connection;
	public PreparedStatement preparedStatement;
	public ResultSet resultSet;


	/**
	 * @author 윤철민
	 * 
	 * 사용자 정보를 입력받아 회원가입을 처리한다.
	 *
	 * SEQ_USER 시퀀스를 사용하여 USER_NUMBER를 순서대로 생성하며, 전달받은 사용자 정보를 TBL_USER 테이블에 저장한다.
	 *
	 * @param userDTO
	 * @return 회원가입 성공 시 true, 실패 시 false 반환
	 */
	
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


	/**
	 * @author 윤철민
	 * 
	 * 입력받은 아이디의 중복 여부를 확인한다.
	 *
	 * TBL_USER 테이블에서 USER_ID가 존재하는지 조회하여 이미 존재하는 경우 중복으로 판단한다.
	 *
	 * @param id
	 * @return 아이디가 이미 존재하면 true, 존재하지 않으면 false 반환
	 */
	
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

	/**
	 * @author 윤철민
	 * 
	 * 기존 비밀번호가 일치하는 경우 새로운 비밀번호로 변경한다.
	 *
	 * USER_NUMBER와 기존 비밀번호(USER_PW)가 일치하는 사용자에 한해 비밀번호를 새로운 값으로 수정한다.
	 *
	 * @param userNumber, oldPw, newPW
	 * @return 비밀번호 변경 성공 시 true, 실패 시 false 반환
	 */
	
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
	
	/**
	 * @author 오명근
	 * 
	 * 사용자 번호를 이용해 회원의 기본 정보를 조회한다.
	 *
	 * USER_NUMBER를 기준으로 USER_ID, USER_NAME, USER_PHONE, ADDR_NUMBER 정보를 조회한다.
	 *
	 * @param userNumber
	 * @return 조회된 사용자 정보를 담은 UserDTO 객체 반환
	 */

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

	/**
	 * @author 윤철민
	 * 
	 * 사용자의 전화번호와 주소 정보를 수정한다.
	 *
	 * 사용자 번호와 비밀번호가 일치하는 경우에만, 전화번호(USER_PHONE)와 주소(ADDR_NUMBER)를 변경한다.
	 *
	 * @param userNumber, phone, addrNum, pw
	 * @return 회원정보 수정 성공 시 true, 실패 시 false 반환
	 */
	
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

	/**
	 * @author 서서울
	 * @since jdk17
	 * 
	 * 사용자 아이디와 비밀번호를 입력받아 로그인을 한다
	 * 
	 * TBL_USER 테이블에서 USER_ID와 USER_PW가 일치하는 사용자를 조회하며, 조회 결과가 존재할 경우 해당 사용자의 정보를 UserDTO 객체에 담아 반환한다.
	 * 
	 * @param userDTO
	 * @return 로그인 성공 시 사용자 정보가 담긴 userDTO 객체 반환
	 * 		   로그인 실패 시 null 반환
	 */

	public UserDTO login(UserDTO userDTO) {

		String query = "SELECT USER_NUMBER, USER_ID, USER_PW, USER_NAME, USER_PHONE, ADDR_NUMBER FROM TBL_USER WHERE USER_ID = ? AND USER_PW = ?";

		UserDTO u = new UserDTO();

		try {
			connection = DBConnector.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userDTO.getUserId());
			preparedStatement.setString(2, userDTO.getUserPw());
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				u.setUserNumber(resultSet.getInt("USER_NUMBER"));
				u.setUserId(resultSet.getString("USER_ID"));
				u.setUserPw(resultSet.getString("USER_PW"));
				u.setUserName(resultSet.getString("USER_NAME"));
				u.setUserPhone(resultSet.getString("USER_PHONE"));
				u.setAddrNumber(resultSet.getString("ADDR_NUMBER"));
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

	/**
	 * @author 서서울
	 * @since jdk17
	 * 
	 * 사용자의 아이디 비밀번호 이름을 입력받아 모두 일치하면 회원탈퇴을 한다.
	 * 입력받은 사용자 정보와 userDTO 객체에 저장된 값이 일치하면 boolean 값으로 반환한다.
	 * 
	 * @param userDTO
	 * @return 삭제 성공 시 true, 실패 시 false 반환
	 */

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
