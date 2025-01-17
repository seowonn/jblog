package jblog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jblog.service.UserService;
import jblog.vo.UserVo;

public class AuthInterceptor implements HandlerInterceptor {
	
	@Autowired
	private UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		// 인증 여부 확인 (UserVo 조회)
		UserVo authUser = (UserVo) request.getServletContext().getAttribute("authUser");
		if(authUser == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		
		// 인증자와 경로 사용자 이름 일치 확인
		String[] pathParts = request.getRequestURI().split("/");
		if(pathParts.length > 2 && pathParts[3].length() > 0) {
			UserVo user = userService.getUser(pathParts[3]);
			if(!user.getId().equals(authUser.getId())) {
				response.sendRedirect(request.getContextPath() + "/main");
		        return false;
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/main");
	        return false;
		}
		
		return true;
	}
	
}
