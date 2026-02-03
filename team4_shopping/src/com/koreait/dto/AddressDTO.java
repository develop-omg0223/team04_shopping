package com.koreait.dto;

public class AddressDTO {
//	CREATE TABLE TBL_ADDR(
//			ADDR_NUMBER VARCHAR2(20)
//			,ADDR_DETAIL VARCHAR2(100) NOT NULL
//			,CONSTRAINT ADDR_PK PRIMARY KEY (ADDR_NUMBER)
//		);
	
	private String addrNumber;
	private String addrDetail;
	
	public String getAddrNumber() {
		return addrNumber;
	}
	public void setAddrNumber(String addrNumber) {
		this.addrNumber = addrNumber;
	}
	public String getAddrDetail() {
		return addrDetail;
	}
	public void setAddrDetail(String addrDetail) {
		this.addrDetail = addrDetail;
	}
	
	@Override
	public String toString() {
		return "AddressDTO [addrNumber=" + addrNumber + ", addrDetail=" + addrDetail + "]";
	}
}
