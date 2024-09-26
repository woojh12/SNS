package com.jhsns.Sns.interceptor;

import java.io.IOException;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class PermissionInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request
			, HttpServletResponse response
			, Object handle) throws IOException
	{
		HttpSession session = request.getSession();
		
		Integer userId = (Integer)session.getAttribute("userId");
		
		// Post & Get Mapping을 제외한 url 얻어오기
		String uri = request.getRequestURI();
		
		if(userId == null)
		{
			// 로그인이 되지 않은 상태에서 게시글 접근시
			if(uri.startsWith("/post"))
			{
				response.sendRedirect("/user/login-view");
				
				return false;
			}
		}
		else
		{
			if(uri.startsWith("/user"))
			{
				response.sendRedirect("/post/list-view");
				
				return false;
			}
		}
		
		return true;
	}
}
