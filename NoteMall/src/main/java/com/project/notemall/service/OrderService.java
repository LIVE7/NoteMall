package com.project.notemall.service;

import java.util.List;

import com.project.notemall.domain.OrderVO;

public interface OrderService {
	
	String orderInsert(OrderVO ovo);//주문개요 정보와 상품정보, 수령자 정보를  insert
	
	List<OrderVO> getOrderDesc(String onum);//주문처리된 내역 정보 가져오기
	
	List<OrderVO> getUserOrderList(int idx);
	//특정 회원의 주문목록 가져오기

}