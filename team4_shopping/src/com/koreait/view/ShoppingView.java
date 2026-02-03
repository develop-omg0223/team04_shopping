package com.koreait.view;

import java.util.Scanner;

public class ShoppingView {
	
//	회원 : 회원가입(등록), 로그인(조회), 정보수정(수정), 회원탈퇴(삭제)
//	상품 : 상품검색(조회) 카테고리별 / 전체, 
//	주문 : 상품구매(등록), 주문조회(조회) 기간별 / 날짜별, 배송지 변경(수정), 주문취소(삭제)
	
	Scanner sc = new Scanner(System.in);
	
	//로그인 전 메뉴
	public int menuLogout () {
		System.out.println("===== 남녀공용 의류 쇼핑몰 =====\n");
        System.out.println("1. 로그인");
        System.out.println("2. 회원가입");
        System.out.println("3. 상품검색");
        System.out.println("0. 종료");
        System.out.print("선택 : ");
        int choice = sc.nextInt();
        sc.nextLine();
        return choice;
	}
	
	//로그인 후 메뉴
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
	
	//마이페이지 메뉴 
	public int myPageMenu() {
        System.out.println("--- 마이페이지 ---\n");
        System.out.println("1. 정보수정");
        System.out.println("2. 회원탈퇴");
        System.out.println("0. 뒤로가기");
        System.out.print("선택 >> ");
        int choice = sc.nextInt();
        sc.nextLine();
        return choice;
    }
	
	//상품 메뉴
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
	
	//주문 메뉴
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
	
	//---로그인 전 메뉴 
	
	//회원가입
	public UserDTO inputUser() {
		UserDTO user = new UserDTO();
        System.out.println("\n[회원 가입]");
        System.out.print("아이디 : ");
        user.setUserId(sc.nextLine());
        System.out.print("비밀번호 : ");
        user.setUserPw(sc.nextLine());
        System.out.print("이름 : ");
        user.setUserName(sc.nextLine());
        System.out.print("전화번호 : ");
        user.setUserPhone(sc.nextLine());
        
        // 주소 정보는 별도 DTO나 필드로 처리하여 TBL_ADDR에 먼저 insert 해야 함
        System.out.print("주소(상세주소) : ");
        user.setUserAddr(sc.nextLine()); 
        return user;
    }
	
	//---회원관련
	
	
	
	//메세지 출력
	public void msg(String msg) {
		System.out.println(msg);
	}

	
}
