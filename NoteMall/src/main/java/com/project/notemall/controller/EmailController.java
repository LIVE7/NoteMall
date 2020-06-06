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
	
	
	/** 뉴스레터에 이메일 등록했을때 */
	@PostMapping("/emailAdd")
	public String emailAdd(Model m, @ModelAttribute("email") EmailVO email) {
		System.out.println("emailAdd요청 들어옴");
		int n = Emailservice.insertEmail(email);
		String str=(n>0)? "등록을 완료했습니다.":"등록을 실패했습니다.";
		String loc=(n>0)? "javascript:history.back()":"javascript:history.back()";
		m.addAttribute("message",str);
		m.addAttribute("loc",loc);
		return "message";
	}
	@PostMapping("/user/emailAdd")
	public String emailAddUser(Model m, @ModelAttribute("email") EmailVO email) {
		System.out.println("emailAdd요청 들어옴");
		int n = Emailservice.insertEmail(email);
		String str=(n>0)? "등록을 완료했습니다.":"등록을 실패했습니다.";
		String loc=(n>0)? "javascript:history.back()":"javascript:history.back()";
		m.addAttribute("message",str);
		m.addAttribute("loc",loc);
		return "message";
	}
	
	@PostMapping("/admin/emailAdd")
	public String emailAddAdmin(Model m, @ModelAttribute("email") EmailVO email) {
		System.out.println("emailAdd요청 들어옴");
		int n = Emailservice.insertEmail(email);
		String str=(n>0)? "등록을 완료했습니다.":"등록을 실패했습니다.";
		String loc=(n>0)? "javascript:history.back()":"javascript:history.back()";
		m.addAttribute("message",str);
		m.addAttribute("loc",loc);
		return "message";
	}
	
	/** 등록된 뉴스레터 보기*/
	@GetMapping("/admin/emailList")
	public String emailList(Model m) {
		System.out.println("emailList요청 들어옴");
		List<EmailVO> arr = this.Emailservice.getEmail();
		m.addAttribute("emailArr",arr);
		return "/admin/emailList";
	}
	
	/** 등록된 뉴스레터 삭제*/
	@GetMapping("/admin/emailDelete")
	public String emailDelete(Model m, @RequestParam(defaultValue="0") int idx) {
		System.out.println("emailDelete요청 들어옴");

		if(idx==0) {
			return util.addMsgBack(m, "잘못 들어온 경로입니다.");
		}
		
		int n = this.Emailservice.deleteEmail(idx);
		String str=(n>0)? "삭제를 완료했습니다.":"삭제를 실패했습니다.";
		String loc=(n>0)? "emailList":"javascript:history.back()";
		m.addAttribute("message",str);
		m.addAttribute("loc",loc);
		
		return "message";
	}

}
