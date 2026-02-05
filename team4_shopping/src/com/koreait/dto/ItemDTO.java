package com.koreait.dto;

/**
 * @author 윤철민
 * 
 * @since jdk17
 * 
 * Item 정보를 담기 위한 DTO 클래스
 * 
 * DB의 Item 테이블 구조를 그대로 반영하여
 * 상품 번호, 상품명, 카테고리, 가격, 재고 정보를 저장
 * 
 * 데이터를 전달하는 용도로 사용
 */


/* 	ITEM_NUMBER NUMBER
	,ITEM_NAME VARCHAR2(100) NOT NULL
	,ITEM_CAT VARCHAR2(100) CHECK(ITEM_CAT IN('모자', '상의', '하의', '신발')) NOT NULL
	,ITEM_PRICE NUMBER DEFAULT 0 NOT NULL 
	,ITEM_STOCK NUMBER DEFAULT 0 NOT NULL 	*/

public class ItemDTO {
		private int itemNumber;
		private String itemName;
		private String itemCat;
		private int itemPrice;
		private int itemStock;
	
	
	public ItemDTO() {
		
	}

	public int getItemNumber() {
		return itemNumber;
	}


	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}


	public String getItemName() {
		return itemName;
	}


	public void setItemName(String itemName) {
		this.itemName = itemName;
	}


	public String getItemCat() {
		return itemCat;
	}


	public void setItemCat(String itemCat) {
		this.itemCat = itemCat;
	}


	public int getItemPrice() {
		return itemPrice;
	}


	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}


	public int getItemStock() {
		return itemStock;
	}


	public void setItemStock(int itemStock) {
		this.itemStock = itemStock;
	}

	@Override
	public String toString() {
		   return "상품번호 : " + itemNumber + ", 상품이름 : " + itemName + ", 카테고리" + itemCat + ", 가격"
		            + itemPrice + ", 재고" + itemStock;
	}

	

}
