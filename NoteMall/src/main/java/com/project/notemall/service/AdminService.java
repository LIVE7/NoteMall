package com.project.notemall.service;

import java.util.List;

import com.project.notemall.domain.CategoryVO;
import com.project.notemall.domain.OrderVO;
import com.project.notemall.domain.ProductVO;
import com.project.notemall.domain.SumVO;

public interface AdminService {

	// 상위 카테고리 등록
	int addUpCategory(CategoryVO cvo);

	// 하위 카테고리 등록
	int addDownCategory(CategoryVO cvo);

	// 상위 카테고리 삭제
	int deleteUpCategory(int upcg_code);

	// 하위 카테고리 삭제
	int deleteDownCategory(int downcg_code);

	// 상위 카테고리 목록 가져오기
	public List<CategoryVO> getUpCategory();

	// 하위 카테고리 목록 가져오기
	public List<CategoryVO> getDownCategory(int upcg_code);

	//총 상품 갯수 가져오기
	int getTotalProduct();
	// 상품정보 등록하는 메소드
	int addProduct(ProductVO prod);

	// 상품목록 가져오기
	List<ProductVO> listProduct(int start, int end);

	// [주문관련] 월별 주문 목록 가져오기
	List<OrderVO> getOrderListByMonth(String month);

	// 배송상태, 지불상태를 관리하는 메소드
	void manageOrder(String onum, String colName, String colVal);
	

	
	//[매출 통계 구하기] 연도별 매출통계 가져오기
	List<SumVO> getSumYear();
	
	//[매출 통계 구하기] 해당 년도 월별 매출통계 가져오기
	List<SumVO> getSumMonth(String year);
	
	//주문자의 누적포인트 가져오기
	int getUserSumPoint(int midx_fk);
}
