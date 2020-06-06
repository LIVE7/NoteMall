package com.project.notemall.service;

import java.util.List;
import java.util.Map;

import com.project.notemall.domain.CartVO;
import com.project.notemall.domain.PReviewVO;
import com.project.notemall.domain.PagingVO;
import com.project.notemall.domain.ProductVO;

public interface ShopService {
	
	/** 상품관련 메소드================*/
	
	/*Pspec 별로 상품 정보 가져오기*/
	public List<ProductVO> selectByPspec(String pspec);
	
	/*카테고리별 상품정보 가져오기*/
	public List<ProductVO> selectByCategory(String code);
	
	/**상품번호로 특정 상품 정보 가져오기*/
	public ProductVO selectByPnum(Integer pnum);
	
	/*모든 브랜드 상품정보*/
	public List<ProductVO> selectByAllPcompany(String pcompany);
	
	/*해당 브랜드 상품정보*/
	public List<ProductVO> selectByPcompany(PagingVO paging);
	
	int getTotalCount();//총 게시글 수 가져오기
	int getTotalCount(PagingVO paging);//검색한 총 게시글 수 가져오기
	
	/**장바구니 관련 메소드===============*/
	int addCart(CartVO cartVo);//장바구니 추가하기
	int updateCartQty(CartVO cartVo);//장바구니 추가 관련=>기존에 담긴 상품이면 수량만 수정하기
	int editCart(CartVO cartVo);// 장바구니 수정하기
	List<CartVO> selectCartView(int midx);//특정 회원의 장바구니 목록보기
	CartVO getCartTotal(int idx);//회원의 장바구니 총액과 총포인트 가져오기
	
	int delCart(int cartNum);
	int delCartAll(CartVO cartVo);
	int delCartOrder(Map<String,Integer>map);
	
	int getCartCountByIdx(CartVO cartVo);

	/**상품평-리뷰 관련 메소드=================*/
	int insertReview(PReviewVO rvo);
	List<PReviewVO> getReviewList(int pnum_fk, int start, int end);
	int getReviewCount(int pnum_fk);
	int deleteReview(int ridx);
	PReviewVO getReview(int ridx);
	int updateReview(PReviewVO rvo);

	
}



