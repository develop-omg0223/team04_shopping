package com.koreait.dto;


/* 	ITEM_NUMBER NUMBER
,ITEM_NAME VARCHAR2(100) NOT NULL
,ITEM_CAT VARCHAR2(100) CHECK(ITEM_CAT IN('모자', '상의', '하의', '신발')) NOT NULL
,ITEM_PRICE NUMBER DEFAULT 0 NOT NULL 
,ITEM_STOCK NUMBER DEFAULT 0 NOT NULL */

public class ItemDTO {
//	 상품 DTO : 철민	
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



}
