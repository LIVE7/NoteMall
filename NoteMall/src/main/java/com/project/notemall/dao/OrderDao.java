package com.project.notemall.dao;

import java.util.List;

import com.project.notemall.domain.OrderVO;
import com.project.notemall.domain.ProductVO;
import com.project.notemall.domain.ReceiverVO;

public interface OrderDao {

	/** 주문개요 정보를 insert하는 메소드 */
	int orderDescInsert(OrderVO ovo);

	/** 주문상품 정보를 insert하는 메소드 */
	int orderProductInsert(ProductVO prod);

	int orderProductInsert(List<ProductVO> prodList);

	/** 수령자 정보를 insert하는 메소드 */
	int receiverInsert(ReceiverVO rcv);

	// 주문처리된 내역 정보 가져오기
	List<OrderVO> getOrderDesc(String onum);
	
	// 특정 회원의 주문목록 가져오기
	List<OrderVO> getUserOrderList(int idx);

}
