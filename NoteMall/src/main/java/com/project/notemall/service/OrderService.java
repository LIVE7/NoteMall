package com.project.notemall.service;

import java.util.List;

import com.project.notemall.domain.OrderVO;

public interface OrderService {
	
	String orderInsert(OrderVO ovo);//�ֹ����� ������ ��ǰ����, ������ ������  insert
	
	List<OrderVO> getOrderDesc(String onum);//�ֹ�ó���� ���� ���� ��������
	
	List<OrderVO> getUserOrderList(int idx);
	//Ư�� ȸ���� �ֹ���� ��������

}