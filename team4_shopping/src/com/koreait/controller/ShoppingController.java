package com.koreait.controller;

import com.koreait.dao.ItemDAO;
import com.koreait.dao.UserDAO;
import com.koreait.dto.UserDTO;
import com.koreait.view.ShoppingView;

public class ShoppingController {
	
	ShoppingView view = new ShoppingView();
	UserDAO userDAO = new UserDAO();
	UserDTO user = new UserDTO();
	ItemDAO itemDAO = new ItemDAO();
	
	public void run() {
		
		int loginBeforeChoice = 0;
		
		
		while(loginBeforeChoice != 2) {
			loginBeforeChoice = beforeLogin();
			
			if(loginBeforeChoice == 0) {
				return;
			}
		}
		
		if(loginBeforeChoice == 2) {
			afterLogin();
		}
		
		
	}
	
	//로그인 전 선택 메서드
	private int beforeLogin() {
		int choice = view.menuLogout();
		
		switch(choice) {
		case 1 :
			userInsert();
			break;
		case 2:
			user = userLogin();
			break;
		case 0 : 
			view.msg("프로그램을 종료합니다.");
			break;
        default:
            view.msg("잘못입력했습니다");
            break;	
		}
		return choice;	
	}
	
	
	//회원가입
	private void userInsert() {
		UserDTO dto = view.inputUser();
		boolean result = userDAO.join(dto);
		if(result) {
			view.msg("회원 가입 성공");
		}else {
			view.msg("회원가입 실패");
		}
		
	}
	
	//로그인
	private UserDTO userLogin() {
		UserDTO dto = view.login();
		UserDTO result = userDAO.login(dto);
		if(result != null) {
			view.msg("로그인 성공");
			return result;
		}else {
			view.msg("로그인 실패");
			return null;
		}
	}
	
	//로그인 후 메뉴
	private int afterLogin() {
		int choice = view.menuLogin();
		
		switch(choice) {
		case 1 :

			break;
		case 2:

			break;
		case 3 : 

			break;
		case 4:
			
			break;
        default:
            view.msg("잘못입력했습니다");
            break;	
		}
		return choice;
	}
	
	//마이페이지 메뉴
	private void myPage() {
		int choice = view.myPageMenu();
		
		switch(choice) {
		case 1:
			
		case 2:
			
		case 0:
		}
	}
	
//	// 정보수정
//	private void infoChange() {
//		UserDTO dto = view.updateUser();
//		boolean result = userDAO.infoChange(dto);	//userDAO의 정보 수정 메서드
//		
//		if(result) {
//			view.msg("정보가 수정되었습니다.");
//		}
//	}
//	
//	// 회원탈퇴
//	private void userDelete() {
//		UserDTO dto = view.deleteUser();
//		
//		boolean result = userDAO.deleteUser(dto); 
//		
//		if(result) {
//			view.msg(dto.getName + "님께서 회원 탈퇴하셨습니다.");
//		}
//	}
//	
//	//상품 전체조회
//	private void itemAllSelect() {
//		view.findAllItem(itemDAO.itemsSelect());
//	}
//	
//	
//	//상품 카테고리별 조회
//	private void itemCategorySelect() {
//		view.msg(itemDAO.itemCatSelect(view.findItemCategory()));
//	}
//	
//	//상품구매
//	private void itemBuy() {
//		
//	}
//	
//	//기간별 주문 조회
//	private void orderSelect{
//		
//	}
//	
//	//날짜별 주문 조회
//	
//	//배송지 변경
//	
//	//주문 취소
//	
//	//뒤로가기
//	
}//class


