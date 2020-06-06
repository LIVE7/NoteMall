package com.project.notemall.controller;

import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.notemall.domain.UserVO;
import com.project.notemall.service.UserService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class JoinController {

	@Autowired
	private UserService userService;
	
	/** ���̵� �ߺ��˻� */
	@GetMapping(value = "/join/idCheck", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	@ResponseBody
	public Map<String, Integer> idCheck(@RequestParam("userid") String userid) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("isExistID", userService.idCheck(userid));
		
		return map;
	}

	/** ȸ������ �� �ۼ��� ������ �� */
	@PostMapping("/join")
	public String createUser(Model m, @ModelAttribute("join") UserVO user) {
		System.out.println(user);
		int n = userService.createUser(user);
		String str = (n > 0) ? "ȸ�� ���� �Ϸ�" : "ȸ�� ���� ����";
		String loc = (n > 0) ? "home" : "javascript:history.back()";
		
		m.addAttribute("message", str);
		m.addAttribute("loc", loc);
		
		return "message";
	}
}
