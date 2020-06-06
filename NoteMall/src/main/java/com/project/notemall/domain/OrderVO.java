package com.project.notemall.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data
public class OrderVO implements Serializable {
	
	//orderDesc관련 프로퍼티---
	private String onum; //주문번호
	private int ototalPrice;//주문총액
	private int ototalPoint;//총포인트
	private String odeliver;//배송상태
	private String odeliverPrice;//배송비(디폴트 3000원)
	private String opaystate;//지불상태
	private Date orderDate;
	private String orderMemo;
	private int opointuse;//사용 포인트
	
	private int midx_fk;//주문자 번호
	private String uname;//주문자 이름
	private String userid;//주문자 아이디
	private int cnt;//주문한 상품갯수
	private String pimage;//주문한 상품의 대표 이미지
	
	private int payMethod;//결제수단(무통장입금:100, 카드결제:200)
	private int bank;//무통장입금일 경우 (국민은행:1, 우리:2, 신한:3)
	
	
	//주문 상품 관련 프로퍼티----
	private List<ProductVO> orderList;
	//하나의 주문 건수에 대해 주문 상품은 여러 개 가질 수 있다.
	
	//수령자 관련 프로퍼티
	private ReceiverVO receiver;
	//하나의 주문 건수에 대해 수령자는 1명
	
}



