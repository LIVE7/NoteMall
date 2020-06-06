package com.project.notemall.dao;

import java.util.List;

import com.project.notemall.domain.CategoryVO;
import com.project.notemall.domain.PagingVO;
import com.project.notemall.domain.ProductVO;

public interface ProductDao {
	/** ��ǰ���� �޼ҵ�================*/
	/** ����ī�װ� ��������*/
	public List<CategoryVO> getUpCategory();
	
	/**���� ī�װ� ��������*/
	public List<CategoryVO> getDownCategory(int upCategory_code);
	
	/*Pspec ���� ��ǰ ���� ��������*/
	public List<ProductVO> selectByPspec(String pspec);
	
	/*ī�װ��� ��ǰ���� ��������*/
	public List<ProductVO> selectByCategory(String code);
	
	/**��ǰ��ȣ�� Ư�� ��ǰ ���� ��������*/
	public ProductVO selectByPnum(Integer pnum);
	
	/*��� �귣�� ��ǰ����*/
	public List<ProductVO> selectByAllPcompany(String pcompany);
	
	/*�ش� �귣�� ��ǰ����*/
	public List<ProductVO> selectByPcompany(PagingVO paging);
	
	int getTotalCount();// �� �Խñ� �� ��������
	int getTotalCount(PagingVO paging);// �˻��� �� �Խñ� �� ��������
}
