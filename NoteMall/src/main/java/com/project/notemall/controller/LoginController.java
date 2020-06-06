package com.project.notemall.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.notemall.common.CommonUtil;
import com.project.notemall.domain.NotUserException;
import com.project.notemall.domain.UserVO;
import com.project.notemall.service.UserService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class LoginController {

	@Inject
	private CommonUtil util;

	@Inject
	private UserService userservice;

	/** �α��� �� �� */
	@PostMapping("/login")
	public String loginEnd(Model m, HttpSession ses, @ModelAttribute("user") UserVO user) throws NotUserException {

		if (user.getUserid() == null || user.getPwd() == null || user.getUserid().trim().isEmpty()
				|| user.getPwd().trim().isEmpty()) {
			return util.addMsgBack(m, "���̵�� ��й�ȣ�� �Է��ϼ���");
		}
		UserVO loginUser = userservice.loginCheck(user);

		String msg = "";
		String loc = "";

		if (loginUser != null) {
			ses.setAttribute("loginUser", loginUser);
			msg = loginUser.getName() + "�� ȯ���մϴ�.";
			loc = "home";
		}

		return util.addMsgLoc(m, msg, loc);
	}

	/** �α׾ƿ� */
	@GetMapping("/logout")
	public String logout(HttpSession ses) {
		ses.invalidate();
		return "redirect:home";
	}

	/** ����ó�� */
	@ExceptionHandler(NotUserException.class)
	public String exceptionHandle(Exception e) {
		log.error(e.getMessage());
		return "login/errorAlert";
	}

}
