package com.tanunwai.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tanunwai.beans.MembershipBeans;

public class AuthorizationFilter implements Filter {

    public AuthorizationFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse) resp;
		Cookie[] cookies = request.getCookies();		
		Stream<Cookie> stream = Objects.nonNull(cookies) ? Arrays.stream(cookies) : Stream.empty();				
		String cookiesValue = stream
				.filter(cookie -> "Author".equals(cookie.getName())).findFirst()
				.orElse(new Cookie("Author", null)).getValue();
		if (Objects.nonNull(cookiesValue)) {
			MembershipBeans sessionUsers=(MembershipBeans)request.getSession().getAttribute("cred");
			request.setAttribute("sessionUsers", sessionUsers);	
			chain.doFilter(request, response);
		}else {
			String msg="You're not members.Please register first!";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/WEB-INF/pages/showresult.jsp").forward(request, response);			
		}
	}	

	public void init(FilterConfig fConfig) throws ServletException {
	}
}
