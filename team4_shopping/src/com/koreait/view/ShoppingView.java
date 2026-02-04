package com.koreait.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.koreait.dao.UserDAO;
import com.koreait.dto.UserDTO;

public class ShoppingView {

//	회원 : 회원가입(등록), 로그인(조회), 정보수정(수정), 회원탈퇴(삭제)
//	상품 : 상품검색(조회) 카테고리별 / 전체, 
//	주문 : 상품구매(등록), 주문조회(조회) 기간별 / 날짜별, 배송지 변경(수정), 주문취소(삭제)

	private Scanner sc = new Scanner(System.in);
	UserDAO userDAO = new UserDAO();

	// 로그인 전 메뉴
	public int menuLogout() {
		System.out.println("===== 남녀공용 의류 쇼핑몰 =====\n");
		System.out.println("1. 회원가입");
		System.out.println("2. 로그인");
		System.out.println("0. 종료");
		System.out.print("선택 : ");
		int choice = sc.nextInt();
		sc.nextLine();
		return choice;
	}

	// 로그인 후 메뉴
	public int menuLogin() {
		System.out.println("--- 환영합니다 ---\n");
		System.out.println("1. 마이페이지");
		System.out.println("2. 상품조회");
		System.out.println("3. 주문내역");
		System.out.println("4. 로그아웃");
		System.out.print("선택 : ");
		int choice = sc.nextInt();
		sc.nextLine();
		return choice;
	}

	// 마이페이지 메뉴
	public int myPageMenu() {
		System.out.println("--- 마이페이지 ---\n");
		System.out.println("1. 나의 정보");
		System.out.println("2. 비밀번호 변경");
		System.out.println("3. 정보 수정");
		System.out.println("4. 회원탈퇴");
		System.out.println("0. 뒤로가기");
		System.out.print("선택 : ");
		int choice = sc.nextInt();
		sc.nextLine();
		return choice;
	}

	// 상품 메뉴
	public int itemMenu() {
		System.out.println("--- 상품조회 ---\n");
		System.out.println("1. 전체조회");
		System.out.println("2. 카테고리별 조회");
		System.out.println("3. 상품구매");
		System.out.println("0. 뒤로가기");
		System.out.print("선택 : ");
		int choice = sc.nextInt();
		sc.nextLine();
		return choice;
	}

	// 주문 메뉴
	public int orderMenu() {
		System.out.println("--- 주문내역 ---\n");
		System.out.println("1. 기간별 주문조회");
		System.out.println("2. 날짜별 주문조회");
		System.out.println("3. 배송지 변경");
		System.out.println("4. 주문취소");
		System.out.println("0. 뒤로가기");
		System.out.print("선택 : ");
		int choice = sc.nextInt();
		sc.nextLine();
		return choice;
	}

	// ---로그인 전 메뉴

	// 회원가입
	public UserDTO inputUser() {
		UserDTO user = new UserDTO();
		System.out.println("--- 회원가입 ---\n");
		System.out.print("아이디 입력 : ");
		while (true) {
			String id = sc.nextLine();
			// true면 중복
			if (userDAO.idCheck(id)) {
				System.out.println("이미 존재하는 아이디입니다");
			} else {
				System.out.println("사용가능한 아이디입니다");
				user.setUserId(id);
				break;
			}
		}
		System.out.print("비밀번호 입력 : ");
		user.setUserPw(sc.nextLine());
		System.out.print("이름 입력 : ");
		user.setUserName(sc.nextLine());
		System.out.print("전화번호 입력 : ");
		user.setUserPhone(sc.nextLine());
		System.out.print("주소 입력 : ");
		// System.out.print("우편번호 입력 : ");
		user.setAddrNumber(sc.nextLine());
		return user;
	}

	// 로그인
	public UserDTO login() {
		UserDTO user = new UserDTO();
		System.out.println("--- 로그인 ---\n");
		System.out.print("아이디 : ");
		user.setUserId(sc.nextLine());
		System.out.print("비밀번호 : ");
		user.setUserPw(sc.nextLine());
		return user;
	}

	// ---마이페이지
	// 내 정보 확인 
	public void findUserInfo(UserDTO user) {
	    System.out.println("--- 나의 정보 ---\n");
	    System.out.println("아이디 : " + user.getUserId());
	    System.out.println("이름 : " + user.getUserName());
	    System.out.println("전화번호 : " + user.getUserPhone());
	    System.out.println("우편번호 : " + user.getAddrNumber());
	}
	
	// 비밀번호변경
	public List<String> changePw() {
		List<String> changePw = new ArrayList<>();
		System.out.println("--- 비밀번호 변경 ---\n");

		System.out.print("현재 비밀번호 : ");
		String oldPw = sc.nextLine();

		System.out.print("새 비밀번호 : ");
		String newPw = sc.nextLine();

		changePw.add(oldPw);
		changePw.add(newPw);
		return changePw;
	}

	// 정보수정
	public UserDTO updateUser() {
		UserDTO user = new UserDTO();
		System.out.println("--- 정보 수정 ---");
		System.out.println("(전화번호, 우편번호 변경)\n");
		System.out.print("비밀번호 입력 : ");
		user.setUserPw(sc.nextLine());
		System.out.print("변경할 전화번호 : ");
		user.setUserPhone(sc.nextLine());
		System.out.print("변경할 우편번호 : ");
		user.setAddrNumber(sc.nextLine());
		return user;
	}

	// 회원탈퇴
	public UserDTO deleteUser() {
		UserDTO user = new UserDTO();
		System.out.println("--- 회원 탈퇴 ---\n");
		System.out.print("아이디 : ");
		user.setUserId(sc.nextLine());
		System.out.print("비밀번호 : ");
		user.setUserPw(sc.nextLine());
		System.out.print("이름 : ");
		user.setUserName(sc.nextLine());
		return user;
	}

	// ---상품
	// 상품 전체조회
//	public void findAllItem(List<String> list) {
//		System.out.println("--- 전체조회 ---\n");
//		if(list.isEmpty() ) {
//			System.out.println("상품 데이터가 없습니다");
//			return;
//		}
//		for (String item : list) {
//			System.out.println(item);
//		}
//	}

	// 상품 카테고리별 조회
//	public String findItemCategory() { 
//		System.out.println("--- 카테고리별 조회 ---\n");
//        System.out.println("검색할 카테고리 입력\n[모자, 상의, 하의, 신발]");
//        String category = sc.nextLine();
//        return category;
//	}

	// 상품 구매
	// 회원번호 어떻게 받아올지 고민
//	public OrderDTO buyItem() {
//		OrderDTO order = new OrderDTO();
//		System.out.println("--- 상품 구매 ---\n");
//		System.out.print("구매할 상품 이름 : ");
//	    order.setItemName(sc.nextLine());
//        System.out.print("배송지 우편번호 : ");
//        order.setAddrNumber(sc.nextLine());
//		order.setUserNumber(/*회원번호*/); 
//        return order;
//	}

	// ---주문
	// 기간으로 주문조회
//	public List<String> orderFindDateRange() {
//		List<String> dateRange = new ArrayList<>();
//		System.out.println("--- 주문 조회 ---\n");
//		System.out.print("시작 날짜 입력 (YYYY-MM-DD) : ");
//		String startDate = sc.nextLine();
//		System.out.print("끝 날짜 입력 (YYYY-MM-DD) : ");
//		String endDate = sc.nextLine();
//		dateRange.add(startDate);
//		dateRange.add(endDate);
//		return dateRange;
//	}

	// 날짜로 주문조회
//	public String orderFindDate() {
//		System.out.println("--- 주문 조회 ---\n");
//		System.out.print("날짜 입력 (YYYY-MM-DD) : ");
//		String date = sc.nextLine();
//		return date;
//	}

	// 주문 배송지 변경
	// 주문번호로?
//	public OrderDTO updateOrderAddr() {
//        OrderDTO order = new OrderDTO();
//        System.out.println("--- 배송지 변경 ---\n");
//        System.out.print("변경할 주문 번호 : ");
//        order.setOrderNumber(sc.nextInt());
//        sc.nextLine();
//        System.out.print("새로운 배송지 우편번호 : ");
//        order.setAddrNumber(sc.nextLine());
//        return order;
//    }

	// 주문 취소
//	public int cancelOrder() {
//		System.out.println("--- 주문 취소 ---\n");
//		System.out.print("취소할 주문 번호 : ");
//		int orderNumber = sc.nextInt();
//		sc.nextLine();
//		return orderNumber;
//	}

	// 메세지 출력
	public void msg(String msg) {
		System.out.println(msg);
	}

}
