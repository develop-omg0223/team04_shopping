package com.koreait.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
	
	public Connection connection;
	public PreparedStatement preparedStatement;
	public ResultSet resultSet;
	
	
	//모든 품목 조회
	public List<String> itemsSelect() {
		String query = "SELECT ITEM_NAME, ITEM_CAT, ITEM_PRICE, ITEM_STOCK"
				+ " FROM TBL_ITEM"
				+ " ORDER BY ITEM_PRICE AES";
		
		List<String> itemsList = new ArrayList<>();
		
		try {
			connection = DBConnector.getConnection();
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				String row = 
						resultSet.getString("ITEM_NAME") + " , " +
						resultSet.getString("ITEM_CAT") + " , " +
						resultSet.getInt("ITEM_PRICE") + " , " +
						resultSet.getInt("ITEM_STOCK");
						
				itemsList.add(row);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("itemsSelect() SQL 오류");
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
				if(resultSet != null) {
					resultSet.close();
				}
				
			} catch (SQLException e) {
           // TODO Auto-generated catch block
        	System.out.println("itemsSelect() 연결 종료 오류");
            e.printStackTrace();
        	}
		}
        return itemsList;
	}
	
	//카테고리 품목 조회
	public List<String> itemCatSelect(String category) {
		String query = "SELECT ITEM_NAME, ITEM_CAT, ITEM_PRICE, ITEM_STOCK"
				+ " FROM TBL_ITEM"
				+ " WHERE ITEM_CAT = ?"
				+ " ORDER BY ITEM_PRICE AES";
		
		List<String> itemCatList = new ArrayList<>();
		
		try {
			connection = DBConnector.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1,category);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				String row = 
						resultSet.getString("ITEM_NAME") + " , " +
						resultSet.getString("ITEM_CAT") + " , " +
						resultSet.getInt("ITEM_PRICE") + " , " +
						resultSet.getInt("ITEM_STOCK");
						
				itemCatList.add(row);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("itemCatSelect() SQL 오류");
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
				if(resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("itemCatSelect() 연결 종료 오류");
				e.printStackTrace();
			}
		}
		
		return itemCatList;
		
	}
	
}

