package com.project.notemall.dao;

import java.util.List;
import java.util.Map;

import com.project.notemall.domain.CartVO;

public interface CartDao {
	
	int addCart(CartVO cartvo);//��ٱ��� �߰�
	int updateCartOqty(CartVO cartvo);//��ٱ��� ���� ����	
	int editCart(CartVO cartVo);// ��ٱ��� �����ϱ�
	
	List<CartVO> selectCartView(int idx);//ȸ���� ��ٱ��� ��� ��������
	CartVO getCartTotal(int idx);//ȸ���� ��ٱ��� �Ѿװ� ������Ʈ ��������
	
	int deleteCart(int cartNum);//��ٱ��� �����ϱ�
	int deleteCartByIdx(Map<String, Integer> map);//ȸ���� ��ٱ��� ��� �����ϱ�
	
	int getCartCountByIdx(CartVO cartvo);//ȸ���� ��ٱ��� ��� ��ǰ ���� ��������
}







