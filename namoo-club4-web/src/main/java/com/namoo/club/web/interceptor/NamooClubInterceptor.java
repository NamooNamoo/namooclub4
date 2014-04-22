package com.namoo.club.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.namoo.club.web.session.LoginRequired;
import com.namoo.club.web.session.SessionManager;

public class NamooClubInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// handler는 컨트롤러에서 요청을 받은 메소드 객체
		if (handler instanceof HandlerMethod) {
		//
		// $$ 로그인 체크를 해야하는 상황 $$
		// 1. 요청 메소드에 LoginRequired 어노테이션이 붙어 있는 경우 ( LoginRequired(false)는 예외 )
		// 2. Controller에 LoginRequired 어노테이션이 붙어 있는 경우 ( LoginRequired(false)는 예외 )
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			LoginRequired annotationMethod = 
					handlerMethod.getMethodAnnotation(LoginRequired.class);
			LoginRequired annotationType = 
					handlerMethod.getBeanType().getAnnotation(LoginRequired.class);
			
			if (annotationMethod != null && annotationMethod.value() || 
					(annotationMethod == null && annotationType != null && annotationType.value())) {
				// 로그인 체크
				if ( SessionManager.getInstance(request).getLoginId() == null) {
					// 로그인이 되어있지 않으면 로그인 페이지로 리다이렉트
					String contextPath = request.getContextPath();
					response.sendRedirect(contextPath + "/user/login");
				} 
			} 
		}
		return true;
	}
}
