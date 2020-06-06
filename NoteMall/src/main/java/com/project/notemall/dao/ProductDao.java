package com.project.notemall.dao;

import java.util.List;

import com.project.notemall.domain.CategoryVO;
import com.project.notemall.domain.PagingVO;
import com.project.notemall.domain.ProductVO;

public interface ProductDao {
	/** 상품관련 메소드================*/
	/** 상위카테고리 가져오기*/
	public List<CategoryVO> getUpCategory();
	
	/**하위 카테고리 가져오기*/
	public List<CategoryVO> getDownCategory(int upCategory_code);
	
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
	
	int getTotalCount();// 총 게시글 수 가져오기
	int getTotalCount(PagingVO paging);// 검색한 총 게시글 수 가져오기
}
