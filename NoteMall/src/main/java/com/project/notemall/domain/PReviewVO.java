package com.project.notemall.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class PReviewVO implements Serializable {

	private Integer ridx;//리뷰글번호
	private int pnum_fk;//상품번호
	private int idx_fk; //회원번호
	private String title;//리뷰제목
	private String content;//리뷰내용
	private int score;//평가점수
	private String filename;//업로드 이미지파일명
	private java.sql.Date rdate;//리뷰 작성일
	
	private String name;//작성자 이름
	private String userid;//작성자 아이디
	
}
