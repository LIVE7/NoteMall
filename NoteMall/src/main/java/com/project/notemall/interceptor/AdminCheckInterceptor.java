package com.project.notemall.interceptor;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.project.notemall.domain.UserVO;

import lombok.extern.log4j.Log4j;

@Log4j
public class AdminCheckInterceptor extends HandlerInterceptorAdapter {
	
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, 
			Object handler) throws ServletException, IOException {
		HandlerMethod method=(HandlerMethod)handler;
		Method mObj=method.getMethod();
		//���� �����Ϸ��� �޼ҵ� ������ �� ������ �˾Ƴ� �� ����
		log.info("Method: "+mObj);
		log.info("Bean: "+method.getBean());
		
		HttpSession ses=req.getSession();
		UserVO user=(UserVO)ses.getAttribute("loginUser");
		if(user.getUserid().equals("admin")) {//-������ ���̵�
			return true;
		}else {
			String msg="�����ڸ� �̿� �����մϴ�";
			String loc="javascript:history.back()";
			req.setAttribute("message", msg);
			req.setAttribute("loc", loc);
			
			String arg0="/WEB-INF/views/message.jsp";
			RequestDispatcher disp=req.getRequestDispatcher(arg0);
			disp.forward(req, res);		
			
			return false;
		}
	}

}



