package com.koreait.controller;

import java.util.List;

import com.koreait.dao.ItemDAO;
import com.koreait.dao.OrderDAO;
import com.koreait.dao.UserDAO;
import com.koreait.dto.ItemDTO;
import com.koreait.dto.OrderDTO;
import com.koreait.dto.UserDTO;
import com.koreait.view.ShoppingView;

public class ShoppingController {
	
	ShoppingView view = new ShoppingView();
	UserDAO userDAO = new UserDAO();
	ItemDAO itemDAO = new ItemDAO();
	OrderDAO orderDAO = new OrderDAO();
	UserDTO user = null;
	
	public void run() {
		
		int loginBeforeChoice = 0;
		
		while(user == null) {
			loginBeforeChoice = beforeLogin();
			
			if(loginBeforeChoice == 0) {
				return;	
			}
				
			while(user != null) {
				afterLogin();	
			}
			user = null;
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
	private void afterLogin() {
		int choice = view.menuLogin();
		
		switch(choice) {
		case 1 :
			myPage();
			break;
		case 2:
			item();
			break;
		case 3 : 
			order();
			break;
		case 4:
			userLogout();
			break;
        default:
            view.msg("잘못입력했습니다");
            break;	
		}
	}
	
	//마이페이지 메뉴
	private void myPage() {
		int choice = view.myPageMenu();
		
		switch(choice) {
		case 1:
			view.findUserInfo(user);
			break;
		case 2:
			pwChange();
			break;	
		case 3:
			userUpdate();
			break;
		case 4:
			userDelete();
			break;
		case 0:
			view.msg("뒤로가기");
			break;
        default:
            view.msg("잘못입력했습니다");
            break;	
		}
	}
	
	// 비밀번호수정
	private void pwChange() {
		List<String> pwList = view.changePw();
		boolean result = userDAO.changePw(user.getUserNumber(),pwList.get(0),pwList.get(1));	//userDAO의 정보 수정 메서드
		
		if(result) {
			view.msg("비밀번호가 수정되었습니다.");
		}else {
			view.msg("기존 비밀번호가 일치하지 않습니다.");
		}
	}
	
	//정보 수정
	private void userUpdate() {
		UserDTO infoChange = view.updateUser();
		boolean result = userDAO.changeInfo(user.getUserNumber(),infoChange.getUserPhone(), infoChange.getAddrNumber(),infoChange.getUserPw());
		
		if(result) {
			view.msg("전화번호, 주소가 변경되었습니다.");
		}else {
			view.msg("비밀번호가 일치하지 않습니다.");
		}
		
	}
	
	// 회원탈퇴
	private void userDelete() {
		UserDTO dto = view.deleteUser();
		
		boolean result = userDAO.deleteUser(dto); 
		
		if(result) {
			view.msg(dto.getUserName() + "님께서 회원 탈퇴하셨습니다.");
			user = null;
		}else {
			view.msg("아이디, 비밀번호, 이름을 일치하지 않습니다.");
		}
		
	}

	//상품조회
	private void item() {
		int choice = view.itemMenu();
		
		switch(choice) {
		case 1:
			itemAllSelect();
			break;
		case 2:
			itemCategorySelect();
			break;
		case 3:
			itemBuy();
			break;
		case 0:
			view.msg("뒤로가기");
			break;
        default:
            view.msg("잘못입력했습니다");
            break;	
			
		
		}
	}
	
	//상품 전체조회
	private void itemAllSelect() {
		view.findAllItem(itemDAO.itemsSelect());
	}
	
	
	//상품 카테고리별 조회
	private void itemCategorySelect() {

		itemDAO.itemCatSelect(view.findItemCategory()).stream().forEach(System.out::println);
		
//		for(String item : itemDAO.itemCatSelect(view.findItemCategory())){
//			view.msg(item);
//		}
	
	}
	
	//상품구매
	private void itemBuy() {
		String itemName = view.buyItem();
		ItemDTO item = itemDAO.nameNumberMatch(itemName);
		boolean result = orderDAO.itemBuy(user, item);
		
		if(result) {
			view.msg("주문 완료되었습니다.");
		}else {
			view.msg("해당 상품은 품절되었습니다.");
		}
		
	}

	//주문 메뉴
	private void order() {
		int choice = view.orderMenu();
		
		switch(choice) {
		case 1:
			orderPeriodSelect();
			break;
			
		case 2:
			orderDateSelect();
			break;
			
		case 3:
			orderAddrChange();
			break;
			
		case 4:
			orderCancel();
			break;
			
		case 0:
			view.msg("뒤로가기");
			break;
			
        default:
            view.msg("잘못입력했습니다");
            break;	
			
		}
	}
	
	
	//기간별 주문 조회
	private void orderPeriodSelect(){
		List<String> dateRange =  view.orderFindDateRange();
		orderDAO.orderSearch(dateRange.getFirst(),dateRange.getLast()).stream().forEach(System.out::println);
		
	}
	
	//날짜별 주문 조회
	private void orderDateSelect() {
		String date = view.orderFindDate();
		orderDAO.daySearch(date).stream().forEach(System.out::println);
		
	}
	
	//배송지 변경
	private void orderAddrChange() {
		OrderDTO changeOrder = view.updateOrderAddr();
		boolean result = orderDAO.updateOrderAddr(changeOrder,user);
		
		if(result) {
			view.msg("배송지가 변경되었습니다.");
		}else {
			view.msg("주문번호와 우편번호를 확인해주시기 바랍니다.");
		}
	}
	
	//주문 취소
	private void orderCancel() {
		int orderNumber = view.cancelOrder();
		boolean result = orderDAO.cancelOrder(orderNumber,user);
		
		if(result) {
			view.msg("주문 취소되었습니다.");
		}else {
			view.msg("주문번호를 확인해 주시기 바랍니다.");
		}
	}
	
	
	//로그아웃
	private void userLogout() {
		view.msg("로그아웃되었습니다.");
		user = null;
	}

}//class


