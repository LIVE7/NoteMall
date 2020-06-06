package com.project.notemall.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.notemall.common.CommonUtil;
import com.project.notemall.domain.EmailVO;
import com.project.notemall.service.EmailService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j

public class EmailController {
	
	@Autowired
	private EmailService Emailservice;
	
	@Inject
	private CommonUtil util;
	
	
	/** �������Ϳ� �̸��� ��������� */
	@PostMapping("/emailAdd")
	public String emailAdd(Model m, @ModelAttribute("email") EmailVO email) {
		System.out.println("emailAdd��û ����");
		int n = Emailservice.insertEmail(email);
		String str=(n>0)? "����� �Ϸ��߽��ϴ�.":"����� �����߽��ϴ�.";
		String loc=(n>0)? "javascript:history.back()":"javascript:history.back()";
		m.addAttribute("message",str);
		m.addAttribute("loc",loc);
		return "message";
	}
	@PostMapping("/user/emailAdd")
	public String emailAddUser(Model m, @ModelAttribute("email") EmailVO email) {
		System.out.println("emailAdd��û ����");
		int n = Emailservice.insertEmail(email);
		String str=(n>0)? "����� �Ϸ��߽��ϴ�.":"����� �����߽��ϴ�.";
		String loc=(n>0)? "javascript:history.back()":"javascript:history.back()";
		m.addAttribute("message",str);
		m.addAttribute("loc",loc);
		return "message";
	}
	
	@PostMapping("/admin/emailAdd")
	public String emailAddAdmin(Model m, @ModelAttribute("email") EmailVO email) {
		System.out.println("emailAdd��û ����");
		int n = Emailservice.insertEmail(email);
		String str=(n>0)? "����� �Ϸ��߽��ϴ�.":"����� �����߽��ϴ�.";
		String loc=(n>0)? "javascript:history.back()":"javascript:history.back()";
		m.addAttribute("message",str);
		m.addAttribute("loc",loc);
		return "message";
	}
	
	/** ��ϵ� �������� ����*/
	@GetMapping("/admin/emailList")
	public String emailList(Model m) {
		System.out.println("emailList��û ����");
		List<EmailVO> arr = this.Emailservice.getEmail();
		m.addAttribute("emailArr",arr);
		return "/admin/emailList";
	}
	
	/** ��ϵ� �������� ����*/
	@GetMapping("/admin/emailDelete")
	public String emailDelete(Model m, @RequestParam(defaultValue="0") int idx) {
		System.out.println("emailDelete��û ����");

		if(idx==0) {
			return util.addMsgBack(m, "�߸� ���� ����Դϴ�.");
		}
		
		int n = this.Emailservice.deleteEmail(idx);
		String str=(n>0)? "������ �Ϸ��߽��ϴ�.":"������ �����߽��ϴ�.";
		String loc=(n>0)? "emailList":"javascript:history.back()";
		m.addAttribute("message",str);
		m.addAttribute("loc",loc);
		
		return "message";
	}

}
