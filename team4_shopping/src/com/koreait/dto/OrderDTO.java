package com.koreait.dto;

/**
 * @author 서서울
 * @since jdk17
 * 
 * 주문 정보를 저장하고 전달하기 위한 DTO(Data Transfer Object) 클래스.
 *
 * 주문 번호, 주문 날짜, 주문 상태, 사용자 번호, 상품 번호, 배송지 번호 등의 주문 관련 데이터를 하나의 객체로 관리한다.
 *
 */

public class OrderDTO {

	/** 주문 번호 */
	private int orderNumber;

	/** 주문 날짜 */
	private String orderDate;

	/** 주문 상태 (예: 주문완료, 배송중, 배송완료 등) */
	private String orderStatus;

	/** 주문한 사용자 번호 */
	private int userNumber;

	/** 주문한 상품 번호 */
	private int itemNumber;

	/** 배송지 번호 */
	private String addrNumber;

	/**
	 * 주문 번호를 반환한다.
	 * @return 주문 번호
	 */
	public int getOrderNumber() {
		return orderNumber;
	}

	/**
	 * 주문 번호를 설정한다.
	 * @param orderNumber 주문 번호
	 */
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	/**
	 * 주문 날짜를 반환한다.
	 * @return 주문 날짜
	 */
	public String getOrderDate() {
		return orderDate;
	}

	/**
	 * 주문 날짜를 설정한다.
	 * @param orderDate 주문 날짜
	 */
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * 주문 상태를 반환한다.
	 * @return 주문 상태
	 */
	public String getOrderStatus() {
		return orderStatus;
	}

	/**
	 * 주문 상태를 설정한다.
	 * @param orderStatus 주문 상태
	 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	/**
	 * 사용자 번호를 반환한다.
	 * @return 사용자 번호
	 */
	public int getUserNumber() {
		return userNumber;
	}

	/**
	 * 사용자 번호를 설정한다.
	 * @param userNumber 사용자 번호
	 */
	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}

	/**
	 * 상품 번호를 반환한다.
	 * @return 상품 번호
	 */
	public int getItemNumber() {
		return itemNumber;
	}

	/**
	 * 상품 번호를 설정한다.
	 * @param itemNumber 상품 번호
	 */
	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}

	/**
	 * 배송지 번호를 반환한다.
	 * @return 배송지 번호
	 */
	public String getAddrNumber() {
		return addrNumber;
	}

	/**
	 * 배송지 번호를 설정한다.
	 * @param addrNumber 배송지 번호
	 */
	public void setAddrNumber(String addrNumber) {
		this.addrNumber = addrNumber;
	}

	/**
	 * 주문 객체의 모든 정보를 문자열 형태로 반환한다.
	 *
	 * 디버깅 및 로그 출력 용도로 사용된다.
	 *
	 * @return 주문 정보 문자열
	 */
	@Override
	public String toString() {
		return "OrderDTO [orderNumber=" + orderNumber + ", orderDate=" + orderDate + ", orderStatus=" + orderStatus
				+ ", userNumber=" + userNumber + ", itemNumber=" + itemNumber + ", addrNumber=" + addrNumber + "]";
	}
}
