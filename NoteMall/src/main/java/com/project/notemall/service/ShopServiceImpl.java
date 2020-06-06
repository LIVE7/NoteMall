package com.project.notemall.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.project.notemall.dao.CartDao;
import com.project.notemall.dao.ProductDao;
import com.project.notemall.domain.CartVO;
import com.project.notemall.domain.PReviewVO;
import com.project.notemall.domain.PagingVO;
import com.project.notemall.domain.ProductVO;

import lombok.extern.log4j.Log4j;

@Log4j
@Service("shopServiceImpl")
public class ShopServiceImpl implements ShopService {
	
	@Inject
	private ProductDao productDao;
	
	@Inject
	private CartDao cartDao; 
	//shop.dao.CartDao(���ӽ����̽�) ������Ʈ id�� (�޼ҵ� �̸�)

	@Override
	public List<ProductVO> selectByAllPcompany(String pcompany) {

		return this.productDao.selectByAllPcompany(pcompany);
	}
	
	@Override
	public List<ProductVO> selectByPcompany(PagingVO paging) {

		return this.productDao.selectByPcompany(paging);
	}
	
	@Override
	public int getTotalCount() {
		return this.productDao.getTotalCount();
	}
	
	@Override
	public int getTotalCount(PagingVO paging) {
		return this.productDao.getTotalCount(paging);/////////
	}
	
	@Override
	public List<ProductVO> selectByPspec(String pspec) {

		return this.productDao.selectByPspec(pspec);
	}

	@Override
	public List<ProductVO> selectByCategory(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductVO selectByPnum(Integer pnum) {
		
		return this.productDao.selectByPnum(pnum);
	}

	@Override
	public int addCart(CartVO cartVo) {
		
		// 0. Ư��ȸ���� �߰��ϴ� ��ǰ�� CART���̺� �ִ��� ���θ� ī����
		//=>SELECT
		int count = cartDao.getCartCountByIdx(cartVo);
		
		log.info("count="+count);
		int n=0;
		
		if(count>0) {//-ū���
			n=cartDao.updateCartOqty(cartVo);
		}else {//�ƴѰ��
			n=cartDao.addCart(cartVo);
		}
		
		// 1_1. Ư�� ȸ���� ��ٱ��Ͽ� �߰��� ��ǰ�� �̹� �����ϴ� ��ǰ�� ���
		//		������ ���� (���� ������ ���ο� ������ ���� ó��)=> UPDATE��
		
		// 1_2. ��ٱ��Ͽ� ���� ��ǰ�� ���� �߰��ϴ� ���
		//		CART���̺� ��ǰ������ ���Ӱ� ��� => INSERT��
		return n;
	}

	@Override
	public int updateCartQty(CartVO cartVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int editCart(CartVO cartVo) {
		return this.cartDao.editCart(cartVo);
	}

	@Override
	public List<CartVO> selectCartView(int midx) {
		return this.cartDao.selectCartView(midx);
	}
	
	@Override
	public CartVO getCartTotal(int idx) {
		return this.cartDao.getCartTotal(idx);
	}

	@Override
	public int delCart(int cartNum) {
		// TODO Auto-generated method stub
		return this.cartDao.deleteCart(cartNum);
	}

	@Override
	public int delCartAll(CartVO cartVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delCartOrder(Map<String, Integer> map) {
		// TODO Auto-generated method stub
		return this.cartDao.deleteCartByIdx(map);
	}

	@Override
	public int getCartCountByIdx(CartVO cartVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertReview(PReviewVO rvo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<PReviewVO> getReviewList(int pnum_fk, int start, int end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getReviewCount(int pnum_fk) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteReview(int ridx) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PReviewVO getReview(int ridx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateReview(PReviewVO rvo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
