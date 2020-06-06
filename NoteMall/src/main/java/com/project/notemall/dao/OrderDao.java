package com.project.notemall.dao;

import java.util.List;

import com.project.notemall.domain.OrderVO;
import com.project.notemall.domain.ProductVO;
import com.project.notemall.domain.ReceiverVO;

public interface OrderDao {

	/** �ֹ����� ������ insert�ϴ� �޼ҵ� */
	int orderDescInsert(OrderVO ovo);

	/** �ֹ���ǰ ������ insert�ϴ� �޼ҵ� */
	int orderProductInsert(ProductVO prod);

	int orderProductInsert(List<ProductVO> prodList);

	/** ������ ������ insert�ϴ� �޼ҵ� */
	int receiverInsert(ReceiverVO rcv);

	// �ֹ�ó���� ���� ���� ��������
	List<OrderVO> getOrderDesc(String onum);
	
	// Ư�� ȸ���� �ֹ���� ��������
	List<OrderVO> getUserOrderList(int idx);

}
