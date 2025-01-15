package jblog.security;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LogoutInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		request.getServletContext().removeAttribute("authUser");
		
		response.sendRedirect(request.getContextPath());
		return false;
	}
	
	

}
