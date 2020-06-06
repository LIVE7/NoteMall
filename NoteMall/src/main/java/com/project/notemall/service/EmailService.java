package com.project.notemall.service;

import java.util.List;

import com.project.notemall.domain.EmailVO;

public interface EmailService {
	
	/** 이메일 등록하기 */
	int insertEmail(EmailVO email);
	
	/** 이메일 목록 보기*/
	List<EmailVO> getEmail();
	
	/** 글 삭제*/
	int deleteEmail(int idx);//삭제
	
}
