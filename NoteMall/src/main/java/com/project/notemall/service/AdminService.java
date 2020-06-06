package com.project.notemall.service;

import java.util.List;

import com.project.notemall.domain.CategoryVO;
import com.project.notemall.domain.OrderVO;
import com.project.notemall.domain.ProductVO;
import com.project.notemall.domain.SumVO;

public interface AdminService {

	// ���� ī�װ� ���
	int addUpCategory(CategoryVO cvo);

	// ���� ī�װ� ���
	int addDownCategory(CategoryVO cvo);

	// ���� ī�װ� ����
	int deleteUpCategory(int upcg_code);

	// ���� ī�װ� ����
	int deleteDownCategory(int downcg_code);

	// ���� ī�װ� ��� ��������
	public List<CategoryVO> getUpCategory();

	// ���� ī�װ� ��� ��������
	public List<CategoryVO> getDownCategory(int upcg_code);

	//�� ��ǰ ���� ��������
	int getTotalProduct();
	// ��ǰ���� ����ϴ� �޼ҵ�
	int addProduct(ProductVO prod);

	// ��ǰ��� ��������
	List<ProductVO> listProduct(int start, int end);

	// [�ֹ�����] ���� �ֹ� ��� ��������
	List<OrderVO> getOrderListByMonth(String month);

	// ��ۻ���, ���һ��¸� �����ϴ� �޼ҵ�
	void manageOrder(String onum, String colName, String colVal);
	

	
	//[���� ��� ���ϱ�] ������ ������� ��������
	List<SumVO> getSumYear();
	
	//[���� ��� ���ϱ�] �ش� �⵵ ���� ������� ��������
	List<SumVO> getSumMonth(String year);
	
	//�ֹ����� ��������Ʈ ��������
	int getUserSumPoint(int midx_fk);
}
