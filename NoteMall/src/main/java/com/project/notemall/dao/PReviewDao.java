package com.project.notemall.dao;

import java.util.List;

import com.project.notemall.domain.PReviewVO;

public interface PReviewDao {

	/**상품평-리뷰 관련 메소드=================*/
	int insertReview(PReviewVO rvo);
	List<PReviewVO> getReviewList(int pnum_fk, int start, int end);
	int getReviewCount(int pnum_fk);
	int deleteReview(int ridx);
	PReviewVO getReview(int ridx);
	int updateReview(PReviewVO rvo);
}
