package com.koreait.dto;

public class UserDTO {

	   private int userNumber;
	   private String userId;
	   private String userPw;
	   private String userName;
	   private String userPhone;
	   private int addrNumber;
	   public int getUserNumber() {
		   return userNumber;
	   }
	   public void setUserNumber(int userNumber) {
		   this.userNumber = userNumber;
	   }
	   public String getUserId() {
		   return userId;
	   }
	   public void setUserId(String userId) {
		   this.userId = userId;
	   }
	   public String getUserPw() {
		   return userPw;
	   }
	   public void setUserPw(String userPw) {
		   this.userPw = userPw;
	   }
	   public String getUserName() {
		   return userName;
	   }
	   public void setUserName(String userName) {
		   this.userName = userName;
	   }
	   public String getUserPhone() {
		   return userPhone;
	   }
	   public void setUserPhone(String userPhone) {
		   this.userPhone = userPhone;
	   }
	   public int getAddrNumber() {
		   return addrNumber;
	   }
	   public void setAddrNumber(int addrNumber) {
		   this.addrNumber = addrNumber;
	   }
	   
	   @Override
	   	   public String toString() {
		return "UserDTO [userNumber=" + userNumber + ", userId=" + userId + ", userPw=" + userPw + ", userName="
				+ userName + ", userPhone=" + userPhone + ", addrNumber=" + addrNumber + "]";
	   }
	   
	    
	   
}
