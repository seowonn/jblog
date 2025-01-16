package jblog.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jblog.service.BlogService;
import jblog.vo.BlogVo;
import jblog.vo.UserVo;

public class BlogInterceptor implements HandlerInterceptor {
	
	private BlogService blogService;
	
	public BlogInterceptor(BlogService blogService) {
		this.blogService = blogService;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		BlogVo blogVo = (BlogVo) request.getServletContext().getAttribute("blogVo");
		UserVo userVo = (UserVo) request.getServletContext().getAttribute("authUser");
		if(blogVo == null) {
			if(userVo == null) {
				response.sendRedirect(request.getContextPath() + "/user/login");
				return false;
			}
			blogVo = blogService.getUserBlog(userVo.getId());
			request.getServletContext().setAttribute("blogVo", blogVo);
		}
		return true;
	}
	
}
