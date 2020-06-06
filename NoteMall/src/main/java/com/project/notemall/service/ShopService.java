package com.project.notemall.service;

import java.util.List;
import java.util.Map;

import com.project.notemall.domain.CartVO;
import com.project.notemall.domain.PReviewVO;
import com.project.notemall.domain.PagingVO;
import com.project.notemall.domain.ProductVO;

public interface ShopService {
	
	/** ��ǰ���� �޼ҵ�================*/
	
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
	
	int getTotalCount();//�� �Խñ� �� ��������
	int getTotalCount(PagingVO paging);//�˻��� �� �Խñ� �� ��������
	
	/**��ٱ��� ���� �޼ҵ�===============*/
	int addCart(CartVO cartVo);//��ٱ��� �߰��ϱ�
	int updateCartQty(CartVO cartVo);//��ٱ��� �߰� ����=>������ ��� ��ǰ�̸� ������ �����ϱ�
	int editCart(CartVO cartVo);// ��ٱ��� �����ϱ�
	List<CartVO> selectCartView(int midx);//Ư�� ȸ���� ��ٱ��� ��Ϻ���
	CartVO getCartTotal(int idx);//ȸ���� ��ٱ��� �Ѿװ� ������Ʈ ��������
	
	int delCart(int cartNum);
	int delCartAll(CartVO cartVo);
	int delCartOrder(Map<String,Integer>map);
	
	int getCartCountByIdx(CartVO cartVo);

	/**��ǰ��-���� ���� �޼ҵ�=================*/
	int insertReview(PReviewVO rvo);
	List<PReviewVO> getReviewList(int pnum_fk, int start, int end);
	int getReviewCount(int pnum_fk);
	int deleteReview(int ridx);
	PReviewVO getReview(int ridx);
	int updateReview(PReviewVO rvo);

	
}



