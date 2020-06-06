package com.project.notemall.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.project.notemall.domain.UserVO;

import lombok.extern.log4j.Log4j;

/*Interceptor
 * - ��Ʈ�ѷ��� ����Ǳ� ���� ���� ó���� �۾��� �ִٸ�
 * 	������������ ���ͼ��ͷ� �����Ѵ�.
 * - �������
 * 1.
 * [1] HandlerInterceptor�������̽��� ��ӹ޾� ����=> �߻�޼ҵ� 3�� ��� ����
 * [2] HandlerInterceptorAdapter�߻�Ŭ������ ��ӹ޾� ����=> �ʿ��� �޼ҵ常 �������̵�
 * 
 * 2. servlet-context.xml�� ���ͼ��͸� ����ϰ� ���������� �����Ѵ�.
 * <!-- Interceptor ����=========================== -->
	<interceptors>
		<interceptor>
			<mapping path="/user/**"/>
				<mapping path="/admin/**"/>
				<beans:bean class="shop.interceptor.LoginCheckInterceptor"/>
		</interceptor>
		<!-- ������ ���θ� üũ�ϴ� ���ͼ��� ���⿡ �߰��� ����  -->
	</interceptors>
	<!--========================================= -->
 * */
@Log4j
public class LoginCheckInterceptor  implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("preHandle");
		// ��Ʈ�ѷ� ���� ���� ȣ��Ǵ� �޼ҵ�
		// �α��� ���θ� ������ üũ�ؼ� �α������� ������ �̿� ���ϵ��� ����
		HttpSession ses=request.getSession();
		UserVO userVO=(UserVO)ses.getAttribute("loginUser");
		if(userVO!=null) return true;//�α��� �ߴٸ� true�� ��ȯ
		else {
			//�α��� ���� �ʾҴٸ�
			String msg="�α����� �Ͻñ� �ٶ��ϴ�.";
			String loc=request.getContextPath()+"/home";
			
			request.setAttribute("message", msg);
			request.setAttribute("loc",loc);
			String viewName="/WEB-INF/views/message.jsp";
			RequestDispatcher disp=request.getRequestDispatcher(viewName);//-����
			disp.forward(request, response);
			return false;//false�� ��ȯ�ϸ� Controller�� �������� �ʴ´�.
		}
			
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// ��Ʈ�ѷ� ���� �� ���� �並 �����ϱ� ���� ȣ��Ǵ� �޼ҵ�
		log.info("postHandle");
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		log.info("afterCompletion");
		// ��Ʈ�ѷ��� �����ϰ� �並 ������ �Ŀ� ȣ��Ǵ� �޼ҵ�
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
	

}




