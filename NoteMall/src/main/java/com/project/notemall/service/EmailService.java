package com.project.notemall.service;

import java.util.List;

import com.project.notemall.domain.EmailVO;

public interface EmailService {
	
	/** �̸��� ����ϱ� */
	int insertEmail(EmailVO email);
	
	/** �̸��� ��� ����*/
	List<EmailVO> getEmail();
	
	/** �� ����*/
	int deleteEmail(int idx);//����
	
}
