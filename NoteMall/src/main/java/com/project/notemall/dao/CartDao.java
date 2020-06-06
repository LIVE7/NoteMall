package com.project.notemall.dao;

import java.util.List;
import java.util.Map;

import com.project.notemall.domain.CartVO;

public interface CartDao {
	
	int addCart(CartVO cartvo);//장바구니 추가
	int updateCartOqty(CartVO cartvo);//장바구니 수량 수정	
	int editCart(CartVO cartVo);// 장바구니 수정하기
	
	List<CartVO> selectCartView(int idx);//회원의 장바구니 목록 가져오기
	CartVO getCartTotal(int idx);//회원의 장바구니 총액과 총포인트 가져오기
	
	int deleteCart(int cartNum);//장바구니 삭제하기
	int deleteCartByIdx(Map<String, Integer> map);//회원의 장바구니 모두 삭제하기
	
	int getCartCountByIdx(CartVO cartvo);//회원의 장바구니 담긴 상품 수량 가져오기
}







