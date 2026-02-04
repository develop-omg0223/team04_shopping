package com.koreait.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.koreait.dto.OrderDTO;


public class OrderDAO {

	public Connection connection;
	public PreparedStatement preparedStatement;
	public ResultSet resultSet;

//   주문 DAO - 수정, 주문취소

	// 주문 수정
	public boolean updateOrderAddr(OrderDTO dto) {
		String query = "UPDATE TBL_ORDER SET ADDR_NUMBER = ? WHERE ORDER_NUMBER = ?";
		int result = 0;

		try {
			connection = DBConnector.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, dto.getAddrNumber());
			preparedStatement.setInt(2, dto.getOrderNumber());
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("updateOrderAddr() SQL 오류");
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
				System.out.println("updateOrderAddr() 접속 종료 오류");
				e.printStackTrace();
			}
		}
		return result > 0;
	}

	// 주문 취소
	public boolean cancelOrder(int orderNumber) {
		String query = "DELETE FROM TBL_ORDER WHERE ORDER_NUMBER = ?";
		int result = 0;

		try {
			connection = DBConnector.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, orderNumber);
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("cancelOrder() SQL 오류");
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
				System.out.println("cancelOrder() 접속 종료 오류");
				e.printStackTrace();
			}
		}
		return result > 0;
	}

}
