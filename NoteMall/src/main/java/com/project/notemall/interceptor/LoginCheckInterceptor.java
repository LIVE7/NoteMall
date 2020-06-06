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
 * - 컨트롤러가 실행되기 전에 사전 처리할 작업이 있다면
 * 	스프링에서는 인터셉터로 구현한다.
 * - 구현방법
 * 1.
 * [1] HandlerInterceptor인터페이스를 상속받아 구현=> 추상메소드 3개 모두 구현
 * [2] HandlerInterceptorAdapter추상클래스를 상속받아 구현=> 필요한 메소드만 오버라이딩
 * 
 * 2. servlet-context.xml에 인터셉터를 등록하고 매핑정보를 설정한다.
 * <!-- Interceptor 설정=========================== -->
	<interceptors>
		<interceptor>
			<mapping path="/user/**"/>
				<mapping path="/admin/**"/>
				<beans:bean class="shop.interceptor.LoginCheckInterceptor"/>
		</interceptor>
		<!-- 관리자 여부를 체크하는 인터셉터 여기에 추가할 예정  -->
	</interceptors>
	<!--========================================= -->
 * */
@Log4j
public class LoginCheckInterceptor  implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("preHandle");
		// 컨트롤러 실행 전에 호출되는 메소드
		// 로그인 여부를 사전에 체크해서 로그인하지 않으면 이용 못하도록 막자
		HttpSession ses=request.getSession();
		UserVO userVO=(UserVO)ses.getAttribute("loginUser");
		if(userVO!=null) return true;//로그인 했다면 true를 반환
		else {
			//로그인 하지 않았다면
			String msg="로그인을 하시기 바랍니다.";
			String loc=request.getContextPath()+"/home";
			
			request.setAttribute("message", msg);
			request.setAttribute("loc",loc);
			String viewName="/WEB-INF/views/message.jsp";
			RequestDispatcher disp=request.getRequestDispatcher(viewName);//-전송
			disp.forward(request, response);
			return false;//false를 반환하면 Controller로 진입하지 않는다.
		}
			
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// 컨트롤러 실행 후 아직 뷰를 실행하기 전에 호출되는 메소드
		log.info("postHandle");
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		log.info("afterCompletion");
		// 컨트롤러를 실행하고 뷰를 실행한 후에 호출되는 메소드
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
	

}




