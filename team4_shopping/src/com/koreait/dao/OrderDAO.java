package com.koreait.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.koreait.dto.ItemDTO;
import com.koreait.dto.OrderDTO;
import com.koreait.dto.UserDTO;


public class OrderDAO {

	public Connection connection;
	public PreparedStatement preparedStatement;
	public ResultSet resultSet;

	
	//상품구매 
	// tbl_order에 tbl_item join 해서 item_stock을 조회
	// item_stock이 0보다 크면 주문이 가능하다 0이면 주문 불가능
	// join query문 입력
	// 
	public boolean itemBuy (UserDTO userDto, ItemDTO itemDto) {
		String query = " INSERT INTO TBL_ORDER "
				+ "VALUES(SEQ_ORDER.NEXTVAL , sysdate, ?, ?, ?, ?) ";
		
		String candelivery;
		if(itemDto.getItemStock() > 0) {
			candelivery = "구매가능";
		} else {
			candelivery = "구매불가";
		}
		
		int result = 0;
		try {
			connection = DBConnector.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, candelivery);
			preparedStatement.setInt(2, userDto.getUserNumber());
			preparedStatement.setInt(3, itemDto.getItemNumber());
			preparedStatement.setString(4, userDto.getAddrNumber());
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("itemBuy() SQL 오류");
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
				System.out.println("itemBuy() 접속 종료 오류");
				e.printStackTrace();
			}
		}
		return result > 0;
	
	}
	
	
	
//	public ItemDTO itemBuy (OrderDTO item, ItemDTO itemDTO) {
//		String query = "SELECT o.order_number, o.order_status, i.item_stock, i.item_number "
//				+ "FROM tbl_order "
//				+ "JOIN tbl_item i"
//				+ " ON o.item_number = i.item_number "
//				+ "WHERE i.ITEM_STOCK >= ?";
//		
//		ItemDTO i = new ItemDTO();
//			
//		try {
//			connection = DBConnector.getConnection();
//			preparedStatement = connection.prepareStatement(query);
//			preparedStatement.setInt(1, itemDTO.getItemStock());
//			resultSet = preparedStatement.executeQuery();
//			
//		} catch (SQLException e) {
//			System.out.println("itemBuy() SQL 오류!");
//			e.printStackTrace();
//		} finally {
//			try {
//				if(resultSet != null) {
//					resultSet.close();
//				}
//				if(preparedStatement != null) {
//					preparedStatement.close();
//				}
//				if(connection != null) {
//					connection.close();
//				}
//			} catch (SQLException e) {
//				System.out.println("itemBuy() 연결 종료 오류!");
//				e.printStackTrace();
//			}
//		}
//		return i;
//	}
//	주문조회 
	public void ordersearch (OrderDTO order) {
		// 아이템 name , 주문날짜 , 주문 상태, 아이템cat , 아이템 price,
		connection = DBConnector.getConnection();
		preparedStatement = connection.prepareStatement(query);
		
		
	}
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
