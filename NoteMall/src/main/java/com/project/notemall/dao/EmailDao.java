package com.project.notemall.dao;

import java.util.List;

import com.project.notemall.domain.EmailVO;

public interface EmailDao {
	
	/** �̸��� ����ϱ� */
	int insertEmail(EmailVO email);
	
	/** �̸��� ��� ����*/
	List<EmailVO> getEmail();
	
	/** �� ����*/
	int deleteEmail(int idx);//����

}
