package com.koreait.dto;

public class OrderDTO {
//	CREATE TABLE TBL_ORDER (
//			ORDER_NUMBER NUMBER 
//			,ORDER_DATE DATE NOT NULL
//			,ORDER_STATUS VARCHAR2(100) CHECK(ORDER_STATUS IN ('주문불가','상품준비중','배송중','배송완료'))  NOT NULL
//			,USER_NUMBER NUMBER 
//			,ITEM_NUMBER NUMBER
//			,ADDR_NUMBER VARCHAR(20)
//			,CONSTRAINT ORDER_PK PRIMARY KEY(ORDER_NUMBER)
//			,CONSTRAINT ORDER_USER_FK FOREIGN KEY (USER_NUMBER) REFERENCES TBL_USER (USER_NUMBER)
//			,CONSTRAINT ORDER_ITEM_FK FOREIGN KEY (ITEM_NUMBER) REFERENCES TBL_ITEM (ITEM_NUMBER)
//			,CONSTRAINT ORDER_ADDR_FK FOREIGN KEY (ADDR_NUMBER) REFERENCES TBL_ADDR (ADDR_NUMBER)
//		);
	
	private int orderNumber;
	private String orderDate;
	private String orderStatus;
	private int userNumber;
	private int itemNumber;
	private String addrNumber;
	
	
	public int getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public int getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}
	public int getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}
	public String getAddrNumber() {
		return addrNumber;
	}
	public void setAddrNumber(String addrNumber) {
		this.addrNumber = addrNumber;
	}
	
	@Override
	public String toString() {
		return "OrderDTO [orderNumber=" + orderNumber + ", orderDate=" + orderDate + ", orderStatus=" + orderStatus
				+ ", userNumber=" + userNumber + ", itemNumber=" + itemNumber + ", addrNumber=" + addrNumber + "]";
	}
	
	
}
