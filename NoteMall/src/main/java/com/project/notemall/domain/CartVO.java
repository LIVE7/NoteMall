package com.project.notemall.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class CartVO implements Serializable {

	private int cartNum;//장바구니 번호
	private int idx;//회원 번호
	private int pnum;//상품번호
	private int oqty;//수량
	private java.sql.Date indate;//장바구니 입고일
	
	//상품정보 관련----
	private String pname;//상품명
	private String pimage;//상품이미지
	private int saleprice;//판매가
	
	private int point;//포인트
	private int upCategory_code;//상위 카테고리
	private int downCategory_code;//하위 카테고리
	
	private int totalPrice;//장바구니 특정 상품의 총액
	private int totalPoint;//장바구니 특정 상품의 총포인트
	
	private int cartTotalPrice;//장바구니 모든 상품의 누적 총액
	private int cartTotalPoint;//장바구니 모든 상품의 누적 포인트
}
