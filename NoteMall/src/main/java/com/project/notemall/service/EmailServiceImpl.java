package com.project.notemall.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.project.notemall.dao.EmailDao;
import com.project.notemall.domain.EmailVO;

import lombok.extern.log4j.Log4j;

@Service("EmailServiceImpl")
@Log4j

public class EmailServiceImpl implements EmailService {
	
	@Inject
	private EmailDao emailDao;

	/** �̸��� ����ϱ� */
	@Override
	public int insertEmail(EmailVO Email) {
		return this.emailDao.insertEmail(Email);
	}

	/** �̸��� ��� ����*/
	@Override
	public List<EmailVO> getEmail() {
		return this.emailDao.getEmail();
	}
	
	/** �� ����*/
	@Override
	public int deleteEmail(int idx) {
		return this.emailDao.deleteEmail(idx);
	}

}
