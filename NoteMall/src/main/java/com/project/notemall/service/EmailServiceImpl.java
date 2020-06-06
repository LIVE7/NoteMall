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

	/** 이메일 등록하기 */
	@Override
	public int insertEmail(EmailVO Email) {
		return this.emailDao.insertEmail(Email);
	}

	/** 이메일 목록 보기*/
	@Override
	public List<EmailVO> getEmail() {
		return this.emailDao.getEmail();
	}
	
	/** 글 삭제*/
	@Override
	public int deleteEmail(int idx) {
		return this.emailDao.deleteEmail(idx);
	}

}
